package steplist.stepototrans;

import com.google.gson.Gson;
import constant.ConstantPpob;
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
//    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();
    Helper help = new Helper();
    PpobHelper ppobHelp = new PpobHelper();
    Rand r = new Rand();
    private static Map<String, ResInquiry> lresInq;
    private static ResInquiry resInq;
    public static Map<String, ResPayment> lresPay = new HashMap<String, ResPayment>();
    ReqPayment reqPay;
    ResPayment resPay;
    String urlPay = ConstantPpob.ppobPay;
    String urlPayV2 = ConstantPpob.ppobPayV2;
    String secretKey = ConstantPpob.secretKeyPpob;
    Scenario scenario = Hooks.scenario;

    @Before("@payment_sukses")
    public void setUpData() {
        lresInq = Step1Inquiry.lresInq;
        resInq = lresInq.get("SUCCESS");
        lresPay.clear();
    }

    @Given("Valid Request \\({string}, {string}) API Payment {string} OTOTrans")
    public void valid_request_api_payment_ototrans(String extendInfo, String totalAmount, String version) {
        String trxId = resInq.getTrxid();
        if (version.equals("V2")) trxId = r.randStrNum(15);

        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String partnerId = resInq.getPartnerid();
        String productId = resInq.getProductid();
        String customerId = resInq.getCustomerid();
        String trackingRef = resInq.getTrackingref();
        String terminalId = resInq.getTerminalid();
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

        scenario.log("Req Body: " + gson.toJson(reqPay));
    }

    @When("Partner hits API Payment {string} OTOTrans")
    public void partner_hits_api_payment_ototrans(String version) {
        String finalUrlPay = urlPay;
        if (version.equals("V2")) finalUrlPay = urlPayV2;

        ResPayment res = new ResPayment();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqPay), finalUrlPay
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
    
    @Then("Partner gets response {string} API Payment {string} OTOTrans")
    public void partner_gets_response_api_payment_ototrans(String rc, String version) {
        assertEquals(resPay.getRc(), rc);
        lresPay.put("SUCCESS", resPay);
        scenario.log("Res Body: " + gson.toJson(resPay));
    }
}
