package model.newapiproj;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"trxid", "trxdate", "partnerid", "productid", "customerid", "totalamount", "receiptcode"
        , "additionaldata", "rc", "rcdesc", "trackingref", "terminalid", "signature"})
public class ResAdviceXml {
    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    private String trxid;

    @Size(max=19)
    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "Date must be in the format yyyy-MM-dd HH:mm:ss")
    private String trxdate;

    @Size(max=5)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    private String partnerid;

    @Size(max=5)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    private String productid;

    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9/]*$", message = "The value must be alphanumeric and “/”.")
    private String customerid;

    @Size(max=20)
    @NotBlank
    private String totalamount;

    @Size(max=20)
    @NotBlank
    private String receiptcode;

    @Size(max=8000)
    @NotBlank
    private String additionaldata;

    @Size(max=20)
    @NotBlank
    private String rc;

    @Size(max=20)
    @NotBlank
    private String rcdesc;

    @Size(max=40)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    private String trackingref;

    @Size(max=25)
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    private String terminalid;

    @Size(max=48)
    @NotBlank
    private String signature;
}
