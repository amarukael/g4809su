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
import model.mandiri.inquiry.ReqInq;
import model.mandiri.inquiry.res.ResInq;
import utility.Rand;


import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step2Inquiry {

    public static Map<String, AuthRes> lresAuth;

    Gson gsonLog = new Gson();
    Gson gson = new Gson();

    MandiriHelper mandiriHelper = new MandiriHelper();

    Rand rand = new Rand();


    public static Map<String, ResInq> lresInq = new HashMap<String, ResInq>();

    private static Map<String, String> header = new HashMap<String,String>();

    public static ReqInq REQINQ;

    public static ResInq RESINQ;

    private static AuthRes resAuth;
    private static String tmpHttpCode;


    private static final String urlInq = ConstantMandiri.urlMandiri + ConstantMandiri.urlMandiriInq;
    private static final String relativeUrl = ConstantMandiri.urlMandiriInq;

    private static final String secretKey = ConstantMandiri.secretKey;


    CaseHelper caseHelper = new CaseHelper();

    Scenario scenario = Hooks.scenario;

    @Before("@inq_success")
    public void setUpData(){
        if(resAuth == null){
            lresAuth = Step1Auth.lresAuth;
            resAuth = lresAuth.get("SUCCESS");
            lresInq.clear();
        }

    }

    @Given("Valid Request \\({string}) API Inquiry")
    public void validRequestAPIInquiry(String reqBody) throws Exception {
        ReqInq req = reConstructReq(gson.fromJson(reqBody, ReqInq.class), "","","");
        REQINQ = req;
        String xExternalId =  rand.getExternalID(1);
        header = mandiriHelper.getMandiriGlobalHeader(
                    "",
                    "",
                    resAuth.getAccessToken(),
                    gson.toJson(req)
                    ,relativeUrl,
                    xExternalId,
                    secretKey
                );

        scenario.log("Header: " + gson.toJson(header));
        scenario.log("Req Body: " + gson.toJson(req));

    }


    @Given("Invalid Request \\({string}, {string}, {string}, {string}) API Inquiry")
    public void invalidRequestAPIInquiry(String reqBody, String attribute, String descTest, String params) throws Exception {
        ReqInq req = reConstructReq(gson.fromJson(reqBody, ReqInq.class), attribute, descTest , params);
        REQINQ = req;
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
        scenario.log("Req Body: " + gson.toJson(REQINQ));
    }

    @When("Mandiri hits API Inquiry VA")
    public void mandiriHitsAPIInquiryVA() {
        ResInq res = new ResInq();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(REQINQ), urlInq
                , "application/json", header, 1, 10000000);
        scenario.log(resApi.getRc());
        if (resApi.getRc().equals("00")) {
            scenario.log("Success get Response");
            res = gson.fromJson(resApi.getDataJson(), ResInq.class);
            RESINQ = res;
            tmpHttpCode = resApi.getHttpCode();
        } else {
            if (resApi.getDataJson() == null) {
                scenario.log("No Response");
                res.setResponseCode("4001501");
                res.setResponseMessage("Bad Request");
                RESINQ = res;
                tmpHttpCode = "400";
            } else {
                scenario.log("Other Case");
                res = gson.fromJson(resApi.getDataJson(), ResInq.class);
                RESINQ = res;
                tmpHttpCode = resApi.getHttpCode();
            }
        }
    }


    @Then("Mandiri gets the Success response {string} and http code {string} API Inquiry VA")
    public void mandiriGetsTheSuccessResponseAndHttpCodeAPIInquiryVA(String rc, String httpCode) {
        assertEquals(RESINQ.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        lresInq.put("SUCCESS", RESINQ);
        scenario.log("Res Body: " + gsonLog.toJson(RESINQ));
    }

    @Then("Mandiri gets the Failed response {string} and http code {string} API Inquiry VA")
    public void mandiriGetsTheFailedResponseAndHttpCodeAPIInquiryVA(String rc, String httpCode) {
        scenario.log("Res Body: " + gsonLog.toJson(RESINQ));
        assertEquals(RESINQ.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
    }


    private ReqInq reConstructReq(ReqInq reqData, String attribute, String sCase, String params) throws Exception {
        ReqInq req = reqData;
        // reconstruct
        req.setInquiryRequestId(rand.randStrNum(20));
        req.setTrxDateInit(Helper.createDate("yyyy-MM-dd'T'HH:mm:ssXXX"));

        String tmpCase = attribute + sCase;
        if(!tmpCase.trim().isEmpty()){
            scenario.log("Set " + attribute + " to " + sCase);
            caseHelper.executeCaseFunction(attribute, sCase, params, req);
        }

        return req;
    }


    @Given("Given Valid Request \\({string}) API Inquiry With Invalid header {string} - {string}")
    public void givenValidRequestAPIInquiryWithInvalidHeader(String reqBody, String params,String condition) throws Exception {
        ReqInq req = reConstructReq(gson.fromJson(reqBody, ReqInq.class), "","","");
        REQINQ = req;
        String xExternalId =  rand.getExternalID(1);
        header = mandiriHelper.getMandiriGlobalHeader(
                params,
                condition,
                resAuth.getAccessToken(),
                gson.toJson(req)
                ,relativeUrl,
                xExternalId,
                secretKey
        );

        scenario.log("Header: " + gson.toJson(header));
        scenario.log("Req Body: " + gson.toJson(req));

    }


}


