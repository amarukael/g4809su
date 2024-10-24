package steplist.stepdims;

import com.google.gson.Gson;
import constant.ConstantDims;
import database.DimsDataHelper;
import helper.APIHelper;
import helper.DimsHelper;
import helper.Helper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import model.dims.authorization.ResAuth;
import model.dims.inquiry.ReqInquiry;
import model.dims.inquiry.ResInquiry;
import model.ResUrl;
import utility.AddFunction;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step2Inquiry {
    private static Map<String, ResAuth> lresAuth;
    Gson gsonLog = new Gson();
    Gson gson = new Gson();
    DimsHelper dimsHelp = new DimsHelper();
    DimsDataHelper db = new DimsDataHelper();
    Helper h = new Helper();
    Rand rand = new Rand();
    AddFunction addFunc = new AddFunction();
    public static Map<String, ResInquiry> lresInq = new HashMap<String, ResInquiry>();
    public static String externalId;
    private static ResAuth resAuth;
    private static ReqInquiry reqInq;
    private static ResInquiry resInq;
    private static Map<String, String> headers = new HashMap<>();
    private static String tmpHttpCode;
    private static String urlInq = ConstantDims.urlDims + ConstantDims.urlDimsInq;
    private static String relativeUrl = ConstantDims.urlDimsInq;
    private static String secretKey = "";
    private static String tmpCondition = "";
    private static String tmpAccountNo = "";
    private static int tmpFlgDelay = 0;
    Scenario scenario = Hooks.scenario;

    @Before("@inq_sukses or @inq_header_failed or @inq_param_failed " +
            "or @inq_sukses_pay_failed or @inq_sukses_disbursement_type")
    public void setUpData(){
        if (resAuth == null) {
            lresAuth = Step1Auth.lresAuth;
            resAuth = lresAuth.get("SUCCESS");
            lresInq.clear();
//            secretKey = db.getSecretKeyByToken(resAuth.getAccessToken());
            secretKey = "ids123456";
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Account Inquiry DIMS")
    public void valid_request_api_account_inquiry_dims(String productId, String bankCode, String accountNm
            , String accountNo, String catagoryPurchCode) {
        String tmpOriginalPartnerRefNo = rand.randStrNum(15);
        ReqInquiry req = new ReqInquiry(productId, bankCode, accountNm, accountNo,
                tmpOriginalPartnerRefNo, catagoryPurchCode);
        reqInq = req;

        headers = dimsHelp.getHeaderGlobal("", resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, "112344000112", secretKey);
        externalId = headers.get("X-EXTERNAL-ID");

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqInq));
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Account Inquiry DIMS with Partner {string}")
    public void validRequestAPIAccountInquiryDIMSWithPartner(String productId, String bankCode, String accountNm
            , String accountNo, String catagoryPurchCode, String partnerId) {
        tmpCondition = partnerId;
        secretKey = db.getSecretKeyByToken(Step1Auth.lresAuth.get(partnerId).getAccessToken());

        String tmpOriginalPartnerRefNo = rand.randStrNum(15);
        ReqInquiry req = new ReqInquiry(productId, bankCode, accountNm, accountNo,
                tmpOriginalPartnerRefNo, catagoryPurchCode);
        reqInq = req;

        headers = dimsHelp.getHeaderGlobal("", Step1Auth.lresAuth.get(partnerId).getAccessToken(), gson.toJson(req)
                , relativeUrl, rand.getExternalID(2), secretKey);
        externalId = headers.get("X-EXTERNAL-ID");

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqInq));
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Account Inquiry DIMS With " +
            "Invalid header {string}")
    public void valid_request_api_account_inquiry_dims_with_invalid_header_authorization(String productId
            , String bankCode, String accountNm, String accountNo, String catagoryPurchCode, String condition) {
        String tmpOriginalPartnerRefNo = rand.randStrNum(15);
        ReqInquiry req = new ReqInquiry(productId, bankCode, accountNm, accountNo,
                tmpOriginalPartnerRefNo, catagoryPurchCode);
        reqInq = req;

        String externalId = rand.getExternalID(2);
        headers = dimsHelp.getHeaderGlobal(condition, resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, externalId, secretKey);

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqInq));
    }

    @Given("Invalid Request param {string} \\({string}, {string}, {string}, {string}, {string}, {string}) " +
            "API Account Inquiry DIMS")
    public void invalid_request_param_product_id_api_account_inquiry_dims(String condition, String descTest
            , String productId, String bankCode, String accountNm, String accountNo, String catagoryPurchCode) {
        if (condition.equals("authNobu") || condition.equals("authPermata")
                || condition.equals("authDanamon")
                || condition.equals("unAuthHeaderInq") || condition.equals("invalidSignInq")) {
            System.out.println("Delay Start Scenario");
            addFunc.getDelay(25000);
            tmpCondition = condition;
            tmpFlgDelay = 1;
            System.out.println("Start Scenario");
        }

        String tmpOriginalPartnerRefNo = rand.randStrNum(15);
        if (condition.equals("originalPartnerReferenceNo") && descTest.equals("Empty"))
            tmpOriginalPartnerRefNo = "";
//        else if (condition.equals("partnerReference"))
//            tmpOriginalPartnerRefNo = lresInq.get("SUCCESS").getOriginalPartnerReferenceNo();

        ReqInquiry req = new ReqInquiry(productId, bankCode, accountNm, accountNo,
                tmpOriginalPartnerRefNo, catagoryPurchCode);
        reqInq = req;

        headers = dimsHelp.getHeaderGlobal("", resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, rand.getExternalID(2), secretKey);

        scenario.log("Headers: " + gsonLog.toJson(headers));
        scenario.log("Req Body: " + gsonLog.toJson(reqInq));
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}) API Account Inquiry DIMS")
    public void valid_request_api_account_inquiry_dims(String param, String descTest, String productId, String bankCode, String accountNm
            , String accountNo, String catagoryPurchCode) {
        tmpCondition = param + "_" + descTest;
        if (tmpCondition.equals("bankCode_Inter") || tmpCondition.equals("bankCode_Intra")) {
            System.out.println("Start Delay Scenario...");
            h.delay(60000);
            System.out.println("End Delay Scenario...");
        }

        valid_request_api_account_inquiry_dims(productId, bankCode, accountNm, accountNo, catagoryPurchCode);
    }

    @When("Partner hits API Account Inquiry DIMS")
    public void partner_hits_api_account_inquiry_dims() {
        ResInquiry res = new ResInquiry();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqInq), urlInq
                , "application/json", headers, 1, 40000);
        if (resApi.getRc().equals("00")) {
            res = gson.fromJson(resApi.getDataJson(), ResInquiry.class);
            resInq = res;
            tmpHttpCode = resApi.getHttpCode();
        } else {
            if (resApi.getDataJson() == null) {
                res.setResponseCode("4001501");
                res.setResponseMessage("Bad Request");
                resInq = res;
                tmpHttpCode = "400";
            } else {
                res = gson.fromJson(resApi.getDataJson(), ResInquiry.class);
                resInq = res;
                tmpHttpCode = resApi.getHttpCode();
            }
        }
    }

    @Then("Partner gets the Success response {string} and http code {string} API Account Inquiry DIMS")
    public void partner_gets_the_success_response_api_account_inquiry_dims(String rc, String httpCode) {
        assertEquals(resInq.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        if (!tmpCondition.isEmpty()) {
            if (tmpCondition.equals("IDS") || tmpCondition.equals("DMY")
                    || tmpCondition.equals("bankCode_Inter") || tmpCondition.equals("bankCode_Intra")
                    || tmpCondition.equals("xEternalID_conflict_1") || tmpCondition.equals("xEternalID_conflict_2")) {
                lresInq.put(tmpCondition, resInq);
            } else {
                if (tmpAccountNo.isEmpty())
                    lresInq.put(resInq.getAccountNo(), resInq);
                else
                    lresInq.put(tmpAccountNo, resInq);
            }

            tmpCondition = "";
        } else {
            lresInq.put("SUCCESS", resInq);
        }

        scenario.log("Res Body: " + gsonLog.toJson(resInq));
    }

    @Then("Partner gets the Failed response {string} and http code {string} API Account Inquiry DIMS")
    public void partner_gets_the_failed_response_api_account_inquiry_dims(String rc, String httpCode) {
        if (tmpCondition.equals("authNobu") || tmpCondition.equals("authPermata")
                || tmpCondition.equals("authDanamon")
                || tmpCondition.equals("unAuthHeaderInq") || tmpCondition.equals("invalidSignInq")) {
            System.out.println("Delay End Scenario");
            addFunc.getDelay(25000);
            System.out.println("End Scenario");
            tmpCondition = "";
        }

        scenario.log("Res Body: " + gsonLog.toJson(resInq));
        assertEquals(resInq.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
    }
}
