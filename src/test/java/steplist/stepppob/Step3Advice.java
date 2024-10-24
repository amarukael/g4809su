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
import model.ppob.advice.ReqAdvice;
import model.ppob.advice.ResAdvice;
import model.ppob.payment.ResPayment;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step3Advice {
    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();
    Helper help = new Helper();
    PpobHelper ppobHelp = new PpobHelper();
    PpobDataHelper db = new PpobDataHelper();
    private static Map<String, ResPayment> lresPay;
    private static ResPayment resPay;
    ReqAdvice reqAdv;
    ResAdvice resAdv;
    String urlAdv = ConstantPpob.ppobAdvV2;
//    String secretKey = ConstantPpob.secretKeyPpob;
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;

    @Before("@advice_v2_sukses")
    public void setUpData(){
        if (flgSetUp == 0) {
            lresPay = Step2Payment.lresPay;
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}) API Advice V2 PPOB")
    public void valid_request_api_advice_v2_ppob(String customerId, String extendInfo) {
        resPay = lresPay.get(customerId);
        String trxId = resPay.getPartnerid() + "-" + resPay.getTrxid();
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String partnerId = resPay.getPartnerid();
        String productId = resPay.getProductid();
//        String customerId = resPay.getCustomerid();
        String trackingRef = resPay.getTrackingref();
        String terminalId = resPay.getTerminalid();
        String secretKey = db.getSecretKeyByPartnerId(partnerId);
        String fSign = trxId
                + trxDate
                + partnerId
                + productId
                + customerId
                + extendInfo
                + trackingRef
                + terminalId
                + secretKey;

        String signature = "";
        try {
            signature = ppobHelp.createSign(fSign);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqAdvice req = new ReqAdvice(trxId, trxDate, partnerId, productId, customerId, extendInfo
                , trackingRef, terminalId, signature);
        reqAdv = req;

        scenario.log("Req Body: " + gsonLog.toJson(reqAdv));
    }

    @When("Partner hits API Advice V2 PPOB")
    public void partner_hits_api_advice_v2_ppob() {
        ResAdvice res = new ResAdvice();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqAdv), urlAdv
                , "application/json", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            res.setRc("5");
            res.setRcdesc("Error lain (di sisi aggregator)");
            resAdv = res;
        } else {
            res = gson.fromJson(resApi.getDataJson(), ResAdvice.class);
            resAdv = res;
        }
    }

    @Then("Partner gets response {string} API Advice V2 PPOB")
    public void partner_gets_response_api_advice_v2_ppob(String rc) {
        assertEquals(resAdv.getRc(), rc);
        scenario.log("Res Body: " + gsonLog.toJson(resAdv));
    }
}
