package services.ppob;

import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;

import constant.ConstantPpob;
import database.PpobDataHelper;
import helper.APIHelper;
import helper.Helper;
import lombok.Getter;
import model.ResUrl;
import model.ppob.inquiry.ReqInquiry;
import model.ppob.inquiry.ResInquiry;
import utility.Rand;
import utility.signature.SHA1;

public class InquiryPpob {

    @Getter
    String urlInquiry = ConstantPpob.ppobInqV2;
    Gson gson = new Gson();
    Rand r = new Rand();
    Helper helper = new Helper();
    PpobDataHelper dbHelper = new PpobDataHelper();

    @Getter
    ReqInquiry reqInq;
    @Getter
    ResInquiry resInq;

    public void generateRequest(
            String partnerId,
            String productId,
            String customerId,
            String extendinfo,
            String terminalid) {
        String trxId = r.getRandomTrxId(partnerId, "");
        String trackingref = trxId;
        String trxDate = helper.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = "";
        String secretKey = dbHelper.getSecretKeyByPartnerId(partnerId);
        System.out.println("Secret Key : " + secretKey);

        try {
            signature = SHA1.crypt(
                    trxId + trxDate + partnerId + productId + customerId + extendinfo + trackingref + terminalid
                            + secretKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(signature);
        reqInq = new ReqInquiry(trxId, trxDate, partnerId, productId, customerId, extendinfo, trackingref,
                terminalid, signature);
    }

    public void hitRequest() {
        ResUrl resApi = APIHelper.getHitAPI2("", gson.toJson(reqInq), urlInquiry, "application/json", null, 0,
                20000);
        if (resApi.getDataJson() == null) {
            resInq.setRc(" ");
            resInq.setRcdesc("CANNOT GET DATA FROM RESPONSE - AUTOMATION ERROR");
        } else {
            resInq = gson.fromJson(resApi.getDataJson(), ResInquiry.class);
        }
    }
}
