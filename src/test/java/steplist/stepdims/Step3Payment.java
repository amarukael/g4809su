package steplist.stepdims;

import com.google.gson.Gson;
import constant.ConstantDims;
import database.DimsDataHelper;
import helper.APIHelper;
import helper.DimsHelper;
import helper.Helper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ResUrl;
import model.dims.AddtDataInfo;
import model.dims.Amount;
import model.dims.authorization.ResAuth;
import model.dims.inquiry.ResInquiry;
import model.dims.payment.ReqPayment;
import model.dims.payment.ResPayment;
import utility.AddFunction;
import utility.Rand;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.testng.Assert.assertEquals;

public class Step3Payment {
    private static Map<String, ResAuth> lresAuth;
    private static Map<String, ResInquiry> lresInq;
    private static String externalId;
    Gson gsonLog = new Gson();
    Gson gson = new Gson();
    DimsHelper dimsHelp = new DimsHelper();
    DimsDataHelper db = new DimsDataHelper();
    Helper h = new Helper();
    Rand rand = new Rand();
    AddFunction addFunc = new AddFunction();
    public static Map<String, ResPayment> lresPay = new HashMap<String, ResPayment>();
    private static ResAuth resAuth;
    private static ResInquiry resInq;
    private static String OriginalPartnerRefNo;
    private static ReqPayment reqPay;
    private static ResPayment resPay = new ResPayment();
    private static Map<String, ReqPayment> lreqPay = new HashMap<>();
    private static Map<String, String> headers = new HashMap<>();
    private static String tmpHttpCode;
    private static String urlPay = ConstantDims.urlDims + ConstantDims.urlDimsPay;
    private static String relativeUrl = ConstantDims.urlDimsPay;
    private static String secretKey = "";
    private static String tmpCondition = "";
    Scenario scenario = Hooks.scenario;
    private static int flgSetUp;

