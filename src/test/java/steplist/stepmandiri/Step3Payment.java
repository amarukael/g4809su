package steplist.stepmandiri;

import com.google.gson.Gson;

import constant.ConstantMandiri;

import helper.APIHelper;
import helper.CaseHelper;
import helper.Helper;
import helper.MandiriHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.mandiri.auth.AuthRes;
import model.mandiri.inquiry.res.ResInq;
import model.mandiri.payment.ReqPayment;
import model.mandiri.payment.res.ResPayment;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step3Payment {
    public static Map<String, AuthRes> lresAuth;

    Gson gsonLog = new Gson();
    Gson gson = new Gson();

    MandiriHelper mandiriHelper = new MandiriHelper();

    Rand rand = new Rand();


    public static Map<String, ResPayment> lresPayment = new HashMap<String, ResPayment>();

    public static Map<String, ResInq> lresInq = new HashMap<>();

    private static Map<String, String> header = new HashMap<>();

    public static ReqPayment REQPAYMENT;

    public static ResPayment RESPAYMENT;

    private static AuthRes resAuth;

    private static ResInq resInq;
    private static String tmpHttpCode;


    private static final String urlPayment = ConstantMandiri.urlMandiri + ConstantMandiri.urlMandiriPay;
    private static final String relativeUrl = ConstantMandiri.urlMandiriPay;

    private static final String secretKey = ConstantMandiri.secretKey;


    Scenario scenario = Hooks.scenario;

    CaseHelper caseHelper = new CaseHelper();

    @Before("@payment_success")
    public void setUpData(){
        lresAuth = Step1Auth.lresAuth;
        resAuth = lresAuth.get("SUCCESS");

        lresInq = Step2Inquiry.lresInq;
        resInq = lresInq.get("SUCCESS");


    }

    @Given("Valid Request \\({string}) API Payment")
    public void validRequestAPIInquiry(String reqBody) throws Exception {
        ReqPayment req = reConstructReq(gson.fromJson(reqBody, ReqPayment.class), "","","");
        REQPAYMENT = req;
        String xExternalId =  rand.getExternalID(1);

        scenario.log("Req Body:  " + req);
//        scenario.log("Req Body: " + gson.toJson(req));
        header = mandiriHelper.getMandiriGlobalHeader("","", resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, xExternalId, secretKey);

        scenario.log("Header: " + gson.toJson(header));

    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}) API Payment")
    public void invalidRequestAPIInquiry(String reqBody, String attribute, String descTest, String params) throws Exception {
        ReqPayment req = reConstructReq(gson.fromJson(reqBody, ReqPayment.class), attribute, descTest , params);
        REQPAYMENT = req;
        String xExternalId =  rand.getExternalID(0);

        header = mandiriHelper.getMandiriGlobalHeader(
                "",
                "",
                resAuth.getAccessToken(),
                gson.toJson(req)
                , relativeUrl,
                xExternalId,
                secretKey
        );

        scenario.log("Headers: " + gson.toJson(header));
        scenario.log("Req Body: " + gson.toJson(REQPAYMENT));
    }

    @When("Mandiri hits API Payment VA")
    public void mandiriHitsAPIInquiryVA() {
        ResPayment res = new ResPayment();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(REQPAYMENT), urlPayment
                , "application/json", header, 1, 10000000);
        if (resApi.getRc().equals("00")) {
            res = gson.fromJson(resApi.getDataJson(), ResPayment.class);
            RESPAYMENT = res;
            tmpHttpCode = resApi.getHttpCode();
        } else {
            if (resApi.getDataJson() == null) {
                res.setResponseCode("4001501");
                res.setResponseMessage("Bad Request");
                RESPAYMENT = res;
                tmpHttpCode = "400";
            } else {
                res = gson.fromJson(resApi.getDataJson(), ResPayment.class);
                RESPAYMENT = res;
                tmpHttpCode = resApi.getHttpCode();
            }
        }
    }

    @Then("Mandiri gets the Success response {string} and http code {string} API Payment VA")
    public void mandiriGetsTheSuccessResponseAndHttpCodeAPIInquiryVA(String rc, String httpCode) {
        assertEquals(RESPAYMENT.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        lresPayment.put("SUCCESS", RESPAYMENT);
        scenario.log("Res Body: " + gsonLog.toJson(RESPAYMENT));
    }

    private ReqPayment reConstructReq(ReqPayment reqData, String attribute, String sCase, String params) throws Exception {
        // reconstruct
        reqData.setPaymentRequestId(resInq.getVirtualAccountData().getInquiryRequestId());
        reqData.setTrxDateTime(Helper.createDate("yyyy-MM-dd'T'HH:mm:ssXXX"));
        reqData.setReferenceNo(rand.randStrNum(20));

        String tmpCase = attribute + sCase;
        if(!tmpCase.trim().isEmpty()){
            scenario.log("Set " + attribute + " to " + sCase);
            caseHelper.executeCaseFunction(attribute, sCase, params, reqData);
        }

        return reqData;
    }
}
