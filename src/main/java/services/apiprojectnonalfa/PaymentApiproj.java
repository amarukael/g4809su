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
import model.newapiproj.ReqPaymentXml;
import model.newapiproj.ResPaymentXml;
import org.json.JSONObject;
import org.json.XML;
import utility.Rand;

import java.lang.reflect.Type;

import static helper.APIHelper.getHitAPIApiProject;

public class PaymentApiproj {
    Scenario scenario;
    // Helper
    Helper help = new Helper();
    ApiProjectHelper apiProjectHelper = new ApiProjectHelper();
    String secretKey = ConstantAPIProject.secretKeyNonAlfa;
    @Getter
    String urlPayment = ConstantAPIProject.urlAPIProjNonAlfa + ConstantAPIProject.urlAPIProjPayNonAlfa;
    Gson gson = new Gson();
    Rand r = new Rand();

    // Data
    @Getter
    ReqPaymentXml reqPay;
    @Getter
    ResPaymentXml resPay;

    @Getter ResUrl2 resApiInfo;

    public PaymentApiproj(Scenario scenario) {
        this.scenario = scenario;
    }

    public void generateRequest(
            String trackingRef,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            String totalAmount,
            int isMD5New) {
        String trxId = r.getRandomTrxId("","");
        String dateTimeReq = help.createTrxDate("yyyy-MM-dd HH:mm:ss");
        String signature = apiProjectHelper.getSignNonAlfa(trxId, dateTimeReq, partnerId, productId
                , customerId, totalAmount, trackingRef, terminalId, secretKey, isMD5New);

        ReqPaymentXml req = new ReqPaymentXml(trxId, dateTimeReq, partnerId, productId, customerId, totalAmount
                , trackingRef, terminalId, signature);
        reqPay = req;
        scenario.log("Req Body: " + gson.toJson(reqPay));
    }

    public void hitRequest() {
        ResPaymentXml res = new ResPaymentXml();
        Reqdata<ReqPaymentXml> rd = new Reqdata<>();
        rd.setdata(reqPay);

        JSONObject json = new JSONObject(gson.toJson(rd));
        String xml = XML.toString(json);
        ResUrl2 resApi = testHitRequest(xml);
        if (resApi.getExceptionMessage().isEmpty()) {
            Type tk = new TypeToken<Reqdata<ResPaymentXml>>() {
            }.getType();
            JSONObject jsonRes = XML.toJSONObject(resApi.getStringBody());
            Reqdata<ResPaymentXml> rt = gson.fromJson(jsonRes.toString(), tk);

            res = rt.getdata();
            resPay = res;
        }
        resApiInfo = resApi;
    }

    private ResUrl2 testHitRequest(String xml){
        return getHitAPIApiProject(scenario, "POST", "", xml, urlPayment
                , "application/xml", null, 0, 30);
    }
}
