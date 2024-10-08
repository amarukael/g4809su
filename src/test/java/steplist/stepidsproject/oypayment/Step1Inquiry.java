package steplist.stepidsproject.oypayment;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import constant.ConstantIDSProject;
import helper.Helper;
import helper.IDSProjectHelper;
import helper.SOAPAPIHelper;
import helper.SOAPHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.idsproject.oypayment.inquiry.ReqInquiry;
import model.idsproject.oypayment.inquiry.ResInquiry;
import org.json.JSONObject;
import org.json.XML;
import utility.Rand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step1Inquiry {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    IDSProjectHelper idsProjHelp = new IDSProjectHelper();
    SOAPHelper soapHelp = new SOAPHelper();
    public static Map<String, ResInquiry> lresInq = new HashMap<>();
    ReqInquiry reqInq;
    ResInquiry resInq;
    String urlSOAPIDSProj = ConstantIDSProject.urlIDSProjectOYPayment;
    String secretKey =  ConstantIDSProject.secretKey;
    Scenario scenario = Hooks.scenario;

    @Given("Valid Request \\({string}, {string}, {string}) API Inquiry IDSProject")
    public void valid_request_api_inquiry_ids_project(String storeId, String productId, String paymentCode) {
        String messageId = storeId + r.getExternalID(0);
        String timeStamp = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String timeStp = help.createTrxDate("HHmmss");
        String trackingRef = messageId;
        String fSign = storeId + paymentCode + trackingRef + timeStp;

        String signature = "";
        try {
            signature = idsProjHelp.createSign(fSign, secretKey);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqInquiry req = new ReqInquiry("", "", "", messageId, paymentCode, productId
                , signature, storeId, timeStamp, trackingRef);
        reqInq = req;
        scenario.log("Req Body: " + gson.toJson(reqInq));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}, {string}) API Inquiry IDSProject")
    public void invalid_request_api_inquiry_ids_project(String storeId, String productId, String paymentCode
            , String param, String descTest) {
        String messageId = storeId + r.getExternalID(0);
        String timeStamp = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String timeStp = help.createTrxDate("HHmmss");
        String trackingRef = messageId;
        String fSign = storeId + paymentCode + trackingRef + timeStp;

        String signature = "";
        try {
            signature = idsProjHelp.createSign(fSign, secretKey);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqInquiry req = new ReqInquiry("", "", "", messageId, paymentCode, productId
                , signature, storeId, timeStamp, trackingRef);
        reqInq = req;
        scenario.log("Req Body: " + gson.toJson(reqInq));
    }

    @When("Partner hit API Inquiry IDSProject")
    public void partner_hit_api_inquiry_ids_project() {
        Map<String, String> rowData = new HashMap<>();
        JSONObject strJson = new JSONObject(gson.toJson(reqInq));
        Iterator<String> keys = strJson.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String val = strJson.get(key).toString();
            rowData.put(key, val);
        }
        scenario.log("Url: " + urlSOAPIDSProj);
        String reqSoapInq = soapHelp.buildIDSProjSoapRequestOyPayment(rowData, "inquiry");
        scenario.log("input: " + reqSoapInq);

        String resSoapInq = SOAPAPIHelper.callSoapWebService(reqSoapInq, urlSOAPIDSProj);
        scenario.log("output: " + resSoapInq);

        if (!resSoapInq.isEmpty()) {
            // Convert to Json
            JSONObject xmlJSONObj = XML.toJSONObject(resSoapInq);
            String jsonPrettyPrintString = xmlJSONObj.toString(0);
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonPrettyPrintString);

                JsonNode kReturn = rootNode.path("S:Envelope").path("S:Body").path("ns2:inquiryResponse").path("return");
                resInq = gson.fromJson(kReturn.toString(), ResInquiry.class);
                System.out.println("after convert: " + gson.toJson(resInq));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resInq.setResponseCode("05");
            resInq.setResponseDesc("ERROR - General Error");
        }
    }

    @Then("Partner gets response {string} and response desc {string} API Inquiry IDSProject")
    public void partner_gets_response_and_response_desc_api_inquiry_ids_project(String rc, String rcDesc) {
        assertEquals(resInq.getResponseCode(), rc);
        assertEquals(resInq.getResponseDesc(), rcDesc);
        lresInq.put(reqInq.getPaymentCode(), resInq);
        scenario.log("Res Body: " + gson.toJson(resInq));
    }
}
