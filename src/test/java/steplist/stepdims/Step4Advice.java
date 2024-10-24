package steplist.stepdims;

import com.google.gson.Gson;
import constant.ConstantDims;
import database.DimsDataHelper;
import helper.APIHelper;
import helper.DimsHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import model.ResUrl;
import model.dims.Amount;
import model.dims.advice.ReqAdvice;
import model.dims.advice.ResAdvice;
import model.dims.authorization.ResAuth;
import model.dims.payment.ResPayment;
import utility.AddFunction;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step4Advice {
    private static Map<String, ResAuth> lresAuth;
    private static Map<String, ResPayment> lresPay;
    private static String externalId;
    Gson gsonLog = new Gson();
    Gson gson = new Gson();
    DimsHelper dimsHelp = new DimsHelper();
    DimsDataHelper db = new DimsDataHelper();
    AddFunction addFunc = new AddFunction();
    Rand rand = new Rand();
    private static ResAuth resAuth;
    private static ResPayment resPay;
    private static ReqAdvice reqAdv;
    private static ResAdvice resAdv = new ResAdvice();
    private static Map<String, String> headers = new HashMap<>();
    private static String tmpHttpCode;
    private static String urlAdv = ConstantDims.urlDims + ConstantDims.urlDimsAdv;
    private static String relativeUrl = ConstantDims.urlDimsAdv;
    private static String secretKey = "";
    private static String tmpCondition = "";
    Scenario scenario = Hooks.scenario;

    @Before("@adv_sukses")
    public void setUpData(){
        lresAuth = Step1Auth.lresAuth;
        resAuth = lresAuth.get("SUCCESS");
        externalId = Step2Inquiry.externalId;
        lresPay = Step3Payment.lresPay;
        resPay = lresPay.get("SUCCESS");
//        secretKey = db.getSecretKeyByToken(resAuth.getAccessToken());
        secretKey = "ids123456";
    }

    @Given("Valid Request \\({string}, {string}) API Advice DIMS")
    public void valid_request_api_advice_dims(String currency, String amnt) {
        System.out.println("Delay Start Scenario");
        addFunc.getDelay(40000);
        System.out.println("Start Scenario");

        Amount amount = new Amount(currency, amnt);

        String tmpOriginalPartnerRefNo = resPay.getOriginalPartnerReferenceNo();
        String tmporiginalReferenceNo = resPay.getOriginalReferenceNo();
        ReqAdvice req = new ReqAdvice(tmpOriginalPartnerRefNo, tmporiginalReferenceNo, amount);
        reqAdv = req;

        headers = dimsHelp.getHeaderGlobal("", resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, externalId, secretKey);

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqAdv));
    }

    @Given("Valid Request \\({string}, {string}) API Advice DIMS with Partner {string}")
    public void valid_request_api_advice_dims_with_partner(String currency, String amnt, String partnerId) {
        tmpCondition = partnerId;
        secretKey = db.getSecretKeyByToken(Step1Auth.lresAuth.get(partnerId).getAccessToken());
        Amount amount = new Amount(currency, amnt);

        String tmpOriginalPartnerRefNo = "";
        String tmporiginalReferenceNo = "";
        if (Step3Payment.lresPay.containsKey(partnerId)) {
            tmpOriginalPartnerRefNo = Step3Payment.lresPay.get(partnerId).getOriginalPartnerReferenceNo();
            tmporiginalReferenceNo = Step3Payment.lresPay.get(partnerId).getOriginalReferenceNo();
        }
        ReqAdvice req = new ReqAdvice(tmpOriginalPartnerRefNo, tmporiginalReferenceNo, amount);
        reqAdv = req;

        headers = dimsHelp.getHeaderGlobal("", Step1Auth.lresAuth.get(partnerId).getAccessToken(), gson.toJson(req)
                , relativeUrl, Step2Inquiry.externalId, secretKey);

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqAdv));
    }

    @Given("Valid Request \\({string}, {string}) API Advice DIMS With Invalid header {string}")
    public void validRequestAPIAdviceDIMSWithInvalidHeader(String currency, String amnt, String condition) {
        Amount amount = new Amount(currency, amnt);

        String tmpOriginalPartnerRefNo = resPay.getOriginalPartnerReferenceNo();
        String tmporiginalReferenceNo = resPay.getOriginalReferenceNo();
        ReqAdvice req = new ReqAdvice(tmpOriginalPartnerRefNo, tmporiginalReferenceNo, amount);
        reqAdv = req;

        headers = dimsHelp.getHeaderGlobal(condition, resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, externalId, secretKey);

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqAdv));
    }

    @Given("Invalid Request param {string} \\({string}, {string}, {string}) API Advice DIMS")
    public void invalidRequestParamAPIAdviceDIMS(String condition, String descTest, String currency, String amnt) {
        if (condition.equals("originalPartnerReferenceNoNobu")) {
            System.out.println("Delay Start Scenario");
            addFunc.getDelay(50000);
            tmpCondition = condition;
            System.out.println("Start Scenario");
        }

        Amount amount = new Amount(currency, amnt);

        String tmpOriginalPartnerRefNo = resPay.getOriginalPartnerReferenceNo();
        if (condition.equals("originalPartnerReferenceNo") && descTest.equals("Empty")) {
            tmpOriginalPartnerRefNo = "";
        } else if (condition.equals("originalPartnerReferenceNo") && descTest.equals("Wrong")) {
            tmpOriginalPartnerRefNo = "99" + tmpOriginalPartnerRefNo;
        }

        String tmporiginalReferenceNo = resPay.getOriginalReferenceNo();
        if (condition.equals("originalReferenceNo") && descTest.equals("Empty")) {
            tmporiginalReferenceNo = "";
        } else if (condition.equals("originalReferenceNo") && descTest.equals("Wrong")) {
            tmporiginalReferenceNo = "99" + tmporiginalReferenceNo;
        }

        if (condition.equals("originalPartnerReferenceNoNobu") && descTest.equals("Wrong")) {
            tmpOriginalPartnerRefNo = "99" + tmpOriginalPartnerRefNo;
            tmporiginalReferenceNo = "99" + tmporiginalReferenceNo;
        }

        ReqAdvice req = new ReqAdvice(tmpOriginalPartnerRefNo, tmporiginalReferenceNo, amount);
        reqAdv = req;

        headers = dimsHelp.getHeaderGlobal("", resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, externalId, secretKey);

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqAdv));
    }

    @When("Partner hits API Advice DIMS")
    public void partner_hits_api_advice_dims() {
        ResAdvice res = new ResAdvice();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqAdv), urlAdv
                , "application/json", headers, 1, 20000);
        if (resApi.getRc().equals("00")) {
            res = gson.fromJson(resApi.getDataJson(), ResAdvice.class);
            resAdv = res;
            tmpHttpCode = resApi.getHttpCode();
        } else {
            if (resApi.getDataJson() == null) {
                res.setResponseCode("4001701");
                res.setResponseMessage("Bad Request");
                resAdv = res;
                tmpHttpCode = "400";
            } else {
                res = gson.fromJson(resApi.getDataJson(), ResAdvice.class);
                resAdv = res;
                tmpHttpCode = resApi.getHttpCode();
            }
        }
    }

    @Then("Partner gets the Success response {string} and http code {string} API Advice DIMS")
    public void partner_gets_the_success_response_and_http_code_api_advice_dims(String rc, String httpCode) {
        assertEquals(resAdv.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        scenario.log("Res Body: " + gsonLog.toJson(resAdv));
    }

    @Then("Partner gets the Failed response {string} and http code {string} API Advice DIMS")
    public void partnerGetsTheFailedResponseAndHttpCodeAPIAdviceDIMS(String rc, String httpCode) {
        if (tmpCondition.equals("originalPartnerReferenceNoNobu")) {
            System.out.println("Delay End Scenario");
            addFunc.getDelay(30000);
            System.out.println("End Scenario");
            tmpCondition = "";
        }

        assertEquals(resAdv.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        scenario.log("Res Body: " + gsonLog.toJson(resAdv));
    }
}
