package services.apiprojectnonalfa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constant.ConstantAPIProject;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import lombok.Getter;
import model.ResUrl2;
import model.apiproject.Reqdata;
import model.newapiproj.ResReversalXml;
import model.newapiproj.ReqReversalXml;
import org.json.JSONObject;
import org.json.XML;
import utility.Rand;

import java.lang.reflect.Type;

import static helper.APIHelper.getHitAPIApiProject;

public class ReversalApiproj {
    Scenario scenario;
    // Helper
    Helper help = new Helper();
    ApiProjectHelper apiProjectHelper = new ApiProjectHelper();
    String secretKey = ConstantAPIProject.secretKeyNonAlfa;
    @Getter
    String urlReversal = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjRevNonAlfa;
    Gson gson = new Gson();
    Rand r = new Rand();

    // Data
    @Getter
    ReqReversalXml reqRev;
    @Getter
    ResReversalXml resRev;
    @Getter ResUrl2 resApiInfo;

    public ReversalApiproj(Scenario scenario) {
        this.scenario = scenario;
    }

    public void generateRequest(
            String trackingRef,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            String totalAmount,
            int sigMethod)
    {
        String trxId = r.getRandomTrxId("","");
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjectHelper.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId
            , customerId, totalAmount, trackingRef, terminalId, secretKey, sigMethod);;

        ReqReversalXml req = new ReqReversalXml(trxId, dateTimeReq, partnerId, productId,  customerId
                , trackingRef, terminalId, signature);
        reqRev = req;
        System.out.println("Req Body: " + gson.toJson(reqRev));
    }

    public void hitRequest() {
        ResReversalXml res = new ResReversalXml();
        Reqdata<ReqReversalXml> rd = new Reqdata<ReqReversalXml>();
        rd.setdata(reqRev);


        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);
        ResUrl2 resApi = testHitRequest(xml);
        if (resApi.getExceptionMessage().isEmpty()) {
                Type tk = new TypeToken<Reqdata<ResReversalXml>>() {
            }.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getStringBody());
            Reqdata<ResReversalXml> rt = gson.fromJson(jsonRes.toString(), tk);

            res = rt.getdata();
            resRev = res;
        }
        resApiInfo = resApi;
    }

    private ResUrl2 testHitRequest(String xml){
        return getHitAPIApiProject(scenario, "POST", "", xml, urlReversal
                , "application/xml", null, 0, 30);
    }
}
