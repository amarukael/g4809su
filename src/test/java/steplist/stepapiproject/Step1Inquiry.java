package steplist.stepapiproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constant.ConstantAPIProject;
import helper.APIHelper;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.apiproject.ReqInquiry;
import model.apiproject.ReqInquiryXml;
import model.apiproject.Reqdata;
import model.apiproject.ResInquiryXml;
import org.json.JSONObject;
import org.json.XML;
import utility.JSONtoURLEncode;
import utility.Rand;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step1Inquiry {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    ApiProjectHelper apiProjHelp = new ApiProjectHelper();
    public static Map<String, String> lresInq = new HashMap<>();
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
    private static String tmpCondition = "";
    private static int flgSign = ConstantAPIProject.flgSign;

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Inquiry APIProject")
    public void valid_request_api_inquiry_api_project(String agentId, String agentPin, String storeId
            , String productId, String customerId) {
        String trxId = ConstantAPIProject.environmentSvr + r.getExternalID(1);
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");
        String signature = apiProjHelp.getSignInq(agentId, agentPin, storeId, productId, customerId, trxId
                , dateTimeReq, secretKey);

        ReqInquiry req = new ReqInquiry(agentId, agentPin, trxId
                , storeId, productId, customerId, dateTimeReq, signature);
        reqInq = req;
        scenario.log("Req Body: " + gson.toJson(reqInq));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}) API Inquiry APIProject")
    public void invalid_request_api_inquiry_api_project(String agentId, String agentPin, String storeId
            , String productId, String customerId, String param, String descTest) throws InterruptedException {
        tmpCondition = descTest;
        if (tmpCondition.equals("Invalid_Signature_Supplier")) {
            System.out.println("Start delay");
            Thread.sleep(30000);
            System.out.println("Stop delay");
        }

        String trxId = ConstantAPIProject.environmentSvr + r.getExternalID(1);
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");
        if (descTest.equals("Double_TrackingReff")) trxId = "IDS2024091710000002";

        String signature = apiProjHelp.getSignInq(agentId, agentPin, storeId, productId, customerId, trxId
                , dateTimeReq, secretKey);
        if (tmpCondition.equals("Invalid_Signature")) signature = signature + "xx";

        ReqInquiry req = new ReqInquiry(agentId, agentPin, trxId
                , storeId, productId, customerId, dateTimeReq, signature);
        reqInq = req;
        scenario.log("Req Body: " + gson.toJson(reqInq));
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}) API Inquiry APIProject Non Alfa")
    public void valid_request_api_inquiry_api_project_non_alfa(String productId, String customerId, String partnerId
            , String terminalId) {
        String trackingRef = r.getExternalID(1);;
        String trxId = trackingRef;
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjHelp.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId
                , customerId, "", trackingRef, terminalId, secretKeyNonAlfa, flgSign);

        ReqInquiryXml req = new ReqInquiryXml(trxId, dateTimeReq, partnerId, productId, customerId
                , trackingRef, terminalId, signature);
        reqInqXml = req;
        scenario.log("Req Body: " + gson.toJson(reqInqXml));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}, {string}, {string}) API Inquiry APIProject Non Alfa")
    public void invalid_request_api_inquiry_api_project_non_alfa(String productId, String customerId, String partnerId
            , String terminalId, String param, String descTest) {
        tmpCondition = descTest;
        String trackingRef = r.getExternalID(1);;
        String trxId = trackingRef;
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjHelp.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId
                , customerId, "", trackingRef, terminalId, secretKeyNonAlfa, flgSign);

        if (tmpCondition.equals("Invalid_Signature")) signature = signature + "xx";

        ReqInquiryXml req = new ReqInquiryXml(trxId, dateTimeReq, partnerId, productId, customerId
                , trackingRef, terminalId, signature);
        reqInqXml = req;
        scenario.log("Req Body: " + gson.toJson(reqInqXml));
    }

    @When("Partner hit API Inquiry APIProject")
    public void partner_hit_api_inquiry_api_project() {
        String res = "";
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqInq));
        String paramString = jr.jsonToURLEncoding(json);

        ResUrl resApi = APIHelper.getHitAPIMethodGet(scenario, "", paramString, urlInquiry
                , "", null, 0, 40000, 1);
        if (resApi.getDataJson() == null) {
            res = "||||||05|ERROR - General Error|||||||||||";
            resInq = res;
        } else {
            res = resApi.getDataJson();
            resInq = res;
        }
    }

    @When("Partner hit API Inquiry APIProject Non Alfa")
    public void partner_hit_api_inquiry_api_project_non_alfa() {
        ResInquiryXml res = new ResInquiryXml();
        Reqdata<ReqInquiryXml> rd = new Reqdata<ReqInquiryXml>();
        rd.setdata(reqInqXml);

        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", xml, urlInquiryNonAlfa
                , "application/xml", null, 0, 30000);
        if (resApi.getRc().equals("00")) {
            Type tk = new TypeToken<Reqdata<ResInquiryXml>>() {}.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getDataJson());
            Reqdata<ResInquiryXml> rt = gson.fromJson(jsonRes.toString(),tk) ;

            res = rt.getdata();
        } else {
            res.setRc("5");
            res.setRcdesc("Terjadi gangguan di sisi biller");
        }
        resInqXml = res;
    }

    @Then("Partner gets response {string} and response desc {string} API Inquiry APIProject")
    public void partner_gets_response_and_response_desc_api_inquiry_api_project(String rc, String desc) throws InterruptedException {
        String[] tmpResInq = resInq.split("\\|", -1 );
        String resRc = tmpResInq[6];
        String resRcDesc = tmpResInq[7];
        assertEquals(resRc, rc);
        assertEquals(resRcDesc, desc);
        lresInq.put(tmpResInq[4], resInq);
        scenario.log("Res Body: " + resInq);

        if (tmpCondition.equals("signatureSupplier-Invalid_Signature_Supplier")) {
            System.out.println("Start delay");
            Thread.sleep(30000);
            System.out.println("Stop delay");
        }
    }

    @Then("Partner gets response {string} and response desc {string} API Inquiry APIProject Non Alfa")
    public void partner_gets_response_and_response_desc_api_inquiry_api_project_non_alfa(String rc, String desc) {
        assertEquals(resInqXml.getRc(), rc);
        assertEquals(resInqXml.getRcdesc(), desc);
        lresInqXml.put(resInqXml.getCustomerid(), resInqXml);
        scenario.log("Res Body: " + gson.toJson(resInqXml));
    }
}