    @Before("@pay_sukses or @pay_header_failed or @pay_param_failed or @payment_sukses_disbursement_type")
    public void setUpData() {
        if (flgSetUp == 0) {
            lresAuth = Step1Auth.lresAuth;
            resAuth = lresAuth.get("SUCCESS");
            lresInq = Step2Inquiry.lresInq;
            externalId = Step2Inquiry.externalId;
//            resInq = lresInq.get("SUCCESS");
//            lresInq.remove("SUCCESS");
            lresPay.clear();
//            secretKey = db.getSecretKeyByToken(resAuth.getAccessToken());
            secretKey = "ids123456";
            flgSetUp = 1;
        }
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}" +
            ", {string}, {string}, {string}, {string}) API Payment DIMS")
    public void valid_request_api_payment_dims(String productId, String bankCode
            , String accountNo, String accountNm, String custType, String custResidence, String catagoryPurchCode, String purpose
            , String currency, String amnt, String rcvIdNum, String rcvEmail) {
        addFunc.getDelay(1000);
        accountNm = lresInq.get("SUCCESS").getAccountName();
        String tmpOriginalPartnerRefNo = lresInq.get("SUCCESS").getOriginalPartnerReferenceNo();
        buildReqPay(accountNm, productId, bankCode, accountNo, custType, custResidence, rcvIdNum
                , rcvEmail, catagoryPurchCode, purpose, currency, amnt, tmpOriginalPartnerRefNo
                , "", "");
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}" +
            ", {string}, {string}, {string}, {string}) API Payment DIMS with Partner {string}")
    public void valid_request_api_payment_dims_with_partner(String productId, String bankCode
            , String accountNo, String accountNm, String custType, String custResidence, String catagoryPurchCode
            , String purpose, String currency, String amnt, String rcvIdNum, String rcvEmail, String partnerId) {
        addFunc.getDelay(1000);
        tmpCondition = partnerId;
        secretKey = db.getSecretKeyByToken(Step1Auth.lresAuth.get(partnerId).getAccessToken());
        if (Step2Inquiry.lresInq.containsKey(partnerId)) {
            accountNm = Step2Inquiry.lresInq.get(partnerId).getAccountName();
        }

        String tmpOriginalPartnerRefNo = "";
        if (Step2Inquiry.lresInq.containsKey(partnerId)) {
            tmpOriginalPartnerRefNo = Step2Inquiry.lresInq.get(partnerId).getOriginalPartnerReferenceNo();
        }
        buildReqPay(accountNm, productId, bankCode, accountNo, custType, custResidence, rcvIdNum
                , rcvEmail, catagoryPurchCode, purpose, currency, amnt, tmpOriginalPartnerRefNo
                , "", tmpCondition);
    }

    @Given("Valid Request param {string} \\({string}, {string}, {string}, {string}, {string}, {string}, {string}" +
            ", {string}, {string}, {string}, {string}, {string}, {string}) API Payment DIMS")
    public void valid_request_param_api_payment_dims(String condition, String descTest, String productId
            , String bankCode, String accountNo, String accountNm, String custType, String custResidence
            , String catagoryPurchCode, String purpose, String currency, String amnt
            , String rcvIdNum, String rcvEmail) {
        addFunc.getDelay(1000);
        String finalCondition = condition + "_" + descTest;
        tmpCondition = finalCondition;
        if (tmpCondition.equals("bankCode_Inter") || tmpCondition.equals("bankCode_Intra")) {
            System.out.println("Start Delay Scenario...");
            h.delay(60000);
            System.out.println("End Delay Scenario...");
        }

        if(lresInq.containsKey(finalCondition)) {
            if (!lresInq.get(finalCondition).getAccountName().isEmpty())
                accountNm = lresInq.get(finalCondition).getAccountName();
        }

        String tmpOriginalPartnerRefNo = "";
        if (lresInq.containsKey(finalCondition)) {
            tmpOriginalPartnerRefNo = lresInq.get(finalCondition).getOriginalPartnerReferenceNo();
        } else {
            tmpOriginalPartnerRefNo = resInq.getOriginalPartnerReferenceNo();
        }
        buildReqPay(accountNm, productId, bankCode, accountNo, custType, custResidence, rcvIdNum
                , rcvEmail, catagoryPurchCode, purpose, currency, amnt, tmpOriginalPartnerRefNo
                , "", tmpCondition);
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}" +
            ", {string}, {string}, {string}, {string}) API Payment DIMS With Invalid header {string}")
    public void valid_request_api_payment_dims_with_invalid_header(String productId, String bankCode
            , String accountNo, String accountNm, String custType, String custResidence, String catagoryPurchCode, String purpose
            , String currency, String amnt, String rcvIdNum, String rcvEmail, String condition) {
        addFunc.getDelay(1000);
        accountNm = lresInq.get("SUCCESS").getAccountName();
        String tmpOriginalPartnerRefNo = lresInq.get("SUCCESS").getOriginalPartnerReferenceNo();
        buildReqPay(accountNm, productId, bankCode, accountNo, custType, custResidence, rcvIdNum
                , rcvEmail, catagoryPurchCode, purpose, currency, amnt, tmpOriginalPartnerRefNo
                , condition, "");
    }

    @Given("Invalid Request param {string} \\({string}, {string}, {string}, {string}, {string}, {string}, {string}" +
            ", {string}, {string}, {string}, {string}, {string}, {string}) API Payment DIMS")
    public void invalid_request_param_api_payment_dims(String condition, String descTest, String productId
            , String bankCode, String accountNo, String accountNm, String custType, String custResidence
            , String catagoryPurchCode, String purpose, String currency, String amnt
            , String rcvIdNum, String rcvEmail) {
        addFunc.getDelay(1000);
        tmpCondition = condition;
        if (condition.equals("authNobu") || condition.equals("authPermata")
                || condition.equals("invalidSignPay")
                || (condition.equals("amount") && descTest.equals("Insufficient"))) {
            System.out.println("Delay Start Scenario");
            addFunc.getDelay(25000);
            if ((condition.equals("amount") && descTest.equals("Insufficient")))
                tmpCondition = condition + "_" + descTest;

            System.out.println("Start Scenario");
        }

        int flgPayProses = 1;
        if (condition.equals("xEternalID")) flgPayProses = 2;
        for (int i = 0; i < flgPayProses; i++) {
            if (condition.equals("xEternalID"))
                tmpCondition = tmpCondition + "_" + (i + 1);

            // get accountNm by resInq
            if (condition.equals("xEternalID")) {
                accountNm = lresInq.get("xEternalID_conflict_" + (i + 1)).getAccountName();
            } else {
                if (lresInq.containsKey(accountNo)) {
                    accountNm = lresInq.get(accountNo).getAccountName();
                }
            }

            // get originalRefNo by resInq
            String tmpOriginalPartnerRefNo = "";
            if (condition.equals("xEternalID")) {
                tmpOriginalPartnerRefNo = lresInq.get("xEternalID_conflict_" + (i + 1)).getOriginalPartnerReferenceNo();
            } else {
                if (lresInq.containsKey(accountNo)) {
                    tmpOriginalPartnerRefNo = lresInq.get(accountNo).getOriginalPartnerReferenceNo();
                } else {
                    tmpOriginalPartnerRefNo = lresInq.get("SUCCESS").getOriginalPartnerReferenceNo();
                }
            }

            // get originalPartnerRefNo by condition
            if (condition.equals("originalPartnerReferenceNo") && descTest.equals("Empty")) {
                tmpOriginalPartnerRefNo = "";
            } else if (condition.equals("originalPartnerReferenceNo") && descTest.equals("Wrong")) {
                tmpOriginalPartnerRefNo = "99" + tmpOriginalPartnerRefNo;
            } else if (condition.equals("originalPartnerReferenceNo") && descTest.equals("Double")) {
                tmpOriginalPartnerRefNo = OriginalPartnerRefNo;
            }
            buildReqPay(accountNm, productId, bankCode, accountNo, custType, custResidence, rcvIdNum
                    , rcvEmail, catagoryPurchCode, purpose, currency, amnt, tmpOriginalPartnerRefNo
                    , "", tmpCondition);
        }
    }

    @When("Partner hits API Payment DIMS")
    public void partner_hits_api_payment_dims() {
        ResPayment res = new ResPayment();
        if (tmpCondition.equals("xEternalID")) {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            Future<ResUrl> process1Future = executor.submit(() -> {
                ResUrl resApi = APIHelper.getHitAPI(scenario, ""
                        , gson.toJson(lreqPay.get("xEternalID_1")), urlPay
                        , "application/json", headers, 1, 20000);
                return resApi;
            });

            Future<ResUrl> process2Future = executor.submit(() -> {
                ResUrl resApi = APIHelper.getHitAPI(scenario, ""
                        , gson.toJson(lreqPay.get("xEternalID_1")), urlPay
                        , "application/json", headers, 1, 20000);
                return resApi;
            });

            ResUrl resApi1 = null;
            ResUrl resApi2 = null;
            try {
                resApi1 = process1Future.get();
                resApi2 = process2Future.get();
            } catch (Exception e) {
                System.out.println("Error when proses result future");
            } finally {
                executor.shutdown();
            }
        } else {
            if (!tmpCondition.isEmpty())
                reqPay = lreqPay.get(tmpCondition);

            ResUrl resApi = APIHelper.getHitAPI(scenario, "", gson.toJson(reqPay), urlPay
                    , "application/json", headers, 1, 20000);
            if (resApi.getRc().equals("00")) {
                res = gson.fromJson(resApi.getDataJson(), ResPayment.class);
                resPay = res;
                tmpHttpCode = resApi.getHttpCode();
            } else {
                if (resApi.getDataJson() == null) {
                    res.setResponseCode("4001601");
                    res.setResponseMessage("Bad Request");
                    resPay = res;
                    tmpHttpCode = "400";
                } else {
                    res = gson.fromJson(resApi.getDataJson(), ResPayment.class);
                    resPay = res;
                    tmpHttpCode = resApi.getHttpCode();
                }
            }
        }
    }

    @Then("Partner gets the Success response {string} and http code {string} API Payment DIMS")
    public void partner_gets_the_success_response_api_payment_dims(String rc, String httpCode) {
        assertEquals(resPay.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        if (!tmpCondition.isEmpty()) {
            lresPay.put(tmpCondition, resPay);

            tmpCondition = "";
        } else {
            OriginalPartnerRefNo = lresInq.get("SUCCESS").getOriginalPartnerReferenceNo();
            lresPay.put("SUCCESS", resPay);
        }

        scenario.log("Res Body: " + gsonLog.toJson(resPay));
    }

    @Then("Partner gets the Failed response {string} and http code {string} API Payment DIMS")
    public void partner_gets_the_failed_response_api_payment_dims(String rc, String httpCode) {
        if (tmpCondition.equals("authNobu") || tmpCondition.equals("authPermata")
                || tmpCondition.equals("invalidSignPay") || tmpCondition.equals("amount_Insufficient")) {
            System.out.println("Delay End Scenario");
            addFunc.getDelay(25000);
            System.out.println("End Scenario");
        }

        tmpCondition = "";
        assertEquals(resPay.getResponseCode(), rc);
        assertEquals(tmpHttpCode, httpCode);
        scenario.log("Res Body: " + gsonLog.toJson(resPay));
    }

    private void buildReqPay(String accountNm, String productId, String bankCode, String accountNo
            , String custType, String custResidence, String rcvIdNum, String rcvEmail
            , String catagoryPurchCode, String purpose, String currency, String amnt
            , String originalPartnerRefNo, String condition, String flagReq) {
        AddtDataInfo addtDataInfo = new AddtDataInfo(bankCode, accountNo, accountNm, custType, custResidence
                , rcvIdNum, rcvEmail, catagoryPurchCode, purpose);
        Amount amount = new Amount(currency, amnt);
        ReqPayment req = new ReqPayment(productId, originalPartnerRefNo, addtDataInfo, amount);
        headers = dimsHelp.getHeaderGlobal(condition, resAuth.getAccessToken(), gson.toJson(req)
                , relativeUrl, externalId, secretKey);

        scenario.log("Headers: " + gsonLog.toJson(headers));
        if (flagReq.isEmpty()) {
            reqPay = req;
            scenario.log("Req Body: " + gsonLog.toJson(reqPay));
        } else {
            lreqPay.put(flagReq, req);
            scenario.log("Req Body: " + gsonLog.toJson(lreqPay.get(flagReq)));
        }
    }
}
