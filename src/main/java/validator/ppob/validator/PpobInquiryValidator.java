package validator.ppob.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;

import database.PpobDataHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import model.ppob.database.PpobTbInquiry;
import model.ppob.inquiry.ReqInquiry;
import model.ppob.inquiry.ResInquiry;
import validator.ppob.plnpost.PpobPlnpostInquiryValidator;

@Builder
public class PpobInquiryValidator {
    @NonNull
    private ReqInquiry reqInquiry;
    @NonNull
    private ResInquiry resInquiry;

    PpobTbInquiry tbInquiry;
    PpobDataHelper dbHelper;
    PpobPlnpostInquiryValidator ppobPlnpostInquiryValidator;

    @Getter
    @Builder.Default
    public List<String> validationMessage = new ArrayList<>();
    @Builder.Default
    Gson gson = new Gson();

    public boolean validate() {
        ppobPlnpostInquiryValidator = new PpobPlnpostInquiryValidator();
        dbHelper = new PpobDataHelper();
        tbInquiry = dbHelper.get_data_inquiry_by_trackingref(resInquiry.getTrackingref().toString());
        validationMessage.add(tbInquiry.toString());
        String rc = resInquiry.getRc().toString();
        Boolean valid = false;
        if (dbHelper
                .get_categoryname_by_switchid(resInquiry.getProductid(), resInquiry.getPartnerid())
                .equals("PLNPOST")) {
            valid = ppobPlnpostInquiryValidator.valid(reqInquiry, resInquiry);
            validationMessage.addAll(ppobPlnpostInquiryValidator.getMessage());
        }
        if (!Objects.equals(rc, "00")) {
            return true;
        }
        return valid;
    }

}