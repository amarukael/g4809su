package steplist.stepoypayment;

import com.google.gson.Gson;
import constant.ConstantOYPayment;
import helper.APIHelper;
import helper.Helper;
import helper.OYPaymentHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.oypayment.ReqCommit;
import org.json.JSONObject;
import utility.JSONtoURLEncode;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step3Commit {
    Gson gson = new Gson();
    Helper help = new Helper();
    OYPaymentHelper oyPayHelp = new OYPaymentHelper();
    private static Map<String, String> lresPay = new HashMap<>();
    ReqCommit reqCom;
    String resPay;
    String resCom;
    String urlCommit = ConstantOYPayment.urlOYPayment + ConstantOYPayment.getUrlOYPaymentCom;
    String secretKey = ConstantOYPayment.secretKey;
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;

    @Before("@commit_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresPay = Step2Payment.lresPay;
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}) API Commit OYPayment")
    public void valid_request_api_commit_oy_payment(String custId) {
        String[] tmpResInq = lresPay.get(custId).split("\\|", -1);
        String agentId = tmpResInq[0];
        String agentPin = tmpResInq[1];
        String trxId = tmpResInq[2];
        String storeId = tmpResInq[3];
        String productId = tmpResInq[16];
        String customerId = tmpResInq[4];
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");
        String fSign = agentId
                + agentPin
                + trxId
                + storeId
                + productId
                + customerId
                + dateTimeReq
                + secretKey;

        String signature = "";
        try {
            signature = oyPayHelp.createSign(fSign);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqCommit req = new ReqCommit(agentId, agentPin, trxId,storeId, productId, customerId, dateTimeReq, signature);
        reqCom = req;

        scenario.log("Req Body: " + gson.toJson(reqCom));
    }

    @When("Partner hit API Commit OYPayment")
    public void partner_hit_api_commit_oy_payment() {
        String res = "";
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqCom));
        String paramString = jr.jsonToURLEncoding(json);

        ResUrl resApi = APIHelper.getHitAPIMethodGet(scenario, "", paramString, urlCommit
                , "", null, 0, 20000, 1);
        if (resApi.getDataJson() == null) {
            res = "||||||05|ERROR - General Error|||||";
            resCom = res;
        } else {
            res = resApi.getDataJson();
            resCom = res;
        }
    }

    @Then("Partner gets response {string} and response desc {string} API Commit OYPayment")
    public void partner_gets_response_and_response_desc_api_commit_oy_payment(String rc, String rcDesc) {
        String[] tmpResCom = resCom.split("\\|", -1 );
        String resRc = tmpResCom[6];
        String resRcDesc = tmpResCom[7];
        assertEquals(resRc, rc);
        assertEquals(resRcDesc, rcDesc);
        scenario.log("Res Body: " + resCom);
    }
}
