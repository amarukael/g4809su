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
import model.newapiproj.ReqAdviceXml;
import model.newapiproj.ResAdviceXml;
import org.json.JSONObject;
import org.json.XML;
import utility.Rand;

import java.lang.reflect.Type;

import static helper.APIHelper.getHitAPIApiProject;

public class AdviceApiproj {
    Scenario scenario;
    // Helper
    Helper help = new Helper();
    ApiProjectHelper apiProjectHelper = new ApiProjectHelper();
    String secretKey = ConstantAPIProject.secretKeyNonAlfa;
    @Getter
    String urlAdvice = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjAdvNonAlfa;
    Gson gson = new Gson();
    Rand r = new Rand();

    // Data
    @Getter
    ReqAdviceXml reqAdv;
    @Getter
    ResAdviceXml resAdv;

    @Getter
    ResUrl2 resApiInfo;

    public AdviceApiproj(Scenario scenario) {
        this.scenario = scenario;
    }

    public void generateRequest(
            String trackingRef,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            int isMD5New
    )
    {
        String trxId = r.getRandomTrxId(productId,"");
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjectHelper.getSignNonAlfa(trxId, trxDate, partnerId, productId
                , customerId, "", trackingRef, terminalId, secretKey, isMD5New);
//
//        if (sigMethod.equalsIgnoreCase("MD5")) {
//            signature = MD5.generateMD5Signature(partnerId + secretKey);
//        } else if (sigMethod.equalsIgnoreCase("MD5New")) {
//            String concatenatedstring = trxId + partnerId + productId + customerId + trackingRef + terminalId;
//            signature = MD5.generateMD5Signature(concatenatedstring + secretKey);
//        }

        ReqAdviceXml req = new ReqAdviceXml(
                trxId,
                trxDate,
                partnerId,
                productId,
                customerId,
                trackingRef,
                terminalId,
                signature
        );
        reqAdv = req;
    }

    public void hitRequest() {
        ResAdviceXml res = new ResAdviceXml();
        Reqdata<ReqAdviceXml> rd = new Reqdata<>();
        rd.setdata(reqAdv);

        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);

//        ResUrl resApi = APIHelper.getHitAPI(scenario, "", xml, urlAdvice
//                , "application/xml", null, 0, 30000);
//        if (resApi.getRc().equals("00")) {
//            Type tk = new TypeToken<Reqdata<ResInquiryXml>>() {}.getType();
//            JSONObject jsonRes = XML.toJSONObject(resApi.getDataJson());
//            Reqdata<ResAdviceXml> rt = gson.fromJson(jsonRes.toString(),tk) ;
//
//            res = rt.getdata();
//        } else {
//            res.setRc("5");
//            res.setRcdesc("Terjadi gangguan di sisi biller");
//        }
//        resAdv = res;
        ResUrl2 resApi = testHitRequest(xml);
        if (resApi.getExceptionMessage().isEmpty()) {
            Type tk = new TypeToken<Reqdata<ResAdviceXml>>() {
            }.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getStringBody());
            Reqdata<ResAdviceXml> rt = gson.fromJson(jsonRes.toString(), tk);

            res = rt.getdata();
            resAdv = res;
        }
        resApiInfo = resApi;
    }

    private ResUrl2 testHitRequest(String xml){
        return getHitAPIApiProject(scenario, "POST", "", xml, urlAdvice
                , "application/xml", null, 0, 30);
    }
}
