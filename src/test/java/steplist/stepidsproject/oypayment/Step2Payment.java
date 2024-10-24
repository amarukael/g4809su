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
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.idsproject.oypayment.inquiry.ResInquiry;
import model.idsproject.oypayment.payment.ReqPayment;
import model.idsproject.oypayment.payment.ResPayment;
import org.json.JSONObject;
import org.json.XML;
import utility.Rand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step2Payment {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    IDSProjectHelper idsProjHelp = new IDSProjectHelper();
    SOAPHelper soapHelp = new SOAPHelper();
    private static Map<String, ResInquiry> lresInq = new HashMap<>();
    public static Map<String, ResPayment> lresPay = new HashMap<>();
    ResInquiry resInq;
    ReqPayment reqPay;
    ResPayment resPay;
    String urlSOAPIDSProj = ConstantIDSProject.urlIDSProjectOYPayment;
    String secretKey =  ConstantIDSProject.secretKey;
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;

    @Before("@pay_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresInq = Step1Inquiry.lresInq;
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}) API Payment IDSProject")
    public void valid_request_api_payment_ids_project(String storeId, String productId, String paymentCode, String amount) {
        resInq = lresInq.get(paymentCode);
        String messageId = resInq.getMessageID();
        String timeStamp = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String timeStp = help.createTrxDate("HHmmss");
        String trackingRef = resInq.getTrackingRef();
        String fSign = storeId + paymentCode + trackingRef + timeStp;

        String signature = "";
        try {
            signature = idsProjHelp.createSign(fSign, secretKey);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqPayment req = new ReqPayment(amount, messageId, paymentCode, productId, signature, storeId, timeStamp
                , trackingRef);
        reqPay = req;
        scenario.log("Req Body: " + gson.toJson(reqPay));
    }

    @When("Partner hit API Payment IDSProject")
    public void partner_hit_api_payment_ids_project() {
        Map<String, String> rowData = new HashMap<>();
        JSONObject strJson = new JSONObject(gson.toJson(reqPay));
        Iterator<String> keys = strJson.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String val = strJson.get(key).toString();
            rowData.put(key, val);
        }
        scenario.log("Url: " + urlSOAPIDSProj);
        String reqSoapInq = soapHelp.buildIDSProjSoapRequestOyPayment(rowData, "payment");
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

                JsonNode kReturn = rootNode.path("S:Envelope").path("S:Body").path("ns2:paymentResponse").path("return");
                resPay = gson.fromJson(kReturn.toString(), ResPayment.class);
                System.out.println("after convert: " + gson.toJson(resPay));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resPay.setResponseCode("05");
            resPay.setResponseDesc("ERROR - General Error");
        }
    }

    @Then("Partner gets response {string} and response desc {string} API Payment IDSProject")
    public void partner_gets_response_and_response_desc_api_payment_ids_project(String rc, String rcDesc) {
        assertEquals(resPay.getResponseCode(), rc);
        assertEquals(resPay.getResponseDesc(), rcDesc);
        lresPay.put(reqPay.getPaymentCode(), resPay);
        scenario.log("Res Body: " + gson.toJson(resPay));
    }
}