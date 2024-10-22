package validator.apiprojectalfa;

import helper.PropertiesHelper;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import model.newapiproj.ResPayment;
import model.newapiproj.ResReversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helper.CompareField.compareObjects;
import static helper.Helper.validateResponseBody;
import static services.apiprojectalfa.ApiProjectAlfa.scenario;


@Builder
@Data
public class ApiProjectAlfaReversalValidator {
    private ResPayment resPayment;
    private ResReversal resReversal;

    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();


    public boolean validate() {
        if (!resReversal.getResponseCode().equalsIgnoreCase("00") && !resReversal.getResponseCode().equalsIgnoreCase("0")) return true;
        // 1. Validasi membandingan response payment dan reversal
        // 2. Validasi constraint (Optional)
        return performValidation();
    }

    private boolean validate_properties_response_payment_and_reversal() {
        if (resPayment == null) return true;
        validationMessage.add("[PAY AND REV COMPARE VALIDATION]");
        // Compare properti yang sama, jika list terdapat data, maka ada yang berbeda
        List<String> excludedField = new ArrayList<>(Arrays.asList("DatetimeRequest","signature"));
        List<String> list = compareObjects(resPayment,  resReversal, excludedField ,scenario);
        if (!list.isEmpty()) {
            validationMessage.addAll(list);
            return false;
        }
        validationMessage.add("NO difference found!");
        return true;
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

    private boolean performValidation() {
        // Use the properties helper to read the property value
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        boolean validateConstraint = propertiesHelper.getBooleanProperty("apiproject.validate.constraint", true);

        if (validateConstraint) {
            return
                    validate_constraint() &&
                    validate_properties_response_payment_and_reversal() ;
        } else {
            validate_constraint();
            return
                    validate_properties_response_payment_and_reversal() ;
        }
    }
}
