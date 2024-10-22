package validator.apiprojectalfa;

import helper.PropertiesHelper;
import lombok.Builder;
import lombok.Getter;
import model.newapiproj.ResInquiry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helper.Helper.validateResponseBody;

@Builder
public class ApiProjectAlfaInquiryValidator {
//    String expectedRC;
    ResInquiry resInquiry;

    @Getter
    @Builder.Default
    private List<String> validationMessage = new ArrayList<>();


    public boolean validate() {
        if (!resInquiry.getResponseCode().equalsIgnoreCase("00") & !resInquiry.getResponseCode().equalsIgnoreCase("0")) return true;
        return performValidation();
    }

    private boolean validate_customer_information() {
        validationMessage.add("[CUSTOMER INFORMATION VALIDATION]");
        try {
            String[] parts = resInquiry.getCustomerInformation().split("#", -1);

            // Check if there are exactly 3 parts and none are empty
            if (parts.length != 3 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
                throw new IllegalArgumentException("Invalid input: String must have 3 non-empty parts separated by '#'.");
            }
            // Process the valid parts
            System.out.println("Valid input: " + Arrays.toString(parts));
            validationMessage.addAll(Arrays.asList(parts));
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            validationMessage.add("Error: " + e.getMessage());
        }
        return false;
    }

    private boolean validate_constraint() {
        validationMessage.add("[CONSTRAINT VALIDATION]");
        List<String> validateConstraint = validateResponseBody(resInquiry);
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
        // 1. Validate customerinformation
        // 2. validate constraint (Optional)
        PropertiesHelper propertiesHelper = new PropertiesHelper("src/test/resources/extent.properties");
        boolean validateConstraintFirst = propertiesHelper.getBooleanProperty("apiproject.validate.constraint", true);

        if (validateConstraintFirst) {
            return validate_constraint() &&
                    validate_customer_information();
        } else {
            validate_constraint();
            validate_customer_information();
            return true;
        }
    }
}
