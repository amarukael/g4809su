package helper;

import utility.Rand;
import utility.signature.MD5;
import utility.signature.SHA1;

import java.io.UnsupportedEncodingException;

public class ApiProjectHelper {

    public String getSignInq(String agentId, String agentPin, String storeId
            , String productId, String customerId, String trxId, String dateTimeReq, String secretKey) {
        String signature = "";
        String fSign = agentId
                + agentPin
                + trxId
                + storeId
                + productId
                + customerId
                + dateTimeReq
                + secretKey;
        try {
            signature = createSign(fSign);
            return signature;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return signature;
    }

    public String getSignPay(String agentId, String agentPin, String storeId
            , String productId, String customerId, String trxId, String dateTimeReq, String paymentPeriod
            , String amount, String charge, String totalAmount, String adminFee, String secretKey) {
        String signature = "";
        String fSign = agentId
                + agentPin
                + trxId
                + storeId
                + productId
                + customerId
                + dateTimeReq
                + paymentPeriod
                + amount
                + charge
                + totalAmount
                + adminFee
                + secretKey;

        try {
            signature = createSign(fSign);
            return signature;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return signature;
    }

    public String getSignRev(String agentId, String agentPin, String storeId
            , String productId, String customerId, String trxId, String dateTimeReq, String secretKey) {
        String signature = "";
        String fSign = agentId
                + agentPin
                + trxId
                + storeId
                + productId
                + customerId
                + dateTimeReq
                + secretKey;

        try {
            signature = createSign(fSign);
            return signature;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return signature;
    }

    public String getSignNonAlfa(String trxId, String trxDate, String partnerId
            , String productId, String customerId, String totalAmount, String trackingRef
            , String terminalId, String secretKey, int flgSign) {
        String signature = "";
        String fSign = "";
        if (flgSign == 0) {
            fSign = partnerId
                    + secretKey;
        } else {
            fSign = trxId +
                    trxDate +
                    partnerId +
                    productId +
                    customerId +
                    totalAmount +
                    trackingRef +
                    terminalId +
                    secretKey;
        }

        try {
            signature = createSignNonAlfa(fSign);
            return signature;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return signature;
    }

    private String createSign(String param) throws UnsupportedEncodingException {
        String result = "";
        result = SHA1.crypt(param);

        return result;
    }

    private String createSignNonAlfa(String param) throws UnsupportedEncodingException {
        String result = "";
        result = MD5.generateMD5Signature(param);

        return result;
    }

    public static String generateTrackingRef(String newTC, String currentTC, String prefix, String trackingref) {
        Rand r = new Rand();
        // Jika tracking reff null
        if (currentTC == null || !currentTC.equals(newTC)) {
            // If TC is null (first iteration) or TC is different from currentTC, generate a new trackingref
            trackingref = r.getRandomTrxId(prefix,"");
        }
        return trackingref;
    }
}
