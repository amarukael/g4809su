package steplist.stepppob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constant.ConstantPpob;
import database.PpobDataHelper;
import helper.APIHelper;
import helper.Helper;
import helper.PpobHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.ppob.inquiry.ResInquiry;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step2Payment {
    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();
    Helper help = new Helper();
    PpobHelper ppobHelp = new PpobHelper();
    PpobDataHelper db = new PpobDataHelper();
    Rand r = new Rand();
    private static Map<String, ResInquiry> lresInq;
    private static ResInquiry resInq;
    public static Map<String, ResPayment> lresPay = new HashMap<String, ResPayment>();
    ReqPayment reqPay;
    ResPayment resPay;
    String urlPay = ConstantPpob.ppobPayV2;
    String urlPayAsync = ConstantPpob.ppobPayV2Async;
//    String secretKey = ConstantPpob.secretKeyPpob;
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;

    @Before("@payment_v2_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresInq = Step1Inquiry.lresInq;
            lresPay.clear();
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {int}) API Payment V2 PPOB")
    public void valid_request_api_payment_v2_ppob(String partnerId, String productId, String customerId
            , String totalAmount, String extendInfo, String terminalId, Integer flgInq) {
        buildReqPayment(partnerId, productId, customerId, totalAmount, extendInfo, terminalId, flgInq);
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {int}) API Payment V2 Async PPOB")
    public void valid_request_api_payment_v2_async_ppob(String partnerId, String productId, String customerId
            , String totalAmount, String extendInfo, String terminalId, Integer flgInq) {
        buildReqPayment(partnerId, productId, customerId, totalAmount, extendInfo, terminalId, flgInq);
    }

    @When("Partner hits API Payment V2 PPOB")
    public void partner_hits_api_payment_v2_ppob() {
        ResPayment res = new ResPayment();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqPay), urlPay
                , "application/json", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            res.setRc("5");
            res.setRcdesc("Error lain (di sisi aggregator)");
            resPay = res;
        } else {
            res = gson.fromJson(resApi.getDataJson(), ResPayment.class);
            resPay = res;
        }
    }

    @When("Partner hits API Payment V2 Async PPOB")
    public void partner_hits_api_payment_v2_async_ppob() {
        ResPayment res = new ResPayment();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqPay), urlPayAsync
                , "application/json", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            res.setRc("5");
            res.setRcdesc("Error lain (di sisi aggregator)");
            resPay = res;
        } else {
            res = gson.fromJson(resApi.getDataJson(), ResPayment.class);
            resPay = res;
        }
    }
    
    @Then("Partner gets response {string} API Payment V2 PPOB")
    public void partner_gets_response_api_payment_v2_ppob(String rc) {
        assertEquals(resPay.getRc(), rc);
        lresPay.put(resPay.getCustomerid(), resPay);
        scenario.log("Res Body: " + gsonLog.toJson(resPay));
    }

    @Then("Partner gets response {string} API Payment V2 Async PPOB")
    public void partner_gets_response_api_payment_v2_async_ppob(String rc) {
        assertEquals(resPay.getRc(), rc);
        lresPay.put(resPay.getCustomerid(), resPay);
        scenario.log("Res Body: " + gsonLog.toJson(resPay));
    }

    private void buildReqPayment(String partnerId, String productId, String customerId
            , String totalAmount, String extendInfo, String terminalId, Integer flgInq) {
        String trxId = r.randStrNum(15);
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String trackingRef = r.randStrNum(15);
        if (flgInq == 1) {
            resInq = lresInq.get(customerId);
            productId = resInq.getProductid();
            partnerId = resInq.getPartnerid();
            customerId = resInq.getCustomerid();
            trackingRef = resInq.getTrackingref();
            terminalId = resInq.getTerminalid();
            totalAmount = resInq.getTotalamount();
            trxId = resInq.getPartnerid() + "-" + resInq.getTrxid();
        }
        String secretKey = db.getSecretKeyByPartnerId(partnerId);
        String fSign = trxId
                + trxDate
                + partnerId
                + productId
                + customerId
                + extendInfo
                + totalAmount
                + trackingRef
                + terminalId
                + secretKey;

        String signature = "";
        try {
            signature = ppobHelp.createSign(fSign);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqPayment req = new ReqPayment(trxId, trxDate, partnerId, productId, customerId, extendInfo
                , totalAmount, trackingRef, terminalId, signature);
        reqPay = req;

        scenario.log("Req Body: " + gsonLog.toJson(reqPay));
    }
}
