package steplist.stepapiproject;

import static org.testng.Assert.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.ResUrl;
import model.apiproject.ReqPayment;
import model.apiproject.ReqPaymentXml;
import model.apiproject.Reqdata;
import model.apiproject.ResInquiryXml;
import model.apiproject.ResPaymentXml;
import utility.AddFunction;
import utility.JSONtoURLEncode;
import utility.Rand;

public class Step2Payment {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    AddFunction addFunc = new AddFunction();
    ApiProjectHelper apiProjHelp = new ApiProjectHelper();
    private static Map<String, String> lresInq = new HashMap<>();
    private static Map<String, ResInquiryXml> lresInqXml = new HashMap<>();
    public static Map<String, String> lresPay = new HashMap<>();
    public static Map<String, ResPaymentXml> lresPayXml = new HashMap<>();
    ResInquiryXml resInquiryXml;
    ReqPayment reqPay;
    ReqPaymentXml reqPayXml;
    String resPay;
    ResPaymentXml resPayXml;
    String urlPayment = ConstantAPIProject.urlAPIProj + ConstantAPIProject.urlAPIProjPay;
    String secretKey = ConstantAPIProject.secretKey;
    String urlPaymentNonAlfa = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjPayNonAlfa;
    String secretKeyNonAlfa = ConstantAPIProject.secretKeyNonAlfa;
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;
    private static int flgSign = ConstantAPIProject.flgSign;

