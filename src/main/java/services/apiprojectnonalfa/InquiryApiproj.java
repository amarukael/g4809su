package services.apiprojectnonalfa;

import static helper.APIHelper.*;

import java.lang.reflect.Type;

import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import constant.ConstantAPIProject;
import helper.ApiProjectHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import lombok.Getter;
import model.ResUrl2;
import model.apiproject.Reqdata;
import model.newapiproj.ReqInquiryXml;
import model.newapiproj.ResInquiryXml;
import utility.Rand;

public class InquiryApiproj {
    Scenario scenario;
    // Helper
    Helper help = new Helper();
    ApiProjectHelper apiProjectHelper = new ApiProjectHelper();
    String secretKey = ConstantAPIProject.secretKeyNonAlfa;
    @Getter
    String urlInquiry = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjInqNonAlfa;
    Gson gson = new Gson();
    Rand r = new Rand();

    // Data
    @Getter
    ReqInquiryXml reqInq;
    @Getter
    ResInquiryXml resInq;
    // Tambahan
    @Getter
    ResUrl2 resApiInfo;

    public InquiryApiproj(Scenario scenario) {
        this.scenario = scenario;
    }

    public void generateRequest(
            String trackingRef,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            int isMD5New) {
        String trxId = r.getRandomTrxId("", "");
        String trxDate = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjectHelper.getSignNonAlfa(trxId, trxDate, partnerId, productId, customerId, "",
                trackingRef, terminalId, secretKey, isMD5New);
        //
        // if (sigMethod.equalsIgnoreCase("MD5")) {
        // signature = MD5.generateMD5Signature(partnerId + secretKey);
        // } else if (sigMethod.equalsIgnoreCase("MD5New")) {
        // String concatenatedstring = trxId + partnerId + productId + customerId +
        // trackingRef + terminalId;
        // signature = MD5.generateMD5Signature(concatenatedstring + secretKey);
        // }

        ReqInquiryXml req = new ReqInquiryXml(
                trxId,
                trxDate,
                partnerId,
                productId,
                customerId,
                trackingRef,
                terminalId,
                signature);
        reqInq = req;
    }

    public void hitRequest() {
        ResInquiryXml res = new ResInquiryXml();
        Reqdata<ReqInquiryXml> rd = new Reqdata<>();
        rd.setdata(reqInq);

        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);
        ResUrl2 resApi = testHitRequest(xml);
        if (resApi.getExceptionMessage().isEmpty()) {
            Type tk = new TypeToken<Reqdata<ResInquiryXml>>() {
            }.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getStringBody());
            Reqdata<ResInquiryXml> rt = gson.fromJson(jsonRes.toString(), tk);

            res = rt.getdata();
            resInq = res;
        }
        resApiInfo = resApi;
    }

    private ResUrl2 testHitRequest(String xml) {
        return getHitAPIApiProject(scenario, "POST", "", xml, urlInquiry, "application/xml", null, 0, 30);
    }
}
