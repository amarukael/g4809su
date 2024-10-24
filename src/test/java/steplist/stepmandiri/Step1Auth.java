package steplist.stepmandiri;

import com.google.gson.Gson;
import constant.ConstantMandiri;
import helper.APIHelper;
import helper.MandiriHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.mandiri.auth.AuthReq;
import model.mandiri.auth.AuthRes;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step1Auth {
    Scenario scenario = Hooks.scenario;
    Gson gson = new Gson();
    Gson gsonLog = new Gson();
    MandiriHelper mandiriHelper = new MandiriHelper();

    public static Map<String, AuthRes> lresAuth = new HashMap<String, AuthRes>();
    private static AuthReq reqAuth;
    private static AuthRes resAuth;
    private static Map<String, String> headersAuth = new HashMap<>();
    private static String tmpHttpCode;

    private static String urlAuth = ConstantMandiri.urlMandiri + ConstantMandiri.urlMandiriToken;



    @Before("@auth_sukses")
    public void doingWait(){
        try {
            Thread.sleep(1500);

            lresAuth.clear();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("Valid Request API Authorization TOKEN")
    public void valid_request_api_authorization_token() throws InterruptedException {
        AuthReq req = gson.fromJson("{\"grantType\":\"client_credentials\"}", AuthReq.class);
        reqAuth = req;
        headersAuth = mandiriHelper.getHeaderMandiriToken("","");

        scenario.log("Headers: " + gsonLog.toJson(headersAuth));
        scenario.log("Req Body: " + gsonLog.toJson(reqAuth));
    }

    @Given("Invalid Request {string} with {string} API Authorization TOKEN")
    public void invalid_request_api_authorization_token(String param, String condition) throws InterruptedException {
        AuthReq req = gson.fromJson("{\"grantType\":\"client_credentials\"}", AuthReq.class);
        if(param.equals("grantType")){
            if(condition.equals("null")){
                req = new AuthReq("");
            }else if(condition.equals("unsupported")){
                req = new AuthReq("unsupported");
            }
        }

        reqAuth = req;
        headersAuth = mandiriHelper.getHeaderMandiriToken(param,condition);

        scenario.log("Headers: " + gsonLog.toJson(headersAuth));
        scenario.log("Req Body: " + gsonLog.toJson(reqAuth));
    }

    @When("Mandiri hits API Authorization TOKEN")
    public void mandiri_hits_api_authorization_token() {
        AuthRes res = new AuthRes();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqAuth), urlAuth
                , "application/json", headersAuth, 1, 20000);
        if (resApi.getRc().equals("00")) {
            res = gson.fromJson(resApi.getDataJson(), AuthRes.class);
            resAuth = res;
            tmpHttpCode = resApi.getHttpCode();
        } else {
            if (resApi.getDataJson() == null) {
                res.setResponseCode("4007300");
                res.setResponseMessage("Bad Request");
                resAuth = res;
                tmpHttpCode = "400";
            } else {
                res = gson.fromJson(resApi.getDataJson(), AuthRes.class);
                resAuth = res;
                tmpHttpCode = resApi.getHttpCode();
            }
        }
    }

    @Then("Mandiri gets the Success response {string} and http code {string} API Authorization TOKEN")
    public void mandiri_gets_the_success_response_api_authorization_dims(String rc, String httpCode) {
        assertEquals(resAuth.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        lresAuth.put("SUCCESS", resAuth);
        scenario.log("Res Body: " + gsonLog.toJson(resAuth));
    }

    @Then("Mandiri gets the Failed response {string} and http code {string} API Authorization TOKEN")
    public void mandiri_gets_the_failed_response_api_authorization_token(String rc, String httpCode) {
        assertEquals(resAuth.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        scenario.log("Res Body: " + gsonLog.toJson(resAuth));
    }

}