    @Before("@apiproj_pay_sukses or @apiproj_pay_param_failed " +
            "or @apiproj_nonalfa_pay_sukses or @apiproj_nonalfa_pay_param_failed")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresInq = Step1Inquiry.lresInq;
            lresPay.clear();
            lresInqXml = Step1Inquiry.lresInqXml;
            lresPayXml.clear();
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}) API Payment APIProject")
    public void valid_request_api_payment_api_project(String custId, String amount, String charge, String totalAmount,
            String adminFee, String productId, String descTest) {
        String[] tmpResInq = lresInq.get(custId).split("\\|", -1);
        String agentId = tmpResInq[0].trim();
        String agentPin = tmpResInq[1].trim();
        String trxId = tmpResInq[2].trim();
        String storeId = tmpResInq[3].trim();
        String customerId = tmpResInq[4].trim();
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss").trim();
        String paymentPeriod = tmpResInq[12].trim();

        if (descTest.equals("trackingReff_Diff"))
            trxId = ConstantAPIProject.environmentSvr + r.getExternalID(1);

        String signature = apiProjHelp.getSignPay(agentId, agentPin, storeId, productId, customerId, trxId, dateTimeReq,
                paymentPeriod, amount, charge, totalAmount, adminFee, secretKey);

        ReqPayment req = new ReqPayment(agentId, agentPin, trxId, storeId, productId, customerId, dateTimeReq,
                paymentPeriod, amount, charge, totalAmount, adminFee, signature);
        reqPay = req;
        scenario.log("Req Body: " + gson.toJson(reqPay));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}) API Payment APIProject")
    public void invalid_request_api_payment_api_project(String custId, String amount, String charge, String totalAmount,
            String adminFee, String productId, String param, String descTest) {
        String[] tmpResInq = lresInq.get(custId).split("\\|", -1);
        String agentId = tmpResInq[0];
        String agentPin = tmpResInq[1];
        String trxId = tmpResInq[2];
        String storeId = tmpResInq[3];
        String customerId = tmpResInq[4];
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");
        String paymentPeriod = tmpResInq[12];

        if (descTest.equals("Double_TrackingReff"))
            trxId = "IDS2024091710000002";

        String signature = apiProjHelp.getSignPay(agentId, agentPin, storeId, productId, customerId, trxId, dateTimeReq,
                paymentPeriod, amount, charge, totalAmount, adminFee, secretKey);

        ReqPayment req = new ReqPayment(agentId, agentPin, trxId, storeId, productId, customerId, dateTimeReq,
                paymentPeriod, amount, charge, totalAmount, adminFee, signature);
        reqPay = req;
        scenario.log("Req Body: " + gson.toJson(reqPay));
    }

    @Given("Valid Request \\({string}, {string}, {string}) API Payment APIProject Non Alfa")
    public void valid_request_api_payment_api_project_non_alfa(String custId, String totalAmount, String productId) {
        ResInquiryXml tmpResInq = lresInqXml.get(custId);
        String partnerId = tmpResInq.getPartnerid();
        String terminalId = tmpResInq.getTerminalid();
        String trackingRef = tmpResInq.getTrackingref();
        String trxId = tmpResInq.getTrxid();
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjHelp.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId, custId, totalAmount,
                trackingRef, terminalId, secretKeyNonAlfa, flgSign);

        ReqPaymentXml req = new ReqPaymentXml(trxId, dateTimeReq, partnerId, productId, custId, totalAmount,
                trackingRef, terminalId, signature);
        reqPayXml = req;
        scenario.log("Req Body: " + gson.toJson(reqPayXml));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}, {string}) API Payment APIProject Non Alfa")
    public void invalid_request_api_payment_api_project_non_alfa(String custId, String totalAmount, String productId,
            String param, String descTest) {
        if (descTest.equals("Double_TrackingRef")) {
            System.out.println("Delay Start Payment Scenario");
            addFunc.getDelay(30000);
            System.out.println("Start Payment Scenario");
        }

        ResInquiryXml tmpResInq = lresInqXml.get(custId);
        String partnerId = tmpResInq.getPartnerid();
        String terminalId = tmpResInq.getTerminalid();
        String trackingRef = tmpResInq.getTrackingref();
        String trxId = tmpResInq.getTrxid();
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjHelp.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId, custId, totalAmount,
                trackingRef, terminalId, secretKeyNonAlfa, flgSign);

        ReqPaymentXml req = new ReqPaymentXml(trxId, dateTimeReq, partnerId, productId, custId, totalAmount,
                trackingRef, terminalId, signature);
        reqPayXml = req;
        scenario.log("Req Body: " + gson.toJson(reqPayXml));
    }

    @When("Partner hit API Payment APIProject")
    public void partner_hit_api_payment_api_project() {
        String res = "";
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqPay));
        String paramString = jr.jsonToURLEncoding(json);

        ResUrl resApi = APIHelper.getHitAPIMethodGet(scenario, "", paramString, urlPayment, "", null, 0, 40000, 1);
        if (resApi.getDataJson() == null) {
            res = "|||||||||||05|ERROR - General Error|||||";
            resPay = res;
        } else {
            res = resApi.getDataJson();
            resPay = res;
        }
    }

    @When("Partner hit API Payment APIProject Non Alfa")
    public void partner_hit_api_payment_api_project_non_alfa() {
        ResPaymentXml res = new ResPaymentXml();
        Reqdata<ReqPaymentXml> rd = new Reqdata<ReqPaymentXml>();
        rd.setdata(reqPayXml);

        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", xml, urlPaymentNonAlfa, "application/xml", null, 0, 30000);
        if (resApi.getRc().equals("00")) {
            Type tk = new TypeToken<Reqdata<ResPaymentXml>>() {
            }.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getDataJson());
            Reqdata<ResPaymentXml> rt = gson.fromJson(jsonRes.toString(), tk);

            res = rt.getdata();
        } else {
            res.setRc("5");
            res.setRcdesc("Terjadi gangguan di sisi biller");
        }
        resPayXml = res;
    }

    @Then("Partner gets response {string} and response desc {string} API Payment APIProject")
    public void partner_gets_response_and_response_desc_api_payment_api_project(String rc, String desc) {
        String[] tmpResPay = resPay.split("\\|", -1);
        String resRc = tmpResPay[11];
        String resRcDesc = tmpResPay[12];
        assertEquals(resRc, rc);
        assertEquals(resRcDesc, desc);
        lresPay.put(tmpResPay[4], resPay);
        scenario.log("Res Body: " + resPay);
    }

    @Then("Partner gets response {string} and response desc {string} API Payment APIProject Non Alfa")
    public void partner_gets_response_and_response_desc_api_payment_api_project_non_alfa(String rc, String desc) {
        assertEquals(resPayXml.getRc(), rc);
        assertTrue(resPayXml.getRcdesc().contains(desc));
        lresPayXml.put(resPayXml.getCustomerid(), resPayXml);
        scenario.log("Res Body: " + gson.toJson(resPayXml));
    }

    @When("I perform payment with partnerid {string}, productid {string}, customerid {string}")
    public void i_perform_payment_with_partnerid_string_productid_string_customerid_string(String s, String s1,
            String s2) {
        lresInqXml = Step1Inquiry.lresInqXml;
        resInquiryXml = lresInqXml.get("last");
        String rc = resInquiryXml.getRc().toString();
        if (!rc.equals("00")) {
            scenario.log("Skipping this step");
            return;
        }
        long unixtime = System.currentTimeMillis() / 1000L;
        String trxId = "QA" + unixtime;
        String trackingRef = resInquiryXml.getTrackingref();
        String totalAmount = resInquiryXml.getTotalamount();

        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjHelp.getSignNonAlfa(s, "old");

        ReqPaymentXml reqPaymentXml = new ReqPaymentXml(trxId, dateTimeReq, s, s1, s2, totalAmount, trackingRef,
                resInquiryXml.getTerminalid(), signature);

        reqPayXml = reqPaymentXml;

        scenario.log(reqPaymentXml.toXml());
    }

    @Then("I got response payment with expected rc {string}")
    public void i_got_response_inquiry_with_expected_rc(String s) {
        lresInqXml = Step1Inquiry.lresInqXml;
        resInquiryXml = lresInqXml.get("last");
        String rc = resInquiryXml.getRc().toString();
        if (!rc.equals("00")) {
            scenario.log("Skipping this step");
            return;
        }
        Response response = RestAssured.given()
                .contentType("application/xml")
                .body(reqPayXml.toXml())
                .post("https://ids-fe1.com/APIProject/webresources/pay");

        String data = response.asPrettyString();
        ResPaymentXml resPay = new ResPaymentXml();
        scenario.log("Response Body: \n" + response.asPrettyString());

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));

            NodeList nodeList = document.getElementsByTagName("data");
            if (nodeList.getLength() > 0) {
                Element dataElement = (Element) nodeList.item(0);

                resPay.setTrxid(getElementValue(dataElement, "trxid"));
                resPay.setTrxdate(getElementValue(dataElement, "trxdate"));
                resPay.setPartnerid(getElementValue(dataElement, "partnerid"));
                resPay.setProductid(getElementValue(dataElement, "productid"));
                resPay.setCustomerid(getElementValue(dataElement, "customerid"));
                resPay.setTotalamount(getElementValue(dataElement, "totalamount"));
                resPay.setAdditionaldata(getElementValue(dataElement, "additionaldata"));
                resPay.setRc(getElementValue(dataElement, "rc"));
                resPay.setRcdesc(getElementValue(dataElement, "rcdesc"));
                resPay.setTrackingref(getElementValue(dataElement, "trackingref"));
                resPay.setTerminalid(getElementValue(dataElement, "terminalid"));
                resPay.setSignature(getElementValue(dataElement, "signature"));

                Assert.assertEquals(s, getElementValue(dataElement, "rc"));
            }

        } catch (Exception e) {
            scenario.log(e.toString());
            e.printStackTrace();

        }
    }

    private String getElementValue(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}
