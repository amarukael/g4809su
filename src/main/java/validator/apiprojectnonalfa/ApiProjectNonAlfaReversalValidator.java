package validator.apiprojectnonalfa;

import helper.ApiProjectAdminFeeHelper;
import helper.PropertiesHelper;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import model.newapiproj.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static helper.CompareField.compareObjects;
import static helper.Helper.validateResponseBody;
import static services.apiprojectnonalfa.ApiProjectNonAlfa.scenario;


// Validasi
// 1. validasi constrain - validate_constraint()
// 2. validasi response payment an reversal - validate_properties_response_payment_and_reversal()
// 3. Validasi additional data - validate_additional_data()
// 4. Validasi perbandingan admin fee yang tersimpan di csv - validate_admin_fee_is_same_with_csv();
@Builder
@Data
public class ApiProjectNonAlfaReversalValidator {
    private ResPaymentXml resPayment;
    private ResReversalXml resReversal;
    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();

    public boolean validate() {
        String RC = resReversal.getRc();
        if (!Objects.equals(RC, "00")) return true;
        return performValidation();
    }

    private boolean performValidation() {
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        boolean validateConstraint = propertiesHelper.getBooleanProperty("apiproject.validate.constraint", true);

        if (validateConstraint) {
            return validate_constraint() &
                    validate_properties_response_payment_and_reversal() &
                    validate_additional_data_value_with_response_body() &
                    validate_admin_fee_is_same_with_csv() &
                    validate_additional_data_constraint();
        } else {
            validate_constraint();
            return
                    validate_properties_response_payment_and_reversal() &
                            validate_additional_data_value_with_response_body() &
                            validate_admin_fee_is_same_with_csv() &
                            validate_additional_data_constraint();
        }
    }

    private boolean validate_constraint() {
        validationMessage.add("[CONSTRAINT VALIDATION]");
        List<String> validateConstraint = validateResponseBody(resReversal);
        if (validateConstraint.isEmpty()) {
            validationMessage.add("NO constraint validation error!");
            return true;
        }
        else {
            validationMessage.addAll(validateConstraint);
        }
        return false;
    }

    private boolean validate_properties_response_payment_and_reversal() {
        if (resPayment == null) return true;

        // Compare properti yang sama, jika list ada data, maka ada properti yang berbeda
        List<String> excludedField = new ArrayList<>(Arrays.asList("trxid","trxdate","additionaldata","signature"));
        List<String> list = compareObjects(resPayment,  resReversal, excludedField, scenario);
        if (!list.isEmpty()) {
            validationMessage.addAll(list);
            return false;
        }
        return true;
    }


    private boolean validate_additional_data_value_with_response_body() {
        // Jika tidak ada additional data ketika telah mendapatkan rc 00
        try {
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resReversal.getAdditionaldata());
            int error = 0;
            // Jika ada additional data
            String additionaldata = "[ADDITIONAL DATA]";
            List<String> validationMessage = new ArrayList<>();
            if (!resReversal.getCustomerid().equalsIgnoreCase(apiProjAddtData.getCustomerid())) {
                validationMessage.add(additionaldata + ":" + "Contract Number NOT equals");
                error++;
            } else {
                validationMessage.add(additionaldata + ":" + "Contract Number is equals");
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
            String productId = resReversal.getProductid();
            String partnerId = resReversal.getPartnerid();
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resReversal.getAdditionaldata());

            ApiProjectAdminFeeHelper adminFeeHelper = new ApiProjectAdminFeeHelper();
            long registeredAdminFee = adminFeeHelper.getAdminFee(productId, partnerId);
            if ((apiProjAddtData.getAdminfee().equals(String.valueOf(registeredAdminFee)))) {
                validationMessage.add("Admin Fee is same as Registered Adminfee in CSV :" + productId + " " + registeredAdminFee);
                return true;
            } else {
                validationMessage.add("Admin Fee is NOT SAME as Registered Adminfee in CSV :" + productId + " " + registeredAdminFee);
            }
        } catch (Exception e){
            this.validationMessage.add("Additional data is EMPTY");
        }
        return false;
    }

    private boolean validate_additional_data_constraint(){
        validationMessage.add("[ADDITIONAL DATA CONSTRAINT VALIDATION]");
        try {
            ApiProjAddtData additionaldata = new ApiProjAddtData(resReversal.getAdditionaldata());
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
