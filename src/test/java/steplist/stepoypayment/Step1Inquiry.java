package steplist.stepoypayment;

import com.google.gson.Gson;
import constant.ConstantOYPayment;
import helper.APIHelper;
import helper.Helper;
import helper.OYPaymentHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.oypayment.ReqInquiry;
import model.ppob.inquiry.ResInquiry;
import org.json.JSONObject;
import utility.JSONtoURLEncode;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step1Inquiry {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    OYPaymentHelper oyPayHelp = new OYPaymentHelper();
    public static Map<String, String> lresInq = new HashMap<>();
    ReqInquiry reqInq;
    String resInq;
    String urlInquiry = ConstantOYPayment.urlOYPayment + ConstantOYPayment.getUrlOYPaymentInq;
    String secretKey = ConstantOYPayment.secretKey;
    Scenario scenario = Hooks.scenario;

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Inquiry OYPayment")
    public void valid_request_api_inquiry_oy_payment(String agentId, String agentPin, String storeId
            , String productId, String customerId) {
        String trxId = ConstantOYPayment.environmentSvr + r.getExternalID(0);
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

        ReqInquiry req = new ReqInquiry(agentId, agentPin, trxId
                , storeId, productId, customerId, dateTimeReq, signature);
        reqInq = req;

        scenario.log("Req Body: " + gson.toJson(reqInq));
    }

    @When("Partner hit API Inquiry OYPayment")
    public void partner_hit_api_inquiry_oy_payment() {
        String res = "";
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqInq));
        String paramString = jr.jsonToURLEncoding(json);

        ResUrl resApi = APIHelper.getHitAPIMethodGet(scenario, "", paramString, urlInquiry
                , "", null, 0, 20000, 1);
        if (resApi.getDataJson() == null) {
            res = "||||||05|ERROR - General Error|||||||||||";
            resInq = res;
        } else {
            res = resApi.getDataJson();
            resInq = res;
        }

//        System.out.println("OK");
    }

    @Then("Partner gets response {string} and response desc {string} API Inquiry OYPayment")
    public void partner_gets_response_and_response_desc_api_inquiry_oy_payment(String rc, String rcDesc) {
        String[] tmpResInq = resInq.split("\\|", -1 );
        String resRc = tmpResInq[6];
        String resRcDesc = tmpResInq[7];
        assertEquals(resRc, rc);
        assertEquals(resRcDesc, rcDesc);
        lresInq.put(tmpResInq[4], resInq);
        scenario.log("Res Body: " + resInq);
    }
}
