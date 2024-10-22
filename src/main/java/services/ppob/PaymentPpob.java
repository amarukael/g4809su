package services.ppob;

import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;

import constant.ConstantPpob;
import database.PpobDataHelper;
import helper.APIHelper;
import helper.Helper;
import lombok.Getter;
import model.ResUrl;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;
import utility.Rand;
import utility.signature.SHA1;

public class PaymentPpob {
    @Getter
    String urlPayment = ConstantPpob.ppobPayV2;
    Gson gson = new Gson();
    Rand r = new Rand();
    Helper helper = new Helper();
    PpobDataHelper dbHelper = new PpobDataHelper();

    @Getter
    ReqPayment reqPay;

    @Getter
    ResPayment resPay;

    public void generateRequest(
            String trackingRef,
            String partnerId,
            String productId,
            String customerId,
            String extendinfo,
            String totalAmount,
            String terminalid) {
        String trxId = r.getRandomTrxId(partnerId, "");
        String trxDate = helper.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = "";
        String secretKey = dbHelper.getSecretKeyByPartnerId(partnerId);
        System.out.println("Secret Key : " + secretKey);
        try {
            signature = SHA1.crypt(
                    trxId + trxDate + partnerId + productId + customerId + extendinfo + totalAmount + trackingRef
                            + terminalid
                            + secretKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        reqPay = new ReqPayment(trxId, trxDate, partnerId, productId, customerId, extendinfo, totalAmount, trackingRef,
                terminalid,
                signature);

    }

    public void hitRequest() {
        ResUrl resApi = APIHelper.getHitAPI2("", gson.toJson(reqPay), urlPayment, "application/json", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            resPay.setRc(" ");
            resPay.setRcdesc("CANNOT GET DATA FROM RESPONSE - AUTOMATION ERROR");
        } else {
            resPay = gson.fromJson(resApi.getDataJson(), ResPayment.class);
        }
    }

}
