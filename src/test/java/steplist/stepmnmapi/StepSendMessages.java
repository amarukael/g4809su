package steplist.stepmnmapi;

import com.google.gson.Gson;
import constant.ConstantMnM;
import helper.APIHelper;
import helper.MnMHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import model.ResUrl;
import model.mnm.api.ReqSendMessages;
import model.mnm.api.ResSendMessages;
import utility.AddFunction;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class StepSendMessages {
    Gson gson = new Gson();
    MnMHelper mnmHelp = new MnMHelper();
    Rand r = new Rand();
    AddFunction addFunc = new AddFunction();
    private ReqSendMessages reqSendMsg;
    private ResSendMessages resSendMsg;
    private static Map<String, String> headers = new HashMap<>();
    private static String tmpExternalId;
    private static String tmpOriginalPartnerRefNo;
    private static String tmpInvTC;
    private static String urlSendMessages = ConstantMnM.urlMandM + ConstantMnM.urlSendMessages;
    Scenario scenario = Hooks.scenario;

    @Given("Valid Request \\({string}) Type {string} API Send Messages M&M")
    public void valid_request_type_api_send_messages_m_m(String sReqBody, String type) {
        ReqSendMessages req = reConstructReq(gson.fromJson(sReqBody, ReqSendMessages.class), "");
        reqSendMsg = req;
        String xExternalId = "MSG" + r.getExternalID(0);
        if (tmpExternalId == null)
            tmpExternalId = xExternalId;

        headers = mnmHelp.getHeaderGlobal("", gson.toJson(req), xExternalId);

        scenario.log("Headers: " + gson.toJson(headers));
        scenario.log("Req Body: " + gson.toJson(reqSendMsg));
    }

    @Given("Invalid Request \\({string}, {string}, {string}) Type {string} API Send Messages M&M")
    public void invalid_request_type_api_send_messages_m_m(String sReqBody, String param, String descTest
            , String type) {
        tmpInvTC = param + "_" + descTest;
        if (tmpInvTC.equals("urlMeta_wrong")) {
            System.out.println("Delay Start Scenario");
            addFunc.getDelay(20000);
            System.out.println("Start Scenario");
        }

        ReqSendMessages req = reConstructReq(gson.fromJson(sReqBody, ReqSendMessages.class), tmpInvTC);
        reqSendMsg = req;
        String xExternalId = "MSG" + r.getExternalID(0);
        if (tmpExternalId == null)
            tmpExternalId = xExternalId;
        if (param.equals("xExternalId")) {
            xExternalId = tmpExternalId;
            tmpInvTC = null;
        }

        headers = mnmHelp.getHeaderGlobal(param, gson.toJson(req), xExternalId);

        scenario.log("Headers: " + gson.toJson(headers));
        scenario.log("Req Body: " + gson.toJson(reqSendMsg));
    }

    @When("Partner Hit API Send Messages M&M")
    public void partner_hit_api_send_messages_m_m() {
        ResSendMessages res = new ResSendMessages();
        ResUrl resApi;
        resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqSendMsg), urlSendMessages
                , "application/json", headers, 1, 30000);
        if (resApi.getHttpCode().equals("200")) {
            res = gson.fromJson(resApi.getDataJson(), ResSendMessages.class);
            resSendMsg = res;
        } else {
            if (resApi.getDataJson() != null)
                res = gson.fromJson(resApi.getDataJson(), ResSendMessages.class);
            else {
                res.setResponseCode("50000");
                res.setResponseMessage("General Error");
            }

            resSendMsg = res;
        }
    }

    @Then("Partner gets response {string} API Send Messages M&M")
    public void partner_gets_response_api_send_messages_m_m(String rc) {
        if (tmpInvTC != null) {
            if (tmpInvTC.equals("urlMeta_wrong")) {
                System.out.println("Delay Start Scenario");
                addFunc.getDelay(20000);
                System.out.println("Start Scenario");
            }
        }

        scenario.log("Res Body: " + gson.toJson(resSendMsg));
        assertEquals(resSendMsg.getResponseCode(), rc);
    }

    private ReqSendMessages reConstructReq(ReqSendMessages reqData, String sCase) {
        ReqSendMessages req = reqData;
        String originPartnerRefNo = "";
        if (!sCase.equals("originalPartnerReferenceNo_empty")
                && !sCase.equals("originalPartnerReferenceNo_double")) {
            if (req.getMessagingProduct().equals("Slack"))
                originPartnerRefNo = "SLK" + r.getOriginalPartnerReferenceNo();
            else if (req.getMessagingProduct().equals("email"))
                originPartnerRefNo = "EML" + r.getOriginalPartnerReferenceNo();
            else
                originPartnerRefNo = "WA" + r.getOriginalPartnerReferenceNo();
        }

        // reconstruct
        if (!sCase.equals("originalPartnerReferenceNo_double"))
            req.setOriginalPartnerReferenceNo(originPartnerRefNo);

        return req;
    }
}
