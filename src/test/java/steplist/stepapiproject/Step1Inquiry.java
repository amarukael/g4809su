package steplist.stepapiproject;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import constant.ConstantAPIProject;
import helper.ApiProjectHelper;
import helper.Helper;
import helper.LogHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.apiproject.ReqInquiry;
import model.apiproject.ReqInquiryXml;
import model.apiproject.ResInquiryXml;
import utility.Rand;

public class Step1Inquiry {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    ApiProjectHelper apiProjHelp = new ApiProjectHelper();
    public static Map<String, String> trackingref = new HashMap<>();
    public static Map<String, String> lresInq = new HashMap<>();
    public static Map<String, JSONObject> lreqInq = new HashMap<>();
    public static Map<String, ResInquiryXml> lresInqXml = new HashMap<>();
    ReqInquiry reqInq;
    ReqInquiryXml reqInqXml;
    String resInq;
    ResInquiryXml resInqXml;
    String urlInquiry = ConstantAPIProject.urlAPIProj + ConstantAPIProject.urlAPIProjInq;
    String urlInquiryNonAlfa = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjInqNonAlfa;
    String secretKey = ConstantAPIProject.secretKey;
    String secretKeyNonAlfa = ConstantAPIProject.secretKeyNonAlfa;
    Scenario scenario = Hooks.scenario;
    String inqURL;

    @Given("I perform inquiry with partnerid {string}, productid {string}, customerid {string}")
    public void performInquiryWithCustomerId(String agentId, String productId, String customerId) {
        long unixtime = System.currentTimeMillis() / 1000L;
        String trxId = "QA" + unixtime;
        String trackingRef = trxId;
        String dateTimeReq = help
                .createTrxDate(agentId.equalsIgnoreCase("SAT") ? "yyyyMMddHHmmss" : "yyyy-MM-dd HH:mm:ss");
        String signature;

        ReqInquiry req = null;
        ReqInquiryXml reqInqXml = null;

        if (agentId.equalsIgnoreCase("SAT")) {
            signature = apiProjHelp.getSignInq(agentId, "alf4!ds2015321", "AUTO", productId, customerId, trxId,
                    dateTimeReq, "alfaids301214");
            req = new ReqInquiry(agentId, "alf4!ds2015321", trackingRef, "AUTO", productId, customerId, dateTimeReq,
                    signature);
            req.setAgentID(agentId);
            req.setAgentPIN("alf4!ds2015321");
            req.setAgentTrxID(trackingRef);
            req.setAgentStoreID("AUTO");
            req.setCustomerID(customerId);
            req.setDateTimeRequest(dateTimeReq);
            req.setProductID(productId);
            req.setSignature(signature);
            reqInq = req;
        } else {
            signature = apiProjHelp.getSignNonAlfa(agentId, "old");
            reqInqXml = new ReqInquiryXml(trxId, dateTimeReq, agentId, productId, customerId, trackingRef, "AUTO",
                    signature);
            reqInqXml.setcustomerid(customerId);
            reqInqXml.setpartnerid(agentId);
            reqInqXml.setproductid(productId);
            reqInqXml.setterminalid("AUTO");
            reqInqXml.settrackingref(trackingRef);
            reqInqXml.settrxdate(dateTimeReq);
            reqInqXml.settrxid(trxId);
            reqInqXml.setsignature(signature);
        }

        // Logging request
        if (req != null) {
            String json = gson.toJson(req);
            JSONObject jsonObject = new JSONObject(json);
            lreqInq.put(req.getAgentTrxID(), jsonObject);
            trackingref.put("last", trackingRef);
            inqURL = ConstantAPIProject.urlAPIProj + ConstantAPIProject.urlAPIProjInq +
                    "AgentID=" + req.getAgentID() +
                    "&AgentPIN=" + req.getAgentPIN() +
                    "&AgentTrxID=" + req.getAgentTrxID() +
                    "&AgentStoreID=" + req.getAgentStoreID() +
                    "&ProductID=" + req.getProductID() +
                    "&CustomerID=" + req.getCustomerID() +
                    "&DateTimeRequest=" + req.getDateTimeRequest() +
                    "&Signature=" + req.getSignature();
            scenario.log("URL GET = \n " + inqURL);
        } else {
            scenario.log("Request Body: \n" + reqInqXml.printModelXML());
            lresInq.put("lastTrack", reqInqXml.gettrackingref());
        }
    }

    @Then("I got response inquiry with expected rc {string}")
    public void i_got_response_inquiry_with_expected_rc(String s) {
        String productId = "";
        if (reqInq != null && reqInq.getAgentID().equalsIgnoreCase("SAT")) {
            Response response = RestAssured.given()
                    .get(inqURL);
            scenario.log(response.asPrettyString());

            String resString = response.asString();
            String[] tmpResInq = resString.split("\\|", -1);
            String resRc = tmpResInq[6];
            // String resRcDesc = tmpResInq[7];
            assertEquals(s, resRc);
            productId = reqInq.getProductID();
        } else {
            Response response = RestAssured.given()
                    .contentType("application/xml")
                    .body(reqInqXml.printModelXML())
                    .post("https://ids-fe1.com/APIProject/webresources/inq");

            String data = response.asPrettyString();
            ResInquiryXml resInquiry = new ResInquiryXml();
            scenario.log("Response Body: \n" + response.asPrettyString());

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));

                // Mendapatkan elemen
                resInquiry.setTrxid(getElementValue(document, "trxid"));
                resInquiry.setTrxdate(getElementValue(document, "trxdate"));
                resInquiry.setPartnerid(getElementValue(document, "partnerid"));
                resInquiry.setProductid(getElementValue(document, "productid"));
                resInquiry.setCustomerid(getElementValue(document, "customerid"));
                resInquiry.setCustomername(getElementValue(document, "customername"));
                resInquiry.setTotalamount(getElementValue(document, "totalamount"));
                resInquiry.setAdditionaldata(getElementValue(document, "additionaldata"));
                resInquiry.setRc(getElementValue(document, "rc"));
                resInquiry.setRcdesc(getElementValue(document, "rcdesc"));
                resInquiry.setTrackingref(getElementValue(document, "trackingref"));
                resInquiry.setTerminalid(getElementValue(document, "terminalid"));
                resInquiry.setSignature(getElementValue(document, "signature"));

                Assert.assertEquals(s, getElementValue(document, "rc"));

                resInqXml = resInquiry;
                lresInqXml.put("last", resInqXml);
                productId = resInquiry.getProductid();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Olah Log
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (productId) {
            case "DKF":
                String trackingref = Step1Inquiry.trackingref.get("last");
                JSONObject jsonObject = lreqInq.get(trackingref);
                scenario.log("LOG : " + LogHelper.inqAlfaDKF(trackingref, jsonObject));
                break;
            default:
                break;
        }
    }

    private static String getElementValue(Document doc, String tagName) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getTextContent();
        }
        return null;
    }
}
