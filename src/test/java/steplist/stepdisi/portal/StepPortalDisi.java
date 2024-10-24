package steplist.stepdisi.portal;

import com.google.gson.Gson;
import constant.ConstantDisi;
import helper.APIHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.disi.portal.RefundWithdraw;
import model.disi.portal.ReqPortal;
import model.disi.portal.ResPortal;

import static org.testng.Assert.assertEquals;

public class StepPortalDisi {
    Gson gson = new Gson();
    Scenario scenario = Hooks.scenario;
    Helper help = new Helper();
    ReqPortal reqPortal;
    ResPortal resPortal;
    String urlPortal = ConstantDisi.urlPortal;

    @Given("Valid data \\({string}, {string}, {string}) API Portal Disi")
    public void valid_data_api_portal_disi(String merchantId, String partnerId, String trackingRef) {
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        RefundWithdraw refundWithdraw = new RefundWithdraw(trxDate, merchantId, partnerId, trackingRef, null);
        ReqPortal req = new ReqPortal("refundwithdraw", gson.toJson(refundWithdraw));
        reqPortal = req;
        scenario.log("Req Body: " + gson.toJson(reqPortal));
    }
    @Given("Invalid Request {string} {string} \\({string}, {string}, {string}) API Portal Disi")
    public void invalid_request_api_portal_disi(String param, String descTest, String merchantId
            , String partnerId, String trackingRef) {
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        RefundWithdraw refundWithdraw = new RefundWithdraw(trxDate, merchantId, partnerId, trackingRef, null);
        ReqPortal req = new ReqPortal("refundwithdraw", gson.toJson(refundWithdraw));
        reqPortal = req;
        scenario.log("Req Body: " + gson.toJson(reqPortal));
    }

    @When("Internal hits API Portal Disi")
    public void internal_hits_api_portal_disi() {
        ResPortal res = new ResPortal();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqPortal), urlPortal
                , "application/json", null, 0, 30000);
        if (resApi.getDataJson() == null) {
            res.setRc("5");
            res.setRcDesc("General Error");
            resPortal = res;
        } else {
            res = gson.fromJson(resApi.getDataJson(), ResPortal.class);
            resPortal = res;
        }
    }

    @Then("Internal gets {string} desc {string} API Portal Disi")
    public void internal_gets_desc_api_portal_disi(String rc, String rcDesc) {
        assertEquals(resPortal.getRc(), rc);
        assertEquals(resPortal.getRcDesc(), rcDesc);
        scenario.log("Res Body: " + gson.toJson(resPortal));
    }
}
