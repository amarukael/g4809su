package helper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;

public class SOAPAPIHelper {
    public static String callSoapWebService(String soapRequest, String soapUrl) {
        try {
            ByteArrayOutputStream input = new ByteArrayOutputStream();
            InputStream is = new ByteArrayInputStream(soapRequest.getBytes());
            SOAPMessage requestSoapMessage = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL).createMessage(new MimeHeaders(), is);
            requestSoapMessage.writeTo(input);
            HttpClient httpclient = new DefaultHttpClient();
            StringEntity strEntity = new StringEntity(input.toString(), "text/xml", "UTF-8");
            HttpPost post = new HttpPost(soapUrl);
            post.setEntity(strEntity);

            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();
            SOAPMessage soapResponse = null;
            if (respEntity != null) {
                String resp = EntityUtils.toString(respEntity);

                MessageFactory msgFactory = MessageFactory
                        .newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
                soapResponse = msgFactory.createMessage();

                SOAPPart msgPart = soapResponse.getSOAPPart();
                SOAPEnvelope envelope = msgPart.getEnvelope();
                SOAPBody body = envelope.getBody();

                javax.xml.transform.stream.StreamSource _msg = new javax.xml.transform.stream.StreamSource(
                        new java.io.StringReader(resp));
                msgPart.setContent(_msg);

                soapResponse.saveChanges();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                soapResponse.writeTo(output);
            }
            StringWriter writer = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            Source sourceContent = soapResponse.getSOAPPart().getContent();
            StreamResult result = new StreamResult(writer);
            transformer.transform(sourceContent, result);

            String xmlInput = writer.toString();
            return xmlInput;
//            MessageFactory factory = MessageFactory.newInstance();
//            SOAPMessage message = factory.createMessage(
//                    new MimeHeaders(),
//                    new ByteArrayInputStream(xmlInput.getBytes(Charset.forName("UTF-8"))));
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            message.writeTo(baos);
//            System.out.println("Response SOAP Message : " + baos);
//            SOAPBody body = message.getSOAPBody();
        } catch (Exception e) {
            System.out.println(e);
        }

        return "";
    }
}
