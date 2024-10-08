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
import model.ppob.advice.ReqAdvice;
import model.ppob.advice.ResAdvice;
import model.ppob.payment.ResPayment;
import utility.AddFunction;
import utility.Rand;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step3Advice {
//    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();
    AddFunction addFunc = new AddFunction();
    Helper help = new Helper();
    PpobHelper ppobHelp = new PpobHelper();
    Rand r = new Rand();
    private static Map<String, ResPayment> lresPay;
    private static ResPayment resPay;
    ReqAdvice reqAdv;
    ResAdvice resAdv;
    String urlAdv = ConstantPpob.ppobAdv;
    String urlAdvV2 = ConstantPpob.ppobAdvV2;
    String secretKey = ConstantPpob.secretKeyPpob;
    Scenario scenario = Hooks.scenario;

    @Before("@advice_sukses")
    public void setUpData(){
        lresPay = Step2Payment.lresPay;
        resPay = lresPay.get("SUCCESS");
    }

    @Given("Valid Request \\({string}, {string}) API Advice {string} OTOTrans")
    public void valid_request_api_advice_ototrans(String billerId, String extendInfo, String version) {
        if (billerId.equals("FLIP")) {
            System.out.println("Delay Start Scenario");
            addFunc.getDelay(80000);
            System.out.println("Start Scenario");
        }

        String trxId = resPay.getTrxid();
        if (version.equals("V2")) trxId = r.randStrNum(15);

        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String partnerId = resPay.getPartnerid();
        String productId = resPay.getProductid();
        String customerId = resPay.getCustomerid();
        String trackingRef = resPay.getTrackingref();
        String terminalId = resPay.getTerminalid();
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

        scenario.log("Req Body: " + gson.toJson(reqAdv));
    }

    @When("Partner hits API Advice {string} OTOTrans")
    public void partner_hits_api_advice_ototrans(String version) {
        String finalUrlAdv = urlAdv;
        if (version.equals("V2")) finalUrlAdv = urlAdvV2;

        ResAdvice res = new ResAdvice();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqAdv), finalUrlAdv
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

    @Then("Partner gets response {string} API Advice {string} OTOTrans")
    public void partner_gets_response_api_advice_ototrans(String rc, String version) {
        assertEquals(resAdv.getRc(), rc);
        scenario.log("Res Body: " + gson.toJson(resAdv));
    }
}
