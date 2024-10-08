package helper;

import constant.ConstantMnM;
import utility.signature.SHA256RSA;

import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

public class MnMHelper {
    public Map<String, String> getHeaderGlobal(String condition, String req
            , String externalId) {
        String xTimeStamp = createTimeStamp();
        String xExternalId = externalId;
        String xClientId = ConstantMnM.xClientId;
        if (condition.equals("xClientId")) xClientId = xClientId + "xx";

        String xSign = "";
        try {
            String strToSign = req + xTimeStamp;
            System.out.println(strToSign);
            SHA256RSA sha256RSA = new SHA256RSA();
            xSign = sha256RSA.sign4(strToSign, ConstantMnM.privateKey);
            if (condition.equals("signature")) xSign = xSign + "xxx";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        headers.put("X-TIMESTAMP", xTimeStamp);
        headers.put("X-EXTERNAL-ID", xExternalId);
        headers.put("X-CLIENT-ID", xClientId);
        headers.put("X-SIGNATURE", xSign);
        return headers;
    }

    private String createTimeStamp() {
        String result = "";
        Date dNow = new Date();
        Locale inLocale = new Locale("id", "ID");
        SimpleDateFormat ftpay = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", inLocale);
        result = ftpay.format(dNow);

        return result;
    }
}
