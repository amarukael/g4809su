package validator.ppob.plnpost.payment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import database.PpobDataHelper;
import helper.CompareField;
import model.ppob.database.PpobTbLogpay;
import model.ppob.database.PpobTbRefund;
import model.ppob.database.PpobTbSuspect;
import model.ppob.inquiry.ResInquiry;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;

public class PpobPlnpostPayValidator extends PpobPlnpostPayBaseValidator {

    Gson gson = new Gson();
    PpobDataHelper dbHelper = new PpobDataHelper();
    PpobTbLogpay tbLogpay = new PpobTbLogpay();
    PpobTbSuspect tbSuspect = new PpobTbSuspect();
    PpobTbRefund tbRefund = new PpobTbRefund();

    ResPayment resPayment;
    ReqPayment reqPayment;
    CompareField compareField;

    public Boolean valid(ResInquiry resInquiry, ReqPayment reqPayment, ResPayment resPayment) {
        tbLogpay = dbHelper.get_data_logpay_by_trackingref(resPayment.getTrackingref().toString());
        Set<String> skipFields = new HashSet<>(Arrays.asList(
                "stan", "extendinfo", "finishedBy", "prevRc", "prevSupplierRc", "prevSupplierRcDesc",
                "adminCharge", "orderIdIsNull", "supplierId"));
        return additionalDataValidator(resPayment, tbLogpay) & databaseValidator(tbLogpay, skipFields)
                & dbResponseValidator(resPayment, tbLogpay, Arrays.asList("trxid", "trxdate", "signature"))
                & requestResponseValidator(reqPayment, resPayment,
                        Arrays.asList("trxdate", "signature"));
    }

    public Boolean validSuspect(ResInquiry resInquiry, ReqPayment reqPayment, ResPayment resPayment) {
        tbSuspect = dbHelper.get_data_suspect_by_trackingref(resPayment.getTrackingref().toString());
        Set<String> skipFields = new HashSet<>(
                Arrays.asList("receiptno", "additionaldata", "stan", "extendinfo", "orderidisnull", "supplierid"));
        return databaseValidator(tbSuspect, skipFields)
                & dbResponseValidator(resPayment, tbSuspect,
                        Arrays.asList("trxdate", "signature", "rcdesc", "terminalid"))
                & requestResponseValidator(reqPayment, resPayment, Arrays.asList("trxdate", "signature"));
    }

    public Boolean validRefund(ResInquiry resInquiry, ReqPayment reqPayment, ResPayment resPayment) {
        tbRefund = dbHelper.get_data_refund_by_trackingref(resPayment.getTrackingref().toString());
        Set<String> skipFields = new HashSet<>(Arrays.asList(
                "receiptno", "additionaldata", "stan", "extendinfo", "orderid", "prevrc", "prevsupplierrc",
                "prevsupplierrcdesc", "orderidisnull", "supplierid"));
        return databaseValidator(tbRefund, skipFields)
                & dbResponseValidator(resPayment, tbRefund, Arrays.asList("trxdate", "signature", "rcdesc",
                        "terminalid"))
                & requestResponseValidator(reqPayment, resPayment, Arrays.asList("trxdate", "signature"));
    }

}
