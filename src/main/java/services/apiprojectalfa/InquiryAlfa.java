package services.apiprojectalfa;

import com.google.gson.Gson;
import constant.ConstantAPIProject;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import lombok.Getter;
import model.ResUrl2;
import model.newapiproj.ReqInquiry;
import model.newapiproj.ResInquiry;
import org.json.JSONObject;
import utility.JSONtoURLEncode;
import utility.Rand;

import static helper.APIHelper.getHitAPIApiProject;

public class InquiryAlfa  {
    Scenario scenario;
    // Helper
    Helper help = new Helper();
    ApiProjectHelper apiProjectHelper = new ApiProjectHelper();
    String secretKey = ConstantAPIProject.secretKey;
    @Getter
    String urlInquiry = ConstantAPIProject.urlAPIProj + ConstantAPIProject.urlAPIProjInq;
    Gson gson = new Gson();
    Rand r = new Rand();

    // Data
    @Getter
    String requestURLString;
    @Getter
    ReqInquiry reqInq;
    @Getter
    ResInquiry resInq;
    @Getter
    ResUrl2 resApiInfo;

    public InquiryAlfa(Scenario scenario) {
        this.scenario = scenario;
    }

    public void generateRequest(
            String AgentTrxID,
            String agentId,
            String agentPin,
            String storeId,
            String productId,
            String customerId)
    {
            String trxId = AgentTrxID;
            String dateTimeReq = help.createTrxDate("yyyyMMddHHmmss");
            String signature = apiProjectHelper.getSignInq(agentId, agentPin, storeId, productId, customerId, trxId
                    , dateTimeReq, secretKey);

            ReqInquiry req = new ReqInquiry(agentId, agentPin, trxId
                    , storeId, productId, customerId, dateTimeReq, signature);

            reqInq = req;
    }

    public void hitRequest() {
        JSONtoURLEncode jr = new JSONtoURLEncode();
        JSONObject json = new JSONObject(gson.toJson(reqInq));
        String paramString = jr.jsonToURLEncoding(json);
        requestURLString = urlInquiry+paramString;

        ResUrl2 resApi = testHitRequest(paramString);
        if (resApi.getIsSuccessful()) {
            resInq = new ResInquiry(resApi.getStringBody());
        }
        resApiInfo = resApi;
    }

    public String getTrackingRef() {
        return reqInq.getAgentTrxID();
    }
    public String getResponseBody(){
        return resInq.getBody();
    }

    private ResUrl2 testHitRequest(String paramString){
        return getHitAPIApiProject(scenario, "GET", "", paramString,
                urlInquiry, "", null, 0, 30 );
    }
}
