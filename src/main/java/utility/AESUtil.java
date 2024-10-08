package utility;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AESUtil {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey) throws UnsupportedEncodingException {
        MessageDigest sha = null;
        key = myKey.getBytes(StandardCharsets.US_ASCII);
//        System.out.println(new Gson().toJson(key));
        secretKey = new SecretKeySpec(key, "AES");
    }

    public static String encryptpadding(String strToEncrypt, String secret) {
        StringUtil su = new StringUtil();
        String as = strToEncrypt;
        String key = secret;
        int PaddingInput = 16 - (as.length() % 16);
        int PaddingKey = 16 - (key.length() % 16);

        if (as.length() % 16 != 0) {
//            System.out.print(as.length() + PaddingInput + "\n");
            int tmp = as.length() + PaddingInput;
            as = su.leftPadSpasi(as, tmp);
        }
//        System.out.print(key.length() % 16 + "\n");

        if (key.length() % 16 != 0) {
//            System.out.print(key.length() + PaddingKey + "\n");
            key = su.leftPadSpasi(key, key.length() + PaddingKey);
        }

        return encrypt(as, key);
    }


    public static String encrypt(String strToEncrypt, String secret) {
//        System.out.println("encryp \n" + "input : " + strToEncrypt + "\nlength: " + strToEncrypt.length());
//        System.out.println("encryp");
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes());
//            System.out.println(new Gson().toJson(cipherText));
            return DatatypeConverter.printHexBinary(cipherText);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }

        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }

        return null;
    }
}
