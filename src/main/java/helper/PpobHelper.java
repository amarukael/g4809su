package helper;

import utility.signature.SHA1;

import java.io.UnsupportedEncodingException;

public class PpobHelper {
    SHA1 sha1 = new SHA1();

    public String createSign(String param) throws UnsupportedEncodingException {
        String result = "";
        result = SHA1.crypt(param);

        return result;
    }
}
