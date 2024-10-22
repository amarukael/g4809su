package validator.apiprojectnonalfa;

import helper.ApiProjectAdminFeeHelper;
import helper.PropertiesHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import model.newapiproj.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static helper.CompareField.compareObjects;
import static helper.Helper.validateResponseBody;
import static services.apiprojectnonalfa.ApiProjectNonAlfa.scenario;

// Validasi
// 1. Validate Constraint - validate_constraint()
// 2. Validate Value dari Payment dan Advice (jika payment ada) - validate_properties_response_payment_and_advice()
// 3. Validate additionalData with response body - validate_additional_data()
// 4. Validasi perbandingan admin fee yang tersimpan di csv - validate_admin_fee_is_same_with_csv();
@Builder
public class ApiProjectNonAlfaAdviceValidator {
    private ResPaymentXml resPayment;
    @NonNull
    private ResAdviceXml resAdvice;

    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();

    public boolean validate()  {
        String RC = resAdvice.getRc();
        if (!Objects.equals(RC, "00")) return true;
        return performValidation();
    }

    private boolean performValidation() {
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        boolean validateConstraint = propertiesHelper.getBooleanProperty("apiproject.validate.constraint", true);

        if (validateConstraint) {
            return validate_constraint() &&
                    validate_properties_response_payment_and_advice() &&
                    validate_additional_data_value_with_response_body() &&
                    validate_admin_fee_is_same_with_csv() &&
                    validate_additional_data_constraint();
        } else {
            validate_constraint();
            return
                    validate_properties_response_payment_and_advice() &&
                            validate_additional_data_value_with_response_body() &&
                            validate_admin_fee_is_same_with_csv() &&
                            validate_additional_data_constraint();
        }
    }

    private boolean validate_constraint() {
        validationMessage.add("[CONSTRAINT VALIDATION]");
        List<String> validateConstraint = validateResponseBody(resAdvice);
        if (validateConstraint.isEmpty()) {
            validationMessage.add("NO constraint validation error!");
            return true;
        }
        else {
            validationMessage.addAll(validateConstraint);
        }
        return false;
    }


    private boolean validate_properties_response_payment_and_advice()  {
        if (resPayment == null) return true;
        validationMessage.add("[RESPONSE PROPERTIES ADVICE WITH PAYMENT VALIDATION]");
        // Compare properti yang sama, jika list terdapat data, maka ada yang berbeda
        List<String> excludedField = new ArrayList<>(Arrays.asList("trxid","trxdate","additionaldata","signature"));
        List<String> list = compareObjects(resPayment,  resAdvice, excludedField, scenario );
        if (!list.isEmpty()) {
            validationMessage.addAll(list);
            return false;
        } else {
            validationMessage.add("No ERROR Found");
        }
        return true;
    }

    private boolean validate_additional_data_value_with_response_body() {
        // Jika tidak ada additional data ketika telah mendapatkan rc 00
        try {
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resAdvice.getAdditionaldata());
            int error = 0;
            // Jika ada additional data
            String additionaldata = "[ADDITIONAL DATA]";
            List<String> validationMessage = new ArrayList<>();
            if (!resAdvice.getCustomerid().equalsIgnoreCase(apiProjAddtData.getCustomerid())) {
                validationMessage.add(additionaldata + ":" + "Contract Number NOT equals");
                error++;
            } else {
                validationMessage.add(additionaldata + ":" + "Contract Number is equals");
            }

            if (!resAdvice.getTotalamount().equalsIgnoreCase(apiProjAddtData.getTotalamt())) {
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
            String productId = resAdvice.getProductid();
            String partnerId = resAdvice.getPartnerid();
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resAdvice.getAdditionaldata());

            ApiProjectAdminFeeHelper adminFeeHelper = new ApiProjectAdminFeeHelper();
            long registeredAdminFee = adminFeeHelper.getAdminFee(productId, partnerId);
            if ((apiProjAddtData.getAdminfee().equals(String.valueOf(registeredAdminFee)))) {
                validationMessage.add("Admin Fee is same as Registered Adminfee in CSV :" + productId + " " + registeredAdminFee);
                return true;
            } else {
                validationMessage.add("Admin Fee is NOT SAME as Registered Adminfee in CSV :" + productId + " " + registeredAdminFee);
            }
        } catch (Exception e) {
            this.validationMessage.add("Additional data is EMPTY");
        }
        return false;
    }

    private boolean validate_additional_data_constraint(){
        validationMessage.add("[ADDITIONAL DATA CONSTRAINT VALIDATION]");
        try {
            ApiProjAddtData additionaldata = new ApiProjAddtData(resAdvice.getAdditionaldata());
            List<String> validateConstraint = validateResponseBody(additionaldata);
            if (validateConstraint.isEmpty()) {
                validationMessage.add("NO constraint validation error!");
                return true;
            }
            else {
                validationMessage.addAll(validateConstraint);
            }
        } catch (Exception e) {
            validationMessage.add("Additional data is EMPTY");
        }
        return false;
    }

}
