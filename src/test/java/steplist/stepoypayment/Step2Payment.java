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
import io.cucumber.java.it.Ma;
import model.ResUrl;
import model.oypayment.ReqPayment;
import org.json.JSONObject;
import steplist.stepoypayment.Step1Inquiry;
import utility.JSONtoURLEncode;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step2Payment {
    Gson gson = new Gson();
    Helper help = new Helper();
    OYPaymentHelper oyPayHelp = new OYPaymentHelper();
    private static Map<String, String> lresInq = new HashMap<>();
    public static Map<String, String> lresPay = new HashMap<>();
    ReqPayment reqPay;
    String resInq;
    String resPay;
    String urlPayment = ConstantOYPayment.urlOYPayment + ConstantOYPayment.getUrlOYPaymentPay;
    String secretKey = ConstantOYPayment.secretKey;
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;

    @Before("@pay_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresInq = Step1Inquiry.lresInq;
            lresPay.clear();
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Payment OYPayment")
    public void valid_request_api_payment_oy_payment(String custId, String amount, String charge
            , String totalAmount, String adminFee) {
        String[] tmpResInq = lresInq.get(custId).split("\\|", -1);
        String agentId = tmpResInq[0];
        String agentPin = tmpResInq[1];
        String trxId = tmpResInq[2];
        String storeId = tmpResInq[3];
        String productId = tmpResInq[16];
        String customerId = tmpResInq[4];
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");
        String paymentPeriod = tmpResInq[12];
        String fSign = agentId
                + agentPin
                + trxId
                + storeId
                + productId
                + customerId
                + dateTimeReq
                + paymentPeriod
                + amount
                + charge
                + totalAmount
                + adminFee
                + secretKey;

        String signature = "";
        try {
            signature = oyPayHelp.createSign(fSign);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqPayment req = new ReqPayment(agentId, agentPin, trxId, storeId, productId, customerId, dateTimeReq
                , paymentPeriod, amount, charge, totalAmount, adminFee, signature);
        reqPay = req;

        scenario.log("Req Body: " + gson.toJson(reqPay));
    }

    @When("Partner hit API Payment OYPayment")
    public void partner_hit_api_payment_oy_payment() {
        String res = "";
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqPay));
        String paramString = jr.jsonToURLEncoding(json);

        ResUrl resApi = APIHelper.getHitAPIMethodGet(scenario, "", paramString, urlPayment
                , "", null, 0, 20000, 1);
        if (resApi.getDataJson() == null) {
            res = "|||||||||||05|ERROR - General Error|||||";
            resPay = res;
        } else {
            res = resApi.getDataJson();
            resPay = res;
        }
    }

    @Then("Partner gets response {string} and response desc {string} API Payment OYPayment")
    public void partner_gets_response_and_response_desc_api_payment_oy_payment(String rc, String rcDesc) {
        String[] tmpResPay = resPay.split("\\|", -1 );
        String resRc = tmpResPay[11];
        String resRcDesc = tmpResPay[12];
        assertEquals(resRc, rc);
        assertEquals(resRcDesc, rcDesc);
        lresPay.put(tmpResPay[4], resPay);
        scenario.log("Res Body: " + resPay);
    }
}
