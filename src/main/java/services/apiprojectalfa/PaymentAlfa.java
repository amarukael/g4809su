package services.apiprojectalfa;

import com.google.gson.Gson;
import constant.ConstantAPIProject;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import lombok.Getter;
import model.ResUrl2;
import model.apiproject.ReqPayment;
import model.newapiproj.ResPayment;
import org.json.JSONObject;
import utility.JSONtoURLEncode;
import utility.Rand;

import static helper.APIHelper.getHitAPIApiProject;

public class PaymentAlfa  {
    Scenario scenario;
    // Helper
    Helper help = new Helper();
    ApiProjectHelper apiProjectHelper = new ApiProjectHelper();
    String secretKey = ConstantAPIProject.secretKey;
    @Getter
    String urlPayment = ConstantAPIProject.urlAPIProj + ConstantAPIProject.urlAPIProjPay;
    Gson gson = new Gson();
    Rand r = new Rand();

    // Data
    @Getter
    String requestURLString;
    @Getter
    ReqPayment reqPay;
    @Getter
    ResPayment resPay;
    @Getter
    ResUrl2 resApiInfo;

    public PaymentAlfa(Scenario scenario) {
        this.scenario = scenario;
    }


    public void generateRequest(
            String AgentTrxID,
            String AgentID,
            String AgentPIN,
            String AgentStoreID,
            String ProductID,
            String CustomerId,
            String PaymentPeriod,
            String amount,
            String charge,
            String totalAmount,
            String adminFee) {
        String agentId = AgentID;
        String agentPin = AgentPIN;
        String trxId = AgentTrxID;
        String storeId = AgentStoreID;
        String customerId = CustomerId;
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss").trim();
        String paymentPeriod = PaymentPeriod;
        //New addition
        String productId = ProductID;

        String signature = apiProjectHelper.getSignPay(agentId, agentPin, storeId, productId, customerId, trxId, dateTimeReq
                , paymentPeriod, amount, charge, totalAmount,adminFee, secretKey);

        ReqPayment req = new ReqPayment(agentId, agentPin, trxId, storeId, productId, customerId, dateTimeReq
                , paymentPeriod, amount, charge, totalAmount, adminFee, signature);
        reqPay = req;
        System.out.println("Req Body: " + gson.toJson(reqPay));
    }

    public void hitRequest() {
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqPay));
        String paramString = jr.jsonToURLEncoding(json);
        requestURLString = urlPayment+paramString;

        ResUrl2 resApi = testHitRequest(paramString);
        if (resApi.getIsSuccessful()) {
            resPay = new ResPayment(resApi.getStringBody());
        }
        resApiInfo = resApi;
    }

    public String getRequestBody(){
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqPay));
        String paramString = jr.jsonToURLEncoding(json);
        return urlPayment + "?" + paramString;
    }

    public String getResponseBody(){
        return resPay.getBody();
    }

    private ResUrl2 testHitRequest(String paramString){
        return getHitAPIApiProject(scenario, "GET", "", paramString,
                urlPayment, "", null, 0, 30 );
    }
}
