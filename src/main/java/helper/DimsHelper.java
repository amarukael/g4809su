package helper;

import constant.ConstantDims;
import utility.ConvertKey;
import utility.Rand;
import utility.signature.SHA256;
import utility.signature.SHA256RSA;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.*;

public class DimsHelper {

    public Map<String, String> getHeaderToken(String condition) {
        String xClientKey = ConstantDims.xClientKey2;
        if (condition.equals("IDS")) xClientKey = ConstantDims.xClientKey2;
        if (condition.equals("clientKey")) xClientKey = "";

        String xTimeStamp = createTimeStamp();
        if (condition.equals("timeStamps")) xTimeStamp = "";

        String xSign = "";
        try {
            xSign = AsysmetricSign(xClientKey + "|" + xTimeStamp, ConstantDims.privateKey);
            if (condition.equals("signature")) xSign = "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        headers.put("X-CLIENT-KEY", xClientKey);
        headers.put("X-SIGNATURE", xSign);
        headers.put("X-TIMESTAMP", xTimeStamp);
        return headers;
    }

    public Map<String, String> getHeaderGlobal(String condition, String token, String req
            , String relativeUrl, String externalId, String secretKey) {
        String xTimeStamp = createTimeStamp();
        if (condition.equals("timeStamps")) xTimeStamp = "";

        String xEternalId = externalId;
        if (condition.equals("externalId")) xEternalId = "Aa" + xEternalId;

        String authorization = token;
        if (condition.equals("authorization")) authorization = "";
        if (condition.equals("authorizationExpired")) authorization = token + "Z";

        String xSign = "";
        try {
            xSign = SymetricSign(req, relativeUrl, token,xTimeStamp, secretKey);
            if (condition.equals("signature")) xSign = "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");
        headers.put("Authorization", "Bearer " + authorization);
        headers.put("X-TIMESTAMP", xTimeStamp);
        headers.put("X-SIGNATURE", xSign);
        headers.put("X-EXTERNAL-ID", xEternalId);
        headers.put("X-IP-ADDRESS", "103.111.213.93");
        headers.put("X-LONGITUDE", "106.8455");
        headers.put("X-LATITUDE", "-6.2087");

        return headers;
    }

    public String createTimeStamp() {
        String result = "";
        Date dNow = new Date();
        Locale inLocale = new Locale("id", "ID");
        SimpleDateFormat ftpay = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", inLocale);
        result = ftpay.format(dNow);

        return result;
    }

    public String AsysmetricSign(String message, String privKey) throws Exception {
        String result = "";
        SHA256RSA sha256RSA = new SHA256RSA();
        String privateKeyContent = privKey;
        privateKeyContent = privateKeyContent.replaceAll("\n", "").replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "");
        privateKeyContent = privateKeyContent.replace("\n", "");

        ConvertKey ck = new ConvertKey();
        PrivateKey key = ck.genPrivateKey(privateKeyContent);
        result = sha256RSA.sign(message, key);

        return result;
    }

    public String SymetricSign(String requestbody, String relativepath, String token,
                               String timestamp, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        String result = "";
        Helper help = new Helper();

        byte[] sha256 = SHA256.getSHA256(requestbody);
        String data = "POST:"+ relativepath + ":" + token + ":" + SHA256.toHexString(sha256) + ":" + timestamp;
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
        hmacSha512.init(secretKeySpec);
        byte[] hash = hmacSha512.doFinal(data.getBytes());
        result = Base64.getEncoder().encodeToString(hash);

        return result;
    }
}
