package steplist.stepexternallja;

import com.google.gson.Gson;
import constant.ConstantAPIProject;
import constant.ConstantExternal;
import helper.APIHelper;
import helper.Helper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.external.lja.ReqAdvice;
import org.json.JSONObject;
import steplist.stepapiproject.Step1Inquiry;
import utility.AddFunction;
import utility.JSONtoURLEncode;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step4Advice {
    Gson gson = new Gson();
    Helper help = new Helper();
    Rand r = new Rand();
    AddFunction addFunc = new AddFunction();
    ReqAdvice reqAdv;
    String resAdv;
    String urlAdvice = ConstantExternal.urlExternalLJA;
    String trxType = "031";
    private static String tmpCondition = "";
    private static String tmpTrxId;
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;
    private static int flgDlyAdv;
    private static Map<String, String> tmpBillRef = new HashMap<>();

    @Before("@external_lja_adv_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            tmpBillRef.put("9920010001", "LJA20240812181418151");
            tmpBillRef.put("9920010002", "LJA20240812181526102");
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}) API Advice External-LJA")
    public void valid_request_api_advice_external_lja(String merchant, String terminal, String pwd
            , String msisdn, String accNo, String amount) {
        String billRef = tmpBillRef.get(accNo);
        String trxId = "LJA" + r.getExternalID(1);
        tmpTrxId = trxId;
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");

        ReqAdvice req = new ReqAdvice(trxType, merchant, terminal, pwd, msisdn, accNo, dateTimeReq
                , amount, billRef, trxId, null);
        reqAdv = req;
        scenario.log("Req Body: " + gson.toJson(reqAdv));
    }

    @Given("Invalid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}) API Advice External-LJA")
    public void invalid_request_api_advice_external_lja(String merchant, String terminal, String pwd
            , String msisdn, String accNo, String amount, String param, String descTest) {
        tmpCondition = param + "-" + descTest;
        String billRef = tmpBillRef.get(accNo);
        String trxId = "LJA" + r.getExternalID(1);
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");

        if (tmpCondition.equals("trx_type-Null")) trxType = null;
        if (tmpCondition.equals("trx_type-Empty")) trxType = "";
        if (tmpCondition.equals("merchant-Null")) merchant = null;
        if (tmpCondition.equals("terminal-Null")) terminal = null;
        if (tmpCondition.equals("pwd-Null")) pwd = null;
        if (tmpCondition.equals("msisdn-Null")) msisdn = null;
        if (tmpCondition.equals("acc_no-Null")) accNo = null; billRef = "LJA20240812181418151";
        if (tmpCondition.equals("acc_no-Empty")) billRef = "LJA20240812181418151";
        if (tmpCondition.equals("trx_date-Null")) dateTimeReq = null;
        if (tmpCondition.equals("trx_date-Empty")) dateTimeReq = "";
        if (tmpCondition.equals("trx_date-Invalid_Format")) dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");;
        if (tmpCondition.equals("amount-Null")) amount = null;
        if (tmpCondition.equals("bill_ref-Null")) billRef = null;
        if (tmpCondition.equals("bill_ref-Empty")) billRef = "";
        if (tmpCondition.equals("bill_ref-Invalid")) billRef = billRef + "1";
        if (tmpCondition.equals("trx_id-Null")) trxId = null;
        if (tmpCondition.equals("trx_id-Empty")) trxId = "";
        if (tmpCondition.equals("trx_id-Double")) trxId = tmpTrxId;

        ReqAdvice req = new ReqAdvice(trxType, merchant, terminal, pwd, msisdn, accNo, dateTimeReq
                , amount, billRef, trxId, null);
        reqAdv = req;
        scenario.log("Req Body: " + gson.toJson(reqAdv));
    }

    @When("Partner hit API Advice External-LJA")
    public void partner_hit_api_advice_external_lja() {
        String res = "";
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqAdv));
        String paramString = jr.jsonToURLEncoding(json);

        ResUrl resApi = APIHelper.getHitAPI(scenario, "", paramString, urlAdvice
                , "application/x-www-form-urlencoded", null, 0, 20000);
        if (resApi.getDataJson() == null) {
            res = "05:ERROR - General Error:::";
            resAdv = res;
        } else {
            res = resApi.getDataJson();
            resAdv = res;
        }
    }

    @Then("Partner gets response {string} and response desc {string} API Advice External-LJA")
    public void partner_gets_response_and_response_desc_api_advice_external_lja(String rc, String rcDesc) {
        String[] tmpResAdv = resAdv.split(":", -1 );
        String resRc = tmpResAdv[0];
        assertEquals(resRc, rc);
        if (!rcDesc.isEmpty()) {
            String resRcDesc = tmpResAdv[1];
            assertEquals(resRcDesc.trim(), rcDesc);
        }
        scenario.log("Res Body: " + gson.toJson(resAdv));
    }
}
