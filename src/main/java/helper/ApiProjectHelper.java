package helper;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import utility.signature.MD5;
import utility.signature.SHA1;

public class ApiProjectHelper {

    public String getSignInq(String agentId, String agentPin, String storeId, String productId, String customerId,
            String trxId, String dateTimeReq, String secretKey) {
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

    public String getSignPay(String agentId, String agentPin, String storeId, String productId, String customerId,
            String trxId, String dateTimeReq, String paymentPeriod, String amount, String charge, String totalAmount,
            String adminFee, String secretKey) {
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

    public String getSignRev(String agentId, String agentPin, String storeId, String productId, String customerId,
            String trxId, String dateTimeReq, String secretKey) {
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

    public String getSignNonAlfa(String trxId, String trxDate, String partnerId, String productId, String customerId,
            String totalAmount, String trackingRef, String terminalId, String secretKey, int flgSign) {
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

    public String getSignNonAlfa(String partnerId, String type) {
        String signature;
        String secretKey = "ids301214";

        if (type.equalsIgnoreCase("new")) {
            secretKey = "1Ds!201020";
        }

        signature = partnerId + secretKey;

        try {
            signature = createSignNonAlfa(signature);
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

    public String formatterXML(String data) {
        int indent = 2;
        try {
            Source xmlInput = new StreamSource(new StringReader(data));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
