package helper;

import utility.AESUtil;
import utility.signature.SHA1;

import java.io.UnsupportedEncodingException;

public class IDSProjectHelper {
    public String createSign(String param, String secretKey) throws UnsupportedEncodingException {
        String result = "";
        result = AESUtil.encryptpadding(param, secretKey);

        return result;
    }
}
