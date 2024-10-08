package steplist.stepva;

import com.google.gson.Gson;
import helper.APIHelper;
import helper.VaHelper;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.va.create.CreateVaReq;
import model.va.create.res.CreataVaRes;
import utility.Rand;

import java.io.UnsupportedEncodingException;

import static org.testng.Assert.assertEquals;

public class StepCreateVa {

    Gson gsonLog = new Gson();

    VaHelper helper = new VaHelper();

    public static CreateVaReq CREATEVAREQ;

    public static CreataVaRes CREATEVARES;

    Scenario scenario = Hooks.scenario;
    String urlCreatVa = "http://dev140.ids.id:8080/PaymentGateway/webresources/createva";
    String urlCreateVaStag = "https://private-staging.ids.id/PaymentGateway/webresources/createva";

    String secretKey = "idx12345";

    Rand rand = new Rand();

    @Given("{string} Request \\({string},{string},{string},{string},{string},{string},{string},{string},{string},{string}) API Create VA")
    public void requestAPICreateVA(String condition,
                                   String bankCode,
                                   String txAmt,
                                   String isOpen,
                                   String vaExpiredTime,
                                   String isLifeTime,
                                   String username,
                                   String trxExpiredTime,
                                   String partnerId,
                                   String extendInfo,
                                   String terminal) throws UnsupportedEncodingException {

        CreateVaReq req = new CreateVaReq();
        req.setBank_code(bankCode);
        req.setTx_amt(txAmt);
        req.setIs_open(isOpen);
        req.setVa_expired_time(vaExpiredTime);
        req.setIs_lifetime(isLifeTime);
        req.setUsername(username);
        req.setTrx_expiration_time(trxExpiredTime);
        req.setPartner_id(partnerId);
        req.setExtendinfo(extendInfo);
        req.setTerminal(terminal);


        req = reConstructReq(req,condition);
        CREATEVAREQ = req;
        scenario.log("Req Body: " + gsonLog.toJson(req));

    }

    @Given("Request \\({string}) API Create VA")
    public void validRequestAPIInquiry(String reqBody) throws UnsupportedEncodingException {
        CreateVaReq req = reConstructReq(gsonLog.fromJson(reqBody, CreateVaReq.class), "");
        CREATEVAREQ = req;
        scenario.log("Req Body: " + gsonLog.toJson(req));

    }

    private CreateVaReq reConstructReq(CreateVaReq reqData, String sCase) throws UnsupportedEncodingException {
        CreateVaReq req = reqData;
        // reconstruct
        scenario.log("Case : " + sCase);
        req.setTx_id(rand.getRandomTrxId("IDX",""));
        req.setTx_date(rand.getCurrentDate());

        if(sCase.equals("Invalid Format Tx_id")){
            req.setTx_id(rand.getRandomTrxId("",""));
        }

        if(sCase.equals("Invalid Format Date")){
            req.setTx_date("");
        }

        req.setSignature(helper.createSign(req.getStringSign(secretKey,true)));
        return req;
    }

    @When("User Hit API Create Va")
    public void partner_hits_api_inquiry_v2_ppob() {
        CreataVaRes res = new CreataVaRes();
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", gsonLog.toJson(CREATEVAREQ), urlCreateVaStag
                , "application/json", null, 0, 20000);

            res = gsonLog.fromJson(resApi.getDataJson(), CreataVaRes.class);
            CREATEVARES = res;

    }

    @Then("User gets response {string} API Creata VA")
    public void userGetsResponseAPICreataVA(String code) {
        assertEquals(CREATEVARES.getStatus().getCode(), code);
        scenario.log("Res Body: " + gsonLog.toJson(CREATEVARES));
    }

}
