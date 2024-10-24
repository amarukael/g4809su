package validator.ppob.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.PpobDataHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import model.ppob.database.PpobTbLogpay;
import model.ppob.database.PpobTbRefund;
import model.ppob.database.PpobTbSuspect;
import model.ppob.inquiry.ResInquiry;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;
import validator.ppob.plnpost.strategy.PayPlnPostValidationStrategy;
import validator.ppob.strategy.PaymentValidationStrategy;

@Builder
@AllArgsConstructor
public class PpobPaymentValidator {

    @NonNull
    private ResInquiry resInquiry;
    @NonNull
    private ReqPayment reqPayment;
    @NonNull
    private ResPayment resPayment;

    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();

    @Builder.Default
    private PpobDataHelper dbHelper = new PpobDataHelper();
    @Builder.Default
    private Map<String, PaymentValidationStrategy> validationStrategies = new HashMap<>();

    PpobTbSuspect tbSuspect;
    PpobTbLogpay tbLogpay;
    PpobTbRefund tbRefund;
    String status;

    private void initializeStrategies() {
        validationStrategies = new HashMap<>();
        validationStrategies.put("PLNPOST", new PayPlnPostValidationStrategy());
        validationStrategies.put("TVCABLE", new PayPlnPostValidationStrategy());
    }

    public boolean validate() {
        initializeStrategies();
        String rc = resPayment.getRc().toString();
        boolean valid = false;

        if (rc.equals("00")) {
            status = "SUCCESS";
            tbLogpay = dbHelper.get_data_logpay_by_trackingref(resPayment.getTrackingref().toString());
            validationMessage.add(tbLogpay.toString());
            valid = validateByCategory();
        } else if (rc.equals("4") || rc.equals("10") || rc.equals("68")) {
            status = "SUSPECT";
            tbSuspect = dbHelper.get_data_suspect_by_trackingref(resPayment.getTrackingref().toString());
            validationMessage.add(tbSuspect.toString());
            valid = validateByCategory();
        } else {
            status = "REFUND";
            tbRefund = dbHelper.get_data_refund_by_trackingref(resPayment.getTrackingref().toString());
            validationMessage.add(tbRefund.toString());
            valid = validateByCategory();
        }
        return valid;
    }

    private boolean validateByCategory() {
        String category = dbHelper.get_categoryname_by_switchid(
                resPayment.getProductid(),
                resPayment.getPartnerid());

        PaymentValidationStrategy strategy = validationStrategies.get(category);
        if (strategy != null) {
            boolean valid = strategy.validate(resInquiry, reqPayment, resPayment, status);
            validationMessage.addAll(strategy.getValidationMessage());
            return valid;
        } else {
            validationMessage.add("Category not found");
            System.out.println("Category not found");
        }
        return false;
    }
}