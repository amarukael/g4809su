package helper;

import constant.ConstantMandiri;
import utility.ConvertKey;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.*;

public class MandiriHelper {

    public Map<String, String> getHeaderMandiriToken(String param, String condition) {

        String xClientKey = ConstantMandiri.xClientKey;
        if (param.equals("clientKey")){
            if(condition.equals("null")) {
                xClientKey = "";
            }else if(condition.equals("invalid format")){
                xClientKey += "123141231";
            }
        }

        String xTimeStamp = createTimeStamp();
        if (param.equals("timeStamps")){
            if(condition.equals("null")){
                xTimeStamp = "";
            }else if(condition.equals("invalid format")){
                xTimeStamp = "12321";
            }

        }

        String xSign = "";
        try {
            xSign = AsysmetricSign(xClientKey + "|" + xTimeStamp, ConstantMandiri.privateKey);
            if (param.equals("signature")){
                if(condition.equals("null")){
                    xSign = "";
                }else if(condition.equals("invalid format")){
                    xSign += "12312311";
                }
            }
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

    public Map<String,String> getMandiriGlobalHeader(String param,
                                                     String condition ,
                                                     String token,
                                                     String req,
                                                     String relativeUrl,
                                                     String externalId,
                                                     String secretKey){

        String authorization = token;
        if(param.equals("authorization")){
            if (condition.equals("null")) {
                authorization = null;
            }else if (condition.equals("invalid value")){
                authorization = token + "Z";
            }
        }

        String xEternalId = externalId;
        if (param.equals("externalId")){
            if(condition.equals("null")){
                xEternalId = null;
            }else if(condition.equals("invalid format"))
                xEternalId = "Aa" + xEternalId;
        }

        String xTimeStamp = createTimeStamp();
        if (param.equals("timeStamps")){
            if(condition.equals("null")){
                xTimeStamp = null;
            }else if(condition.equals("invalid format")){
                xTimeStamp = "12321";
            }

        }

        String xSign = "";
        try {
            xSign = SymetricSign(req,relativeUrl,token,xTimeStamp,secretKey);
            if (param.equals("signature")){
                if(condition.equals("null")){
                    xSign = "";
                }else if(condition.equals("invalid value")){
                    xSign += "12312311";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", authorization == null ? null : "Bearer " + authorization);
        headers.put("X-SIGNATURE", xSign);
        headers.put("X-TIMESTAMP", xTimeStamp);
        headers.put("X-PARTNER-ID", "BMRI");
        headers.put("X-EXTERNAL-ID", xEternalId);
        headers.put("CHANNEL-ID","6021");

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

    public String AsysmetricSign(String message, String privateKey) throws Exception {
        String result = "";
        utility.signature.SHA256RSA sha256RSA = new utility.signature.SHA256RSA();
        String privateKeyContent = privateKey;
        privateKeyContent = privateKeyContent
                                .replaceAll("\n", "")
                                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                                .replace("-----END RSA PRIVATE KEY-----", "");
        privateKeyContent = privateKeyContent.replace("\n", "");

        ConvertKey ck = new ConvertKey();
        PrivateKey key = ck.genPrivateKey(privateKeyContent);
        System.out.println(key.toString());
        result = sha256RSA.sign(message, key);

        return result;
    }
    public String SymetricSign(String requestbody, String relativepath, String token,
                               String timestamp, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        String result = "";
        Helper help = new Helper();

        byte[] sha256 = utility.signature.SHA256.getSHA256(requestbody);
        String data = "POST:"+ relativepath + ":" + token + ":" + utility.signature.SHA256.toHexString(sha256) + ":" + timestamp;
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA512");
        hmacSha512.init(secretKeySpec);
        byte[] hash = hmacSha512.doFinal(data.getBytes());
        result = Base64.getEncoder().encodeToString(hash);

        return result;
    }


}
