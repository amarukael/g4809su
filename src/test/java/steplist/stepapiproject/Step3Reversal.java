package steplist.stepapiproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constant.ConstantAPIProject;
import helper.APIHelper;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.apiproject.*;
import org.json.JSONObject;
import org.json.XML;
import utility.AddFunction;
import utility.JSONtoURLEncode;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step3Reversal {
    Gson gson = new Gson();
    Helper help = new Helper();
    AddFunction addFunc = new AddFunction();
    ApiProjectHelper apiProjHelp = new ApiProjectHelper();
    public static Map<String, String> lresPay = new HashMap<>();
    public static Map<String, ResPaymentXml> lresPayXml = new HashMap<>();
    ReqReversal reqRev;
    ReqReversalXml reqRevXml;
    String resRev;
    ResReversalXml resRevXml;
    String urlReversal = ConstantAPIProject.urlAPIProj + ConstantAPIProject.urlAPIProjRev;
    String secretKey = ConstantAPIProject.secretKey;
    String urlReversalNonAlfa = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjRevNonAlfa;
    String secretKeyNonAlfa = ConstantAPIProject.secretKeyNonAlfa;
    private static String tmpCondition = "";
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;
    private static int flgSign = ConstantAPIProject.flgSign;

    @Before("@apiproj_rev_sukses or @apiproj_nonalfa_rev_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresPay = Step2Payment.lresPay;
            lresPayXml = Step2Payment.lresPayXml;
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}) API Reversal APIProject")
    public void valid_request_api_reversal_api_project(String custId, String productId) {
        String[] tmpResPay = lresPay.get(custId).split("\\|", -1);
        String agentId = tmpResPay[0].trim();
        String agentPin = tmpResPay[1].trim();
        String trxId = tmpResPay[2].trim();
        String storeId = tmpResPay[3].trim();
        String customerId = tmpResPay[4].trim();
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss").trim();
        String signature = apiProjHelp.getSignRev(agentId, agentPin, storeId, productId, customerId, trxId, dateTimeReq
                , secretKey);

        ReqReversal req = new ReqReversal(agentId, agentPin, trxId, storeId, productId, customerId, dateTimeReq
                , signature);
        reqRev = req;
        scenario.log("Req Body: " + gson.toJson(reqRev));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}) API Reversal APIProject")
    public void invalid_request_api_reversal_api_project(String custId, String productId
            , String param, String descTest) {
        tmpCondition = descTest;
        if (tmpCondition.equals("Invalid_TrackingRef")) {
            System.out.println("Start Delay Scenario...");
            help.delay(45000);
            System.out.println("End Delay Scenario...");
        }

        String[] tmpResPay = lresPay.get(custId).split("\\|", -1);
        String agentId = tmpResPay[0].trim();
        String agentPin = tmpResPay[1].trim();
        String trxId = tmpResPay[2].trim();
        String storeId = tmpResPay[3].trim();
        String customerId = tmpResPay[4].trim();
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss").trim();

        if (tmpCondition.equals("Invalid_TrackingRef")) trxId = trxId + "1";

        String signature = apiProjHelp.getSignRev(agentId, agentPin, storeId, productId, customerId, trxId, dateTimeReq
                , secretKey);

        ReqReversal req = new ReqReversal(agentId, agentPin, trxId, storeId, productId, customerId, dateTimeReq
                , signature);
        reqRev = req;
        scenario.log("Req Body: " + gson.toJson(reqRev));
    }

    @Given("Valid Request \\({string}, {string}) API Reversal APIProject Non Alfa")
    public void valid_request_api_reversal_api_project_non_alfa(String custId, String productId) {
        ResPaymentXml tmpResPay = lresPayXml.get(custId);
        String partnerId = tmpResPay.getPartnerid();
        String terminalId = tmpResPay.getTerminalid();
        String trackingRef = tmpResPay.getTrackingref();
        String trxId = tmpResPay.getTrxid();
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjHelp.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId
                , custId, "", trackingRef, terminalId, secretKeyNonAlfa, flgSign);

        ReqReversalXml req = new ReqReversalXml(trxId, dateTimeReq, partnerId, productId, custId, trackingRef
                , terminalId, signature);
        reqRevXml = req;
        scenario.log("Req Body: " + gson.toJson(reqRevXml));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}) API Reversal APIProject Non Alfa")
    public void invalid_request_api_reversal_api_project_non_alfa(String custId, String productId
            , String param, String descTest) {
        tmpCondition = descTest;
        if (tmpCondition.equals("Invalid_TrackingRef")) {
            System.out.println("Start Delay Scenario...");
            help.delay(45000);
            System.out.println("End Delay Scenario...");
        }

        ResPaymentXml tmpResPay = lresPayXml.get(custId);
        String partnerId = tmpResPay.getPartnerid();
        String terminalId = tmpResPay.getTerminalid();
        String trackingRef = tmpResPay.getTrackingref();
        String trxId = tmpResPay.getTrxid();
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");

        if (descTest.equals("invalid_trackingRef"))
            trackingRef = trackingRef + "1";

        String signature = apiProjHelp.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId
                , custId, "", trackingRef, terminalId, secretKeyNonAlfa, flgSign);

        ReqReversalXml req = new ReqReversalXml(trxId, dateTimeReq, partnerId, productId, custId, trackingRef
                , terminalId, signature);
        reqRevXml = req;
        scenario.log("Req Body: " + gson.toJson(reqRevXml));
    }

    @When("Partner hit API Reversal APIProject")
    public void partner_hit_api_reversal_api_project() {
        String res = "";
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqRev));
        String paramString = jr.jsonToURLEncoding(json);

        ResUrl resApi = APIHelper.getHitAPIMethodGet(scenario, "", paramString, urlReversal
                , "", null, 0, 20000, 1);
        if (resApi.getDataJson() == null) {
            res = "|||||||||||05|ERROR - General Error|||||";
            resRev = res;
        } else {
            res = resApi.getDataJson();
            resRev = res;
        }
    }

    @When("Partner hit API Reversal APIProject Non Alfa")
    public void partner_hit_api_reversal_api_project_non_alfa() {
        ResReversalXml res = new ResReversalXml();
        Reqdata<ReqReversalXml> rd = new Reqdata<ReqReversalXml>();
        rd.setdata(reqRevXml);

        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", xml, urlReversalNonAlfa
                , "application/xml", null, 0, 30000);
        if (resApi.getRc().equals("00")) {
            Type tk = new TypeToken<Reqdata<ResReversalXml>>() {}.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getDataJson());
            Reqdata<ResReversalXml> rt = gson.fromJson(jsonRes.toString(),tk) ;

            res = rt.getdata();
        } else {
            res.setRc("5");
            res.setRcdesc("Terjadi gangguan di sisi biller");
        }
        resRevXml = res;
    }

    @Then("Partner gets response {string} and response desc {string} API Reversal APIProject")
    public void partner_gets_response_and_response_desc_api_reversal_api_project(String rc, String desc) {
        String[] tmpResRev = resRev.split("\\|", -1 );
        String resRc = tmpResRev[6];
        String resRcDesc = tmpResRev[7];
        assertEquals(resRc, rc);
        assertEquals(resRcDesc, desc);
        scenario.log("Res Body: " + resRev);
    }

    @Then("Partner gets response {string} and response desc {string} API Reversal APIProject Non Alfa")
    public void partner_gets_response_and_response_desc_api_reversal_api_project_non_alfa(String rc, String desc) {
        assertEquals(resRevXml.getRc(), rc);
        assertEquals(resRevXml.getRcdesc(), desc);
        scenario.log("Res Body: " + gson.toJson(resRevXml));
    }
}
