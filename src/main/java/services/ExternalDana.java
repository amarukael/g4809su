package services;

import com.google.gson.Gson;
import constant.ConstantExternal;
import helper.APIHelper;
import helper.Helper;
import io.cucumber.java.Scenario;
import model.ResUrl;
import model.ResValidasi;
import model.external.dana.*;
import org.json.JSONObject;
import utility.Rand;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExternalDana {
    Helper help = new Helper();
    Rand r = new Rand();
    static Gson gson = new Gson();
    String privKey = ConstantExternal.danaPrivateKey;
    String urlInquiry = ConstantExternal.danaInq;
    String urlOrderCreate = ConstantExternal.danaOrderCreate;
    String urlOrderDetail = ConstantExternal.danaOrderDetail;
    int timeOut = ConstantExternal.danaTimeOut;
    public static Map<String, ReqInquiry> lReqInqData = new LinkedHashMap<>();
    public static Map<String, ResInquiry> lInqData = new LinkedHashMap<>();
    public static Map<String, ReqOrderCreate> lReqOCData = new LinkedHashMap<>();
    public static Map<String, ResOrderCreate> lOCData = new LinkedHashMap<>();
    public static Map<String, ReqOrderDetail> lReqODData = new LinkedHashMap<>();
    public static Map<String, ResOrderDetail> lODData = new LinkedHashMap<>();
    public static Scenario scenario;

    public void getInq(String tc, String productId, String customerId) {
        ResInquiry resInq = new ResInquiry();
        //build request
        List<DestinationInfo> LInqDest = new ArrayList<>();
        DestinationInfo inqDest = new DestinationInfo(customerId);
        LInqDest.add(inqDest);
        ReqInqBody body =  new ReqInqBody(LInqDest, productId);
        ReqHeadBodyInquiry inq = new ReqHeadBodyInquiry(buildHead(), body);

        //req inq
        String minify = gson.toJson(inq);
        String signature = "";
        try {
            signature = help.Sign256RSA(minify, privKey);
        } catch (Exception e) {
            System.out.println(e);
        }

        ReqInquiry reqInq = new ReqInquiry(inq, signature);
        lReqInqData.put(tc, reqInq);
//        scenario.log("Req Inquiry: " + gson.toJson(reqInq));

        //hit api
        String paramString = gson.toJson(reqInq);
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", paramString, urlInquiry
                , "application/json", null, 0, timeOut);
        resInq = gson.fromJson(resApi.getDataJson(), ResInquiry.class);
        lInqData.put(tc, resInq);
//        scenario.log("Resp Inquiry: " + gson.toJson(resInq));
    }

    public void getOrderCreate(String tc, String productId) {
        ResOrderCreate resOC = new ResOrderCreate();
        //build request
        ResHeadBodyInquiry resInq = lInqData.get(tc).getResponse();
        DestinationInfo destInfo = new DestinationInfo(resInq.getBody().getInquiryResults().get(0).getDestinationInfo().getPrimaryParam());
        Money danaSelling = new Money();
        danaSelling.setValue(resInq.getBody().getInquiryResults().get(0).getTotalAmount().getValue());
        danaSelling.setCurrency(resInq.getBody().getInquiryResults().get(0).getTotalAmount().getCurrency());

        String requestId = help.createTrxDate("yyyyMMddHHmmssXXX") + r.randStrNum(3);
        String extInfo = "{\"inquiryId\":\"" + resInq.getBody().getInquiryResults().get(0).getInquiryId() + "\"}";
        ReqBodyOrderCreate body = new ReqBodyOrderCreate(requestId, productId, destInfo, danaSelling, extInfo);
        ReqHeadBodyOrderCreate oc = new ReqHeadBodyOrderCreate(buildHead(), body);

        //req order create
        String minify = gson.toJson(oc);
        String signature = "";
        try {
            signature = help.Sign256RSA(minify, privKey);
        } catch (Exception e) {
            System.out.println(e);
        }

        ReqOrderCreate reqOC = new ReqOrderCreate(oc, signature);
        lReqOCData.put(tc, reqOC);
//        scenario.log("Req Order Create: " + gson.toJson(reqOC));

        //hit api
        String paramString = gson.toJson(reqOC);
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", paramString, urlOrderCreate
                , "application/json", null, 0, timeOut);
        resOC = gson.fromJson(resApi.getDataJson(), ResOrderCreate.class);
        lOCData.put(tc, resOC);
