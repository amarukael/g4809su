package steplist.stepdims;

import com.google.gson.Gson;
import constant.ConstantDims;
import helper.APIHelper;
import helper.DimsHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import model.dims.authorization.ReqAuth;
import model.dims.authorization.ResAuth;
import model.ResUrl;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step1Auth {
    Gson gsonLog = new Gson();
    Gson gson = new Gson();
    DimsHelper dimsHelp = new DimsHelper();
    public static Map<String, ResAuth> lresAuth = new HashMap<String, ResAuth>();
    private static ResAuth resAuth;
    private static ReqAuth reqAuth;
    private static Map<String, String> headersAuth = new HashMap<>();
    private static String tmpHttpCode;
    private static String urlAuth = ConstantDims.urlDims + ConstantDims.UrlDimsToken;
    private static String tmpCondition = "";
    Scenario scenario = Hooks.scenario;

    @Before("@auth_sukses")
    public void doingWait(){
        try {
            Thread.sleep(1500);

            lresAuth.clear();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("Valid Request API Authorization DIMS")
    public void valid_request_api_authorization_dims() throws InterruptedException {
        ReqAuth req = new ReqAuth("client_credentials");
        reqAuth = req;
        headersAuth = dimsHelp.getHeaderToken("");

        scenario.log("Headers: " + gsonLog.toJson(headersAuth));
        scenario.log("Req Body: " + gsonLog.toJson(reqAuth));
    }

    @Given("Valid Request API Authorization DIMS with Partner {string}")
    public void valid_request_api_authorization_dims_with_partner(String partnerId) {
        tmpCondition = partnerId;
        ReqAuth req = new ReqAuth("client_credentials");
        reqAuth = req;
        headersAuth = dimsHelp.getHeaderToken(partnerId);

        scenario.log("Headers: " + gsonLog.toJson(headersAuth));
        scenario.log("Req Body: " + gsonLog.toJson(reqAuth));
    }

    @Given("Invalid Request {string} API Authorization DIMS")
    public void invalid_request_client_key_api_authorization_dims(String condition) {
        ReqAuth req = new ReqAuth("client_credentials");
        if (condition.equals("grantType")) req.setGrantType("");

        reqAuth = req;
        headersAuth = dimsHelp.getHeaderToken(condition);

        scenario.log("Headers: " + gsonLog.toJson(headersAuth));
        scenario.log("Req Body: " + gsonLog.toJson(reqAuth));
    }

    @When("Partner hits API Authorization DIMS")
    public void partner_hits_api_authorization_dims() {
        ResAuth res = new ResAuth();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqAuth), urlAuth
                , "application/json", headersAuth, 1, 20000);
        if (resApi.getRc().equals("00")) {
            res = gson.fromJson(resApi.getDataJson(), ResAuth.class);
            resAuth = res;
            tmpHttpCode = resApi.getHttpCode();
        } else {
            if (resApi.getDataJson() == null) {
                res.setResponseCode("4007301");
                res.setResponseMessage("Bad Request");
                resAuth = res;
                tmpHttpCode = "400";
            } else {
                res = gson.fromJson(resApi.getDataJson(), ResAuth.class);
                resAuth = res;
                tmpHttpCode = resApi.getHttpCode();
            }
        }
    }

    @Then("Partner gets the Success response {string} and http code {string} API Authorization DIMS")
    public void partner_gets_the_success_response_api_authorization_dims(String rc, String httpCode) {
        assertEquals(resAuth.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        if (!tmpCondition.isEmpty()) {
            lresAuth.put(tmpCondition, resAuth);
            tmpCondition = "";
        } else {
            lresAuth.put("SUCCESS", resAuth);
        }
        scenario.log("Res Body: " + gsonLog.toJson(resAuth));
    }

    @Then("Partner gets the Failed response {string} and http code {string} API Authorization DIMS")
    public void partner_gets_the_failed_response_api_authorization_dims(String rc, String httpCode) {
        assertEquals(resAuth.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        scenario.log("Res Body: " + gsonLog.toJson(resAuth));
    }
}
