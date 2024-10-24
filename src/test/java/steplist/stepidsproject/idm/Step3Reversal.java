package steplist.stepidsproject.idm;

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
import model.idsproject.idm.payment.ResPayment;
import model.idsproject.idm.reversal.ReqReversal;
import model.idsproject.idm.reversal.ResReversal;
import org.json.JSONObject;
import org.json.XML;
import utility.Rand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step3Reversal {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    IDSProjectHelper idsProjHelp = new IDSProjectHelper();
    SOAPHelper soapHelp = new SOAPHelper();
    private static Map<String, ResPayment> lresPay = new HashMap<>();
    ResPayment resPay;
    ReqReversal reqRev;
    ResReversal resRev;
    String urlSOAPIDSProj = ConstantIDSProject.urlIDSProjectIDM;
    String secretKey =  ConstantIDSProject.secretKey;
    Scenario scenario = Hooks.scenario;
    private static String tmpCondition = "";
    private static int flgSetUp;

    @Before("@idsproject_rev_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresPay = Step2Payment.lresPay;
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}) API Reversal IDSProject")
    public void valid_request_api_reversal_ids_project(String storeId, String productId, String paymentCode
            , String amount) {
        resPay = lresPay.get(paymentCode);
        String messageId = resPay.getTrackingRef();
        String timeStamp = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String trackingRef = resPay.getTrackingRef();
        if (resPay.getMessageID() != null)
            messageId =  resPay.getMessageID();

        ReqReversal req = new ReqReversal(amount, messageId, paymentCode, productId, null, storeId, timeStamp
                , trackingRef);
        reqRev = req;
        scenario.log("Req Body: " + gson.toJson(reqRev));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}, {string}, {string}) API Reversal IDSProject")
    public void invalid_request_api_reversal_ids_project(String storeId, String productId, String paymentCode
            , String amount, String param, String descTest) {
        tmpCondition = descTest;
        if (tmpCondition.equals("Invalid_TrackingRef")) {
            System.out.println("Start Delay Scenario...");
            help.delay(45000);
            System.out.println("End Delay Scenario...");
        }

        resPay = lresPay.get(paymentCode);
        String messageId = resPay.getTrackingRef();
        String timeStamp = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String trackingRef = resPay.getTrackingRef();
        if (resPay.getMessageID() != null)
            messageId =  resPay.getMessageID();

//        if (tmpCondition.equals("trackingRef_Invalid")) trackingRef = storeId + r.getExternalID(0);
        if (tmpCondition.equals("Invalid_TrackingRef")) trackingRef = trackingRef + "1";

        ReqReversal req = new ReqReversal(amount, messageId, paymentCode, productId, null, storeId, timeStamp
                , trackingRef);
        reqRev = req;
        scenario.log("Req Body: " + gson.toJson(reqRev));
    }

    @When("Partner hit API Reversal IDSProject")
    public void partner_hit_api_reversal_ids_project() {
        Map<String, String> rowData = new HashMap<>();
        JSONObject strJson = new JSONObject(gson.toJson(reqRev));
        Iterator<String> keys = strJson.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String val = strJson.get(key).toString();
            rowData.put(key, val);
        }
        scenario.log("Url: " + urlSOAPIDSProj);
        String reqSoapInq = soapHelp.buildIDSProjSoapRequestIDM(rowData, "reversal");
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

                JsonNode kReturn = rootNode.path("S:Envelope").path("S:Body").path("ns2:reversalResponse").path("return");
                resRev = gson.fromJson(kReturn.toString(), ResReversal.class);
                System.out.println("after convert: " + gson.toJson(resPay));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resRev.setResponseCode("05");
            resRev.setResponseDesc("ERROR - General Error");
        }
    }

    @Then("Partner gets response {string} and response desc {string} API Reversal IDSProject")
    public void partner_gets_response_and_response_desc_api_reversal_ids_project(String rc, String rcDesc) {
        assertEquals(resRev.getResponseCode(), rc);
        assertEquals(resRev.getResponseDesc(), rcDesc);
        scenario.log("Res Body: " + gson.toJson(resRev));
    }
}
