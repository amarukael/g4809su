package validator.apiprojectalfa;

import helper.ApiProjectAdminFeeHelper;
import helper.PropertiesHelper;
import lombok.Builder;
import lombok.Getter;
import model.newapiproj.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helper.CompareField.compareObjects;
import static helper.Helper.validateResponseBody;
import static services.apiprojectalfa.ApiProjectAlfa.scenario;


@Builder
public class ApiProjectAlfaPaymentValidator {
    private ResInquiry resInquiry;
    private ResPayment resPayment;

    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();

    public boolean validate() {
        if (!resPayment.getResponseCode().equalsIgnoreCase("00") && !resPayment.getResponseCode().equalsIgnoreCase("0")) return true;
        // 1. Validasi total amount apakah sudah benar
        // 2. Validasi Response inquiry dan payment
        // 3. Validasi constraint (Optional)
        return performValidation();
    }

    private boolean validate_Total_Amount_calculation() {
        long calculateTotalAmount =
                Long.parseLong(resPayment.getAmount()) +
                Long.parseLong(resPayment.getCharge() ) +
                        Long.parseLong(resPayment.getAdminFee());

        if (calculateTotalAmount == Long.parseLong(resPayment.getTotal())) {
            System.out.println("Total amount is SAME");
            return true;
        }
        validationMessage.add("Total amount is NOT same");
        return false;
    }

    private boolean validate_properties_response_inquiry_and_payment() {
        if (resInquiry == null) return true;
        validationMessage.add("[INQ AND PAY COMPARE VALIDATION]");
        // Compare properti yang sama, jika list terdapat data, maka ada yang berbeda
        List<String> excludedField = new ArrayList<>(Arrays.asList("DatetimeRequest","signature"));
        List<String> list = compareObjects(resInquiry,  resPayment, excludedField, scenario );
        if (!list.isEmpty()) {
            validationMessage.addAll(list);
            return false;
        }
        validationMessage.add("NO difference found!");
        return true;
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

    private boolean validate_admin_fee_is_same_with_csv() {
        try {
            validationMessage.add("[ADMIN FEE CSV VALIDATION]");
            String productId = resPayment.getProductID();
            String partnerId = resInquiry.getAgentID();

            ApiProjectAdminFeeHelper adminFeeHelper = new ApiProjectAdminFeeHelper();
            long registeredAdminFee = adminFeeHelper.getAdminFee(productId, partnerId);
            if ((resPayment.getAdminFee().equals(String.valueOf(registeredAdminFee)))) {
                validationMessage.add("Admin Fee is same as Registered Adminfee in CSV :"  + productId + " " + registeredAdminFee);
                return true;
            } else {
                validationMessage.add("Admin Fee is NOT SAME as Registered Adminfee in CSV :"  + productId + " " + registeredAdminFee);
            }
        } catch (Exception e) {
            this.validationMessage.add("Additional data is EMPTY");
        }
        return false;
    }

    private boolean performValidation() {
        // Use the properties helper to read the property value
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        boolean validateConstraint = propertiesHelper.getBooleanProperty("apiproject.validate.constraint", true);

        if (validateConstraint) {
            return
                    validate_Total_Amount_calculation()  &&
                            validate_properties_response_inquiry_and_payment() &&
                            validate_constraint();
        } else {
            validate_constraint();
            return
                    validate_Total_Amount_calculation()  &&
                            validate_properties_response_inquiry_and_payment();
        }
    }
}
