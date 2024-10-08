package steplist.stepapiproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constant.ConstantAPIProject;
import helper.APIHelper;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.apiproject.*;
import org.json.JSONObject;
import org.json.XML;
import utility.AddFunction;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Step4Advice {
    Gson gson = new Gson();
    Helper help = new Helper();
    AddFunction addFunc = new AddFunction();
    ApiProjectHelper apiProjHelp = new ApiProjectHelper();
    public static Map<String, String> lresPay = new HashMap<>();
    public static Map<String, ResPaymentXml> lresPayXml = new HashMap<>();
    ReqAdviceXml reqAdvXml;
    ResPaymentXml resPayXml;
    ResAdviceXml resAdvXml;
    String urlAdviceNonAlfa = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjAdvNonAlfa;
    String secretKeyNonAlfa = ConstantAPIProject.secretKeyNonAlfa;
    private static String tmpCondition = "";
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;
    private static int flgDlyAdv;
    private static int flgSign = ConstantAPIProject.flgSign;

    @Before("@apiproj_nonalfa_adv_sukses")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresPay = Step2Payment.lresPay;
            lresPayXml = Step2Payment.lresPayXml;
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}) API Advice APIProject Non Alfa")
    public void valid_request_api_advice_api_project_non_alfa(String paymentStatus, String custId
            ,String productId) {
        if (paymentStatus.equals("SUSPECT") && flgDlyAdv == 0) {
            System.out.println("Delay Start Advice Scenario");
            addFunc.getDelay(60000);
            flgDlyAdv = 1;
            System.out.println("Start Advice Scenario");
        }

        ResPaymentXml tmpResPay = lresPayXml.get(custId);
        String partnerId = tmpResPay.getPartnerid();
        String terminalId = tmpResPay.getTerminalid();
        String trackingRef = tmpResPay.getTrackingref();
        String trxId = tmpResPay.getTrxid();
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjHelp.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId
                , custId, "", trackingRef, terminalId, secretKeyNonAlfa, flgSign);

        ReqAdviceXml req =  new ReqAdviceXml(trxId, dateTimeReq, partnerId, productId, custId, trackingRef
                , terminalId, signature);
        reqAdvXml = req;
        scenario.log("Req Body: " + gson.toJson(reqAdvXml));
    }

    @When("Partner hit API Advice APIProject Non Alfa")
    public void partner_hit_api_advice_api_project_non_alfa() {
        ResAdviceXml res = new ResAdviceXml();
        Reqdata<ReqAdviceXml> rd = new Reqdata<ReqAdviceXml>();
        rd.setdata(reqAdvXml);

        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", xml, urlAdviceNonAlfa
                , "application/xml", null, 0, 30000);
        if (resApi.getRc().equals("00")) {
            Type tk = new TypeToken<Reqdata<ResAdviceXml>>() {}.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getDataJson());
            Reqdata<ResAdviceXml> rt = gson.fromJson(jsonRes.toString(),tk) ;

            res = rt.getdata();
        } else {
            res.setRc("5");
            res.setRcdesc("Terjadi gangguan di sisi biller");
        }
        resAdvXml = res;
    }

    @Then("Partner gets response {string} and response desc {string} API Advice APIProject Non Alfa")
    public void partner_gets_response_and_response_desc_api_advice_api_project_non_alfa(String rc, String desc) {
        assertEquals(resAdvXml.getRc(), rc);
        assertEquals(resAdvXml.getRcdesc(), desc);
        scenario.log("Res Body: " + gson.toJson(resAdvXml));
    }
}