//        scenario.log("Resp Order Create: " + gson.toJson(resOC));
    }

    public void getOrderDetail(String tc) {
        ResOrderDetail resOD = new ResOrderDetail();
        //build request
        ResHeadBodyOrderCreate resOC = lOCData.get(tc).getResponse();
        OrderDetail odtl = new OrderDetail(null, resOC.getBody().getOrder().getRequestId());
        List<OrderDetail> lOdtl = new ArrayList<>();
        lOdtl.add(odtl);

        ReqBodyOrderDetail body = new ReqBodyOrderDetail(lOdtl);
        ReqHeadBodyOrderDetail od = new ReqHeadBodyOrderDetail(buildHead(), body);

        //req order detail
        String minify = gson.toJson(od);
        String signature = "";
        try {
            signature = help.Sign256RSA(minify, privKey);
        } catch (Exception e) {
            System.out.println(e);
        }
        ReqOrderDetail reqOD = new ReqOrderDetail(od, signature);
        lReqODData.put(tc, reqOD);
//        scenario.log("Req Order Detail: " + gson.toJson(reqOD));

        //hit api
        String paramString = gson.toJson(reqOD);
        ResUrl resApi = APIHelper.getHitAPI(scenario, "", paramString, urlOrderDetail
                , "application/json", null, 0, timeOut);
        resOD = gson.fromJson(resApi.getDataJson(), ResOrderDetail.class);
        lODData.put(tc, resOD);
//        scenario.log("Resp Order Detail: " + gson.toJson(resOD));
    }

    public static ResValidasi validasiResInq(ResInquiry resInq, String tc, String billerId) {
        boolean res = false;
        String remark = "";
        try {
            int baseAmount = Integer.parseInt(resInq.getResponse().getBody().getInquiryResults().get(0).getBaseAmount().getValue());
            int pinaltyAmount = Integer.parseInt(resInq.getResponse().getBody().getInquiryResults().get(0).getFineAmount().getValue());
            int adminFee = Integer.parseInt(resInq.getResponse().getBody().getInquiryResults().get(0).getAdminFee().getValue());
            int totalAmount = Integer.parseInt(resInq.getResponse().getBody().getInquiryResults().get(0).getTotalAmount().getValue());

            if (baseAmount == 0) {
                remark = "Base Amount " + baseAmount;
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }
            if (adminFee == 0) {
                remark = "Admin Fee Amount " + adminFee;
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }

            // setting variable amount
            if (baseAmount > 0) baseAmount = baseAmount/100;
            if (pinaltyAmount > 0) pinaltyAmount = pinaltyAmount/100;
            if (adminFee > 0) adminFee = adminFee/100;
            if (totalAmount > 0) totalAmount = totalAmount/100;

            // validasi
            int tmpJumlah = baseAmount + pinaltyAmount + adminFee;
            if (totalAmount == tmpJumlah) {
                res = true;
            } else {
                remark = "Jumlah totalAmount tidak sesuai dengan (baseAmount+pinaltyAmount+adminFee)";
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }

            if (resInq.getResponse().getBody().getInquiryResults().get(0).getInquiryId() == null
                    || resInq.getResponse().getBody().getInquiryResults().get(0).getInquiryId().isEmpty()) {
                res = false;
                remark = "Param InquiryId tidak ditemukan.";
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ResValidasi resVal = new ResValidasi(res, remark);
        return resVal;
    }

    public static ResValidasi validasiResOrderCreate(ResOrderCreate resOC, String tc, String billerId) {
        boolean res = false;
        String remark = "";
        try {
            JSONObject jObj = new JSONObject(resOC.getResponse().getBody().getOrder().getExtendInfo());
            Money mFine = gson.fromJson(jObj.get("fineAmount").toString(), Money.class);
            Money mAdmin = gson.fromJson(jObj.get("adminFee").toString(), Money.class);
            Money mBaseAmt = gson.fromJson(jObj.get("baseAmount").toString(), Money.class);
            int pinaltyAmount = Integer.parseInt(mFine.getValue());
            int adminFee = Integer.parseInt(mAdmin.getValue());
            int baseAmount = Integer.parseInt(mBaseAmt.getValue());
            int price = Integer.parseInt(
                    resOC.getResponse().getBody().getOrder().getProduct().getPrice().getValue());

            if (baseAmount == 0) {
                remark = "Base Amount " + baseAmount;
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }
            if (adminFee == 0) {
                remark = "Admin Fee Amount " + adminFee;
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }

            // setting variable amount
            if (pinaltyAmount > 0) pinaltyAmount = pinaltyAmount/100;
            if (adminFee > 0) adminFee = adminFee/100;
            if (baseAmount > 0) baseAmount = baseAmount/100;
            if (price > 0) price = price/100;

            // validasi
            int tmpJumlah = baseAmount + pinaltyAmount + adminFee;
            if (price == tmpJumlah) {
                res = true;
            } else {
                remark = "Jumlah price tidak sesuai dengan (baseAmount+pinaltyAmount+adminFee)";
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }

            if (resOC.getResponse().getBody().getOrder().getSerialNumber() == null
                    || resOC.getResponse().getBody().getOrder().getSerialNumber().isEmpty()) {
                res = false;
                remark = "Param SerialNumber tidak ditemukan.";
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ResValidasi resVal = new ResValidasi(res, remark);
        return resVal;
    }

    public static ResValidasi validasiResOrderDetail(ResOrderDetail resOD, String tc, String billerId) {
        boolean res = false;
        String remark = "";
        try {
            JSONObject jObj = new JSONObject(resOD.getResponse().getBody().getOrders().get(0).getExtendInfo());
            Money mFine = gson.fromJson(jObj.get("fineAmount").toString(), Money.class);
            Money mAdmin = gson.fromJson(jObj.get("adminFee").toString(), Money.class);
            Money mBaseAmt = gson.fromJson(jObj.get("baseAmount").toString(), Money.class);
            int pinaltyAmount = Integer.parseInt(mFine.getValue());
            int adminFee = Integer.parseInt(mAdmin.getValue());
            int baseAmount = Integer.parseInt(mBaseAmt.getValue());
            int price = Integer.parseInt(
                    resOD.getResponse().getBody().getOrders().get(0).getProduct().getPrice().getValue());

            if (baseAmount == 0) {
                remark = "Base Amount " + baseAmount;
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }
            if (adminFee == 0) {
                remark = "Admin Fee Amount " + adminFee;
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }

            // setting variable amount
            if (pinaltyAmount > 0) pinaltyAmount = pinaltyAmount/100;
            if (adminFee > 0) adminFee = adminFee/100;
            if (baseAmount > 0) baseAmount = baseAmount/100;
            if (price > 0) price = price/100;

            // validasi
            int tmpJumlah = baseAmount + pinaltyAmount + adminFee;
            if (price == tmpJumlah) {
                res = true;
            } else {
                remark = "Jumlah price tidak sesuai dengan (baseAmount+pinaltyAmount+adminFee)";
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }

            if (resOD.getResponse().getBody().getOrders().get(0).getSerialNumber() == null
                    || resOD.getResponse().getBody().getOrders().get(0).getSerialNumber().isEmpty()) {
                res = false;
                remark = "Param SerialNumber tidak ditemukan.";
                ResValidasi resVal = new ResValidasi(res, remark);
                return resVal;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        ResValidasi resVal = new ResValidasi(res, remark);
        return resVal;
    }

    private ReqHead buildHead() {
        String version = "1.0.0";
        String function = "alipayplus.digital.goods.destination.inquiry";
        String reqTime = help.createTrxDate("yyyy-MM-dd'T'HH:mm:ssXXX");
        String reqMsgId = r.randStrAlfaNum(8) + "-" + r.randStrAlfaNum(4) + "-" + r.randStrAlfaNum(4)
                + "-" + r.randStrAlfaNum(4) + "-" + r.randStrAlfaNum(12);
        ReqHead head = new ReqHead(version, function, reqTime, reqMsgId);

        return head;
    }
}
