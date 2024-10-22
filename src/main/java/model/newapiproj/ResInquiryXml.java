package model.newapiproj;

import lombok.Data;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"trxid", "trxdate", "partnerid", "productid", "customerid", "customername", "totalamount", "additionaldata", "rc", "rcdesc", "trackingref", "terminalid", "signature"})
public class ResInquiryXml {
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

    @Size(max=50)
    @NotBlank
//    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "The value must be alphanumeric.")
    private String customername;

    @Size(max=20)
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "The value must be String numeric.")
    private String totalamount;

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


