package validator.apiprojectnonalfa;

import helper.ApiProjectAdminFeeHelper;
import helper.PropertiesHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import model.newapiproj.ApiProjAddtData;
import model.newapiproj.ReqPaymentXml;
import model.newapiproj.ResInquiryXml;
import model.newapiproj.ResPaymentXml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static helper.CompareField.compareObjects;
import static helper.Helper.validateResponseBody;
import static services.apiprojectnonalfa.ApiProjectNonAlfa.scenario;

// Validasi
// 1. Validasi constraint
// 2. Validasi validate_totalAmount_Payment_MustBe_Same_Or_Higher_Than_TotalAmount_Inquiry_Response
// 3. Validasi perbandingan value inquiry dan response
// 4. Validasi perbandingan admin fee yang tersimpan di csv
@Builder
public class ApiProjectNonAlfaPaymentValidator {
    private ResInquiryXml resInquiry;
    private ReqPaymentXml reqPayment;
    @NonNull
    private ResPaymentXml resPayment;

    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();

    public boolean validate()  {
        String RC = resPayment.getRc();
        if (!Objects.equals(RC, "00")) return true;
        return performValidation();
    }

    private boolean performValidation() {
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        boolean validateConstraint = propertiesHelper.getBooleanProperty("apiproject.validate.constraint", true);

        if (validateConstraint) {
            return validate_constraint() &
                    validate_totalAmount_Payment_MustBe_Same_Or_Higher_Than_TotalAmount_Inquiry_Response()  &
                            validate_properties_response_inquiry_and_payment() &
                            validate_additional_data_value_with_response_body() &
                            validate_admin_fee_is_same_with_csv() &
                            validate_additional_data_constraint();

        } else {
            validate_constraint();
            return
                    validate_totalAmount_Payment_MustBe_Same_Or_Higher_Than_TotalAmount_Inquiry_Response()  &
                            validate_properties_response_inquiry_and_payment() &
                            validate_additional_data_value_with_response_body() &
                            validate_admin_fee_is_same_with_csv() &
                            validate_additional_data_constraint();
        }
    }

    private boolean validate_constraint() {
        validationMessage.add("[CONSTRAINT VALIDATION]");
        List<String> validateConstraint = validateResponseBody(resPayment);
        if (validateConstraint.isEmpty()) {
            validationMessage.add("NO constraint validation error!");
            return true;
        }
        else {
            validationMessage.addAll(validateConstraint);
        }
        return false;
    }

    private boolean validate_totalAmount_Payment_MustBe_Same_Or_Higher_Than_TotalAmount_Inquiry_Response() {
        if (resInquiry == null) return true;
        try {
            if (Long.parseLong(resInquiry.getTotalamount()) > Long.parseLong(resPayment.getTotalamount())) {
                validationMessage.add("<totalamount> for payment is MUST NOT smaller than Inquiry Response");
                return false;
            }
            return true;
        }  catch (NumberFormatException e) {
            validationMessage.add(String.valueOf(e));
            return false;
        }
    }

    private boolean validate_properties_response_inquiry_and_payment() {
        if (resInquiry == null) return true;
        // Compare properti yang sama, jika list terdapat data, maka ada yang berbeda
        List<String> excludedField = new ArrayList<>(Arrays.asList("trxid","trxdate","additionaldata","signature"));
        List<String> list = compareObjects(resInquiry,  resPayment, excludedField, scenario );
        if (!list.isEmpty()) {
            validationMessage.addAll(list);
            return false;
        }
        return true;
    }

    private boolean validate_additional_data_value_with_response_body() {
        // Jika tidak ada additional data ketika telah mendapatkan rc 00
        try {
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resPayment.getAdditionaldata());
            int error = 0;
            // Jika ada additional data
            String additionaldata = "[ADDITIONAL DATA]";
            List<String> validationMessage = new ArrayList<>();
            if (!resPayment.getCustomerid().equalsIgnoreCase(apiProjAddtData.getCustomerid())) {
                validationMessage.add(additionaldata + ":" + "Contract Number NOT equals");
                error++;
            } else {
                validationMessage.add(additionaldata + ":" + "Contract Number is equals");
            }

            if (!resPayment.getTotalamount().equalsIgnoreCase(apiProjAddtData.getTotalamt())){
                validationMessage.add(additionaldata + ":" + "Total Amount is NOT equals");
                error++;
            } else {
                validationMessage.add(additionaldata + ":" + "Total Amount is equals");
            }

            this.validationMessage.addAll(validationMessage);
            return error <= 0;
        } catch(Exception e) {
            this.validationMessage.add("Additional data is EMPTY");
            return false;
        }
    }

    private boolean validate_admin_fee_is_same_with_csv() {
        try {
            validationMessage.add("[ADMIN FEE CSV VALIDATION]");
            String productId = resPayment.getProductid();
            String partnerId = resPayment.getPartnerid();
            ApiProjAddtData apiProjAddtData = new ApiProjAddtData(resPayment.getAdditionaldata());

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
            ApiProjAddtData additionaldata = new ApiProjAddtData(resPayment.getAdditionaldata());
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
