package validator.apiprojectnonalfa;

import static helper.Helper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import helper.ApiProjectAdminFeeHelper;
import helper.PropertiesHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import model.newapiproj.ApiProjAddtData;
import model.newapiproj.ResInquiryXml;

// Validasi
// 1. Validate Constraint - validate_constraint()
// 2. Validate Total Amount dari additional data - validate_total_amount()
// 3. Validate additionalData with response body - validate_additional_data()
// 4. Validasi perbandingan admin fee yang tersimpan di csv - validate_admin_fee_is_same_with_csv()
@Builder
public class ApiProjectNonAlfaInquiryValidator {
    // String expectedRC;
    @NonNull
    ResInquiryXml resInquiry;

    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();

    public boolean validate() {
        String RC = resInquiry.getRc();
        if (!Objects.equals(RC, "00"))
            return true;
        return performValidation();
    }

    private boolean performValidation() {
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        boolean validateConstraint = propertiesHelper.getBooleanProperty("apiproject.validate.constraint", true);

        if (validateConstraint) {
            return validate_constraint() &
                    validate_total_amount() &
                    validate_additional_data_value_with_response_body() &
                    validate_admin_fee_is_same_with_csv() &
                    validate_additional_data_constraint();
        } else {
            validate_constraint();
            return validate_total_amount() &
                    validate_additional_data_value_with_response_body() &
                    validate_admin_fee_is_same_with_csv() &
                    validate_additional_data_constraint();
        }
    }

    private boolean validate_constraint() {
        validationMessage.add("[CONSTRAINT VALIDATION]");
        List<String> validateConstraint = validateResponseBody(resInquiry);
        if (validateConstraint.isEmpty()) {
            validationMessage.add("NO constraint validation error!");
            return true;
        } else {
            validationMessage.addAll(validateConstraint);
        }
        return false;
    }

    private boolean validate_total_amount() {
        validationMessage.add("[TOTAL AMOUNT VALIDATION]");
        try {
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resInquiry.getAdditionaldata());
            long total = Long.parseLong(apiProjAddtData.getAdminfee()) +
                    Long.parseLong(apiProjAddtData.getInstallmentpenalty()) +
                    Long.parseLong(apiProjAddtData.getInstallmentamount());

            if (total == Long.parseLong(resInquiry.getTotalamount())) {
                validationMessage.add("Total Amount Calculation is Correct");
                return true;
            } else {
                validationMessage.add("Total Amount Calculation is WRONG");
                return false;
            }
        } catch (NumberFormatException e) {
            this.validationMessage.add("Additional data is EMPTY");
            return false;
        }
    }

    private boolean validate_additional_data_value_with_response_body() {
        // Jika tidak ada additional data ketika telah mendapatkan rc 00
        try {
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resInquiry.getAdditionaldata());
            int error = 0;
            // Jika ada additional data
            String additionaldata = "[ADDITIONAL DATA]";
            List<String> validationMessage = new ArrayList<>();
            if (!resInquiry.getCustomerid().equalsIgnoreCase(apiProjAddtData.getCustomerid())) {
                validationMessage.add(additionaldata + ":" + "Contract Number NOT equals");
                error++;
            } else {
                validationMessage.add(additionaldata + ":" + "Contract Number is equals");
            }

            if (!resInquiry.getCustomername().equalsIgnoreCase(apiProjAddtData.getCustomername())) {
                validationMessage.add(additionaldata + ":" + "Customer Name is NOT equals");
                error++;
            } else {
                validationMessage.add(additionaldata + ":" + "Customer Name is equals");
            }

            if (!resInquiry.getTotalamount().equalsIgnoreCase(apiProjAddtData.getTotalamt())) {
                validationMessage.add(additionaldata + ":" + "Total Amount is NOT equals");
                error++;
            } else {
                validationMessage.add(additionaldata + ":" + "Total Amount is equals");
            }
            this.validationMessage.addAll(validationMessage);
            return error <= 0;
        } catch (Exception e) {
            this.validationMessage.add("Additional data is EMPTY");
            return false;
        }
    }

    private boolean validate_admin_fee_is_same_with_csv() {
        try {
            validationMessage.add("[ADMIN FEE CSV VALIDATION]");
            String productId = resInquiry.getProductid();
            String partnerId = resInquiry.getPartnerid();
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resInquiry.getAdditionaldata());

            ApiProjectAdminFeeHelper adminFeeHelper = new ApiProjectAdminFeeHelper();
            long registeredAdminFee = adminFeeHelper.getAdminFee(productId, partnerId);
            if ((apiProjAddtData.getAdminfee().equals(String.valueOf(registeredAdminFee)))) {
                validationMessage.add(
                        "Admin Fee is same as Registered Adminfee in CSV :" + productId + " " + registeredAdminFee);
                return true;
            } else {
                validationMessage.add(
                        "Admin Fee is NOT SAME as Registered Adminfee in CSV :" + productId + " " + registeredAdminFee);
            }
        } catch (Exception e) {
            this.validationMessage.add("Additional data is EMPTY");
        }
        return false;
    }

    private boolean validate_additional_data_constraint() {
        validationMessage.add("[ADDITIONAL DATA CONSTRAINT VALIDATION]");
        try {
            ApiProjAddtData additionaldata = new ApiProjAddtData(resInquiry.getAdditionaldata());
            List<String> validateConstraint = validateResponseBody(additionaldata);
            if (validateConstraint.isEmpty()) {
                validationMessage.add("NO constraint validation error!");
                return true;
            } else {
                validationMessage.addAll(validateConstraint);
            }
        } catch (Exception e) {
            validationMessage.add("Additional data is EMPTY");
        }
        return false;
    }
}
