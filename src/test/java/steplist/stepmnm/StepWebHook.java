package steplist.stepmnm;

import com.google.gson.Gson;
import constant.ConstantMnM;
import database.MandMDataHelper;
import helper.APIHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import model.ResUrl;
import model.mnm.webhook.webhook.ReqWebhook;
import utility.Rand;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class StepWebHook {
    Gson gson = new Gson();
    MandMDataHelper db = new MandMDataHelper();
    Rand r = new Rand();
    private static ReqWebhook reqWebhook;
    private static Map<String, String> resDataMessage;
    private static String tmpHttpCode;
    private static String tmpGetParam;
    private static String tmpResGetParam;
    private static String tmpType;
    private static String urlWebHook = ConstantMnM.urlMandM + ConstantMnM.UrlWebHookMandM;
    Scenario scenario = Hooks.scenario;

    @Given("Req valid data API WebHook M&M with Method GET")
    public void req_valid_data_api_web_hook_m_m_with_method_get() {
        tmpGetParam= "?hub.challenge=" + r.randStrNum(3);
        scenario.log("Req Body: " + tmpGetParam);
    }

    @Given("Req valid data\\({string}, {string}) Api WebHook M&M")
    public void req_valid_data_api_web_hook_m_m(String type, String data) {
        tmpType = type;
        ReqWebhook req = gson.fromJson(data, ReqWebhook.class);
        if (type.equals("reply_not_interest") || type.equals("reply_interest")) {
            resDataMessage = db.getMessageStatAndRespCustByMessageId(req.getEntry().get(0).getChanges().get(0)
                    .getValue().getMessages().get(0).getContext().getId());
        } else {
            resDataMessage = db.getMessageStatAndRespCustByMessageId(req.getEntry().get(0).getChanges().get(0)
                    .getValue().getStatuses().get(0).getId());
        }

        reqWebhook = req;
        scenario.log("Status Before: " + resDataMessage.get("status"));
        scenario.log("Resp Cust Before: " + resDataMessage.get("respSupplier"));
        scenario.log("Req Body: " + tmpGetParam);
    }

    @Given("Req invalid data\\({string}, {string}) Api WebHook M&M")
    public void req_invalid_data_api_web_hook_m_m(String type, String data) {
        tmpType = type;
        ReqWebhook req = gson.fromJson(data, ReqWebhook.class);
        if (type.equals("reply_not_interest") || type.equals("reply_interest")) {
            resDataMessage = db.getMessageStatAndRespCustByMessageId(req.getEntry().get(0).getChanges().get(0)
                    .getValue().getMessages().get(0).getContext().getId());
        } else {
            resDataMessage = db.getMessageStatAndRespCustByMessageId(req.getEntry().get(0).getChanges().get(0)
                    .getValue().getStatuses().get(0).getId());
        }

        reqWebhook = req;
        scenario.log("Status Before: " + resDataMessage.get("status"));
        scenario.log("Resp Cust Before: " + resDataMessage.get("respSupplier"));
        scenario.log("Req Body: " + tmpGetParam);
    }

    @When("Hit API WebHook M&M with Method {string}")
    public void hit_api_web_hook_m_m_with_method(String method) {
        ResUrl resApi;
        if (method.equals("GET")) {
            resApi = APIHelper.getHitAPIMethodGet(scenario, "", tmpGetParam, urlWebHook
                    , "application/json", null, 0, 30000, 0);

        } else {
            resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqWebhook), urlWebHook
                    , "application/json", null, 0, 30000);
        }

        if (resApi.getHttpCode().equals("200")) {
            tmpHttpCode = resApi.getHttpCode();
        } else {
            if (resApi.getHttpCode() == null) {
                tmpHttpCode = "400";
                tmpResGetParam = resApi.getDataJson().trim();
            } else {
                tmpHttpCode = resApi.getHttpCode();
            }
        }
    }

    @Then("Response {string} API WebHook with Method {string}")
    public void response_api_web_hook_with_method(String httpCode, String method) {
        assertEquals(tmpHttpCode, httpCode);
        if (method.equals("POST")) {
            if (tmpType.equals("reply_not_interest") || tmpType.equals("reply_interest")) {
                resDataMessage = db.getMessageStatAndRespCustByMessageId(reqWebhook.getEntry().get(0).getChanges().get(0)
                        .getValue().getMessages().get(0).getContext().getId());
            } else {
                resDataMessage = db.getMessageStatAndRespCustByMessageId(reqWebhook.getEntry().get(0).getChanges().get(0)
                        .getValue().getStatuses().get(0).getId());
            }

            scenario.log("Status After: " + resDataMessage.get("status"));
            scenario.log("Resp Cust After: " + resDataMessage.get("respSupplier"));
        }

        scenario.log("Res Body: " + tmpHttpCode);
    }
}
