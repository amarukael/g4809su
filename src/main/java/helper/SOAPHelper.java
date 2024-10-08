package helper;

import java.util.Map;

public class SOAPHelper {
    public String buildIDSProjSoapRequestIDM(Map<String, String> rowData, String type) {
        StringBuilder soapRequest = new StringBuilder();
        soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:idm=\"http://idm.ids.com/\">");
        soapRequest.append("<soapenv:Header/>");
        soapRequest.append("<soapenv:Body>");
        soapRequest.append("<idm:").append(type).append(">");
        soapRequest.append("<input>");

        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            soapRequest.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
        }

        soapRequest.append("</input>");
        soapRequest.append("</idm:").append(type).append(">");
        soapRequest.append("</soapenv:Body>");
        soapRequest.append("</soapenv:Envelope>");

        return soapRequest.toString();
    }

    public String buildIDSProjSoapRequestOyPayment(Map<String, String> rowData, String type) {
        StringBuilder soapRequest = new StringBuilder();
        soapRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:oyp=\"http://oypayment.idm.idsproject.ids.com/\">");
        soapRequest.append("<soapenv:Header/>");
        soapRequest.append("<soapenv:Body>");
        soapRequest.append("<oyp:").append(type).append(">");
        soapRequest.append("<inputParam>");

        for (Map.Entry<String, String> entry : rowData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            soapRequest.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
        }

        soapRequest.append("</inputParam>");
        soapRequest.append("</oyp:").append(type).append(">");
        soapRequest.append("</soapenv:Body>");
        soapRequest.append("</soapenv:Envelope>");

        return soapRequest.toString();
    }
}
