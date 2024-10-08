package steplist.stepppob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constant.ConstantPpob;
import database.PpobDataHelper;
import helper.APIHelper;
import helper.Helper;
import helper.PpobHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.ppob.inquiry.ReqInquiry;
import model.ppob.inquiry.ResInquiry;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step1Inquiry {
    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();
    Helper help = new Helper();
    PpobHelper ppobHelp = new PpobHelper();
    PpobDataHelper db = new PpobDataHelper();
    Rand r = new Rand();
    public static Map<String, ResInquiry> lresInq = new HashMap<String, ResInquiry>();
    ReqInquiry reqInq;
    ResInquiry resInq;
    String urlInq = ConstantPpob.ppobInqV2;
    String secretKey = "";
    Scenario scenario = Hooks.scenario;

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Inquiry V2 PPOB")
    public void valid_request_api_inquiry_v2_ppob(String partnerId, String productId, String customerId
            , String extendInfo, String terminalId) {
        String trxId = r.randStrNum(15);
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String trackingRef = r.randStrNum(15);
        secretKey = db.getSecretKeyByPartnerId(partnerId);
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

        ReqInquiry req = new ReqInquiry(trxId, trxDate, partnerId, productId, customerId, extendInfo
                , trackingRef, terminalId, signature);
        reqInq = req;

        scenario.log("Req Body: " + gsonLog.toJson(reqInq));
    }

    @When("Partner hits API Inquiry V2 PPOB")
    public void partner_hits_api_inquiry_v2_ppob() {
        ResInquiry res = new ResInquiry();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqInq), urlInq
                , "application/json", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            res.setRc("5");
            res.setRcdesc("Error lain (di sisi aggregator)");
            resInq = res;
        } else {
            res = gson.fromJson(resApi.getDataJson(), ResInquiry.class);
            resInq = res;
        }
    }

    @Then("Partner gets response {string} API Inquiry V2 PPOB")
    public void partner_gets_response_api_inquiry_v2_ppob(String rc) {
        assertEquals(resInq.getRc(), rc);
        lresInq.put(resInq.getCustomerid(), resInq);
        scenario.log("Res Body: " + gsonLog.toJson(resInq));
    }
}
