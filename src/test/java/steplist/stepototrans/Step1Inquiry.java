package steplist.stepototrans;

import com.google.gson.Gson;
import constant.ConstantPpob;
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
//    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();
    Helper help = new Helper();
    PpobHelper ppobHelp = new PpobHelper();
    Rand r = new Rand();
    public static Map<String, ResInquiry> lresInq = new HashMap<String, ResInquiry>();
    ReqInquiry reqInq;
    ResInquiry resInq;
    String urlInq = ConstantPpob.ppobInq;
    String urlInqV2 = ConstantPpob.ppobInqV2;
    String secretKey = ConstantPpob.secretKeyPpob;
    Scenario scenario = Hooks.scenario;

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}) API Inquiry {string} OTOTrans")
    public void valid_request_api_inquiry_ototrans(String partnerId, String productId, String customerId
            , String extendInfo, String terminalId, String version) {
        String trxId = r.randStrNum(15);
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String trackingRef = partnerId + r.randStrNum(15);
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

        scenario.log("Req Body: " + gson.toJson(reqInq));
    }

    @When("Partner hits API Inquiry {string} OTOTrans")
    public void partner_hits_api_inquiry_ototrans(String version) {
        String finalUrlInq = urlInq;
        if (version.equals("V2")) finalUrlInq = urlInqV2;

        ResInquiry res = new ResInquiry();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqInq), finalUrlInq
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

    @Then("Partner gets response {string} API Inquiry {string} OTOTrans")
    public void partner_gets_response_api_inquiry_ototrans(String rc, String version) {
        assertEquals(resInq.getRc(), rc);
        lresInq.put("SUCCESS", resInq);
        scenario.log("Res Body: " + gson.toJson(resInq));
    }
}
