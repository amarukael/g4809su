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
import model.ppob.inquiryaccount.ReqInquiryAcc;
import model.ppob.inquiryaccount.ResInquiryAcc;
import utility.Rand;

import static org.testng.Assert.assertEquals;

public class Step4InquiryAcc {
//    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
    Gson gson = new Gson();
    Helper help = new Helper();
    PpobHelper ppobHelp = new PpobHelper();
    Rand r = new Rand();
    ReqInquiryAcc reqInqAcc;
    ResInquiryAcc resInqAcc;
    String urlInqAcc = ConstantPpob.ppobInqAcc;
    String secretKey = ConstantPpob.secretKeyPpob;
    Scenario scenario = Hooks.scenario;

    
    @Given("Valid Request \\({string}, {string}, {string}) API Inquiry Account OTOTrans")
    public void valid_request_api_inquiry_account_ototrans(String partnerId, String productId, String customerId) {
        String trackingRef = r.randStrNum(15);
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String fSign = trackingRef
                + trxDate
                + partnerId
                + productId
                + customerId
                + secretKey;

        String signature = "";
        try {
            signature = ppobHelp.createSign(fSign);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqInquiryAcc req = new ReqInquiryAcc(trackingRef, trxDate, partnerId, productId, customerId, signature);
        reqInqAcc = req;

        scenario.log("Req Body: " + gson.toJson(reqInqAcc));
    }

    @Given("Invalid Request param {string} {string} \\({string}, {string}, {string}) API Inquiry Account OTOTrans")
    public void invalid_request_param_api_inquiry_account_ototrans(String param, String descTest, String partnerId
            , String productId, String customerId) {
        String trackingRef = r.randStrNum(15);
        if (param.equals("trackingref") && descTest.equals("Empty")) {
            trackingRef = "";
        }

        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        if (param.equals("trxdate") && descTest.equals("Empty")) {
            trxDate = "";
        } else if (param.equals("trxdate") && descTest.equals("Wrong")) {
            trxDate =  trxDate + "123";
        }

        String fSign = trackingRef
                + trxDate
                + partnerId
                + productId
                + customerId
                + secretKey;

        String signature = "";
        try {
            signature = ppobHelp.createSign(fSign);
            if (param.equals("signature") && descTest.equals("Empty")) {
                signature = "";
            } else if (param.equals("signature") && descTest.equals("Wrong")) {
                signature = signature + "123";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        ReqInquiryAcc req = new ReqInquiryAcc(trackingRef, trxDate, partnerId, productId, customerId, signature);
        reqInqAcc = req;

        scenario.log("Req Body: " + gson.toJson(reqInqAcc));
    }
    
    @When("Partner hits API Inquiry Account OTOTrans")
    public void partner_hits_api_inquiry_account_ototrans() {
        ResInquiryAcc res = new ResInquiryAcc();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqInqAcc), urlInqAcc
                , "application/json", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            res.setRc("05");
            res.setRcdesc("Transaksi gagal");
            resInqAcc = res;
        } else {
            res = gson.fromJson(resApi.getDataJson(), ResInquiryAcc.class);
            resInqAcc = res;
        }
    }
    
    @Then("Partner gets response {string} API Inquiry Account OTOTrans")
    public void partner_gets_response_api_inquiry_account_ototrans(String rc) {
        assertEquals(resInqAcc.getRc(), rc);
        scenario.log("Res Body: " + gson.toJson(resInqAcc));
    }
}
