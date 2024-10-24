package services.ppob;

import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;

import constant.ConstantPpob;
import database.PpobDataHelper;
import helper.APIHelper;
import helper.Helper;
import lombok.Getter;
import model.ResUrl;
import model.ppob.advice.ReqAdvice;
import model.ppob.advice.ResAdvice;
import utility.Rand;
import utility.signature.SHA1;

public class AdvicePpob {
    @Getter
    String urlAdvice = ConstantPpob.ppobAdvV2;
    Gson gson = new Gson();
    Rand r = new Rand();
    Helper helper = new Helper();
    PpobDataHelper dbHelper = new PpobDataHelper();

    @Getter
    ReqAdvice reqAdv;

    @Getter
    ResAdvice resAdv;

    public void generateRequest(
            String trackingRef,
            String partnerId,
            String productId,
            String customerId,
            String extendinfo,
            String terminalid) {
        String trxId = r.getRandomTrxId(partnerId, "");
        String trxDate = helper.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = "";
        String secretKey = dbHelper.getSecretKeyByPartnerId(partnerId);
        System.out.println("Secret Key : " + secretKey);
        try {
            signature = SHA1.crypt(
                    trxId + trxDate + partnerId + productId + customerId + extendinfo + trackingRef + terminalid
                            + secretKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        reqAdv = new ReqAdvice(trxId, trxDate, partnerId, productId, customerId, extendinfo, trackingRef, terminalid,
                signature);

    }

    public void hitRequest() {
        ResUrl resApi = APIHelper.getHitAPI2("", gson.toJson(reqAdv), urlAdvice, "application/json", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            resAdv.setRc(" ");
            resAdv.setRcdesc("CANNOT GET DATA FROM RESPONSE - AUTOMATION ERROR");
        } else {
            resAdv = gson.fromJson(resApi.getDataJson(), ResAdvice.class);
        }
    }

}
