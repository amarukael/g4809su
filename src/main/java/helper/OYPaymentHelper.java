package helper;

import utility.signature.SHA1;

import java.io.UnsupportedEncodingException;

public class OYPaymentHelper {
    public String createSign(String param) throws UnsupportedEncodingException {
        String result = "";
        result = SHA1.crypt(param);

        return result;
    }
}
