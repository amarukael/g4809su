package model.newapiproj;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ApiProjAddtData {
    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9/]*$", message = "The value must be alphanumeric and “/”.")
    String customerid;

    @Size(max=20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String refno;

    @Size(max=50)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String customername;

    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String policeno;

    @Size(max=8)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String installmentperiod;

    @Size(max=5)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String installmennumber;

    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String installmentamount;

    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String installmentpenalty;

    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String adminfee;

    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String totalamt;

    @Size(max=20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String osinstallmentamt;

    @Size(max=20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    String message;

    public ApiProjAddtData(String additionalData) {
        if (additionalData != null  | !additionalData.equalsIgnoreCase("") ) {
            String[] additionalDataParts = additionalData.split("#", -1);
            this.customerid = additionalDataParts[0];
            this.refno = additionalDataParts[1];
            this.customername = additionalDataParts[2];
            this.policeno = additionalDataParts[3];
            this.installmentperiod = additionalDataParts[4];
            this.installmennumber = additionalDataParts[5];
            this.installmentamount = additionalDataParts[6];
            this.installmentpenalty = additionalDataParts[7];
            this.adminfee = additionalDataParts[8];
            this.totalamt = additionalDataParts[9];
            this.osinstallmentamt = additionalDataParts[10];
            this.message = additionalDataParts[11];
        } else {
            // If the input string is null, set all fields to null
            this.customerid = null;
            this.refno = null;
            this.customername = null;
            this.policeno = null;
            this.installmentperiod = null;
            this.installmennumber = null;
            this.installmentamount = null;
            this.installmentpenalty = null;
            this.adminfee = null;
            this.totalamt = null;
            this.osinstallmentamt = null;
            this.message = null;
        }
    }
}
