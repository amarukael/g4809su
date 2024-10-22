package services.apiprojectalfa;

import com.google.gson.Gson;
import constant.ConstantAPIProject;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import lombok.Getter;
import model.ResUrl2;
import model.apiproject.ReqReversal;
import model.newapiproj.ResReversal;
import org.json.JSONObject;
import utility.JSONtoURLEncode;
import utility.Rand;

import static helper.APIHelper.getHitAPIApiProject;

public class ReversalAlfa  {
    Scenario scenario;
    // Helper
    Helper help = new Helper();
    ApiProjectHelper apiProjectHelper = new ApiProjectHelper();
    String secretKey = ConstantAPIProject.secretKey;
    @Getter
    String urlReversal = ConstantAPIProject.urlAPIProj + ConstantAPIProject.urlAPIProjRev;
    Gson gson = new Gson();
    Rand r = new Rand();

    // Data
    @Getter
    String requestURLString;
    @Getter
    ReqReversal reqRev;
    @Getter
    ResReversal resRev;
    @Getter
    ResUrl2 resApiInfo;

    public ReversalAlfa(Scenario scenario) {
        this.scenario = scenario;
    }

    public void generateRequest(
            String AgentTrxID,
            String AgentID,
            String AgentPIN,
            String AgentStoreID,
            String CustomerId,
            String productId)
    {
        String agentId = AgentID;
        String agentPin = AgentPIN;
        String trxId = AgentTrxID;
        String storeId = AgentStoreID;
        String customerId = CustomerId;
        String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss").trim();
        String signature = apiProjectHelper.getSignRev(agentId, agentPin, storeId, productId, customerId, trxId, dateTimeReq
                , secretKey);

        ReqReversal req = new ReqReversal(agentId, agentPin, trxId, storeId, productId, customerId, dateTimeReq
                , signature);
        reqRev = req;
        System.out.println("Req Body: " + gson.toJson(reqRev));
    }

    public void hitRequest() {
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqRev));
        String paramString = jr.jsonToURLEncoding(json);
        requestURLString = urlReversal+paramString;

        ResUrl2 resApi = testHitRequest(paramString);
        if (resApi.getIsSuccessful()) {
            resRev = new ResReversal(resApi.getStringBody());
        }
        resApiInfo = resApi;
    }

    private ResUrl2 testHitRequest(String paramString){
        return getHitAPIApiProject(scenario, "GET", "", paramString,
                urlReversal, "", null, 0, 30 );
    }

    public String getResponseBody(){
        return resRev.getBody();
    }
}
