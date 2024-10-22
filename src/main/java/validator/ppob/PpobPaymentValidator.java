package validator.ppob;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import database.PpobDataHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import model.ppob.database.PpobTbLogpay;
import model.ppob.inquiry.ResInquiry;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;
import validator.ppob.plnpost.PpobPlnpostpayValidator;

@Builder
public class PpobPaymentValidator {

    @NonNull
    private ResInquiry resInquiry;
    @NonNull
    private ReqPayment reqPayment;
    @NonNull
    private ResPayment resPayment;

    @Getter
    @Builder.Default
    public List<String> validationMessage = new ArrayList<>();

    PpobDataHelper dbHelper;

    public boolean validate() {
        PpobPlnpostpayValidator ppobPlnpostpayValidator = new PpobPlnpostpayValidator();
        PpobTbLogpay tbLogpay = new PpobTbLogpay();
        dbHelper = new PpobDataHelper();
        tbLogpay = dbHelper.get_data_logpay_by_trackingref(resPayment.getTrackingref().toString());
        validationMessage.add(tbLogpay.toString());
        String rc = resPayment.getRc().toString();
        Boolean valid = false;
        if (dbHelper
                .get_categoryname_by_switchid(resPayment.getProductid(), resPayment.getPartnerid())
                .equals("PLNPOST")) {
            valid = ppobPlnpostpayValidator.valid(reqPayment, resPayment);
            validationMessage.addAll(ppobPlnpostpayValidator.getMessage());
            System.out.println("Valid: " + validationMessage);
        }
        if (!Objects.equals(rc, "00")) {
            return true;
        }
        return valid;
    }

}
