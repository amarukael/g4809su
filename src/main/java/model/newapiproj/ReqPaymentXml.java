package model.newapiproj;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "data")
public class ReqPaymentXml implements Serializable {
    @XmlElement(name = "trxid")
    private String trxid;
    @XmlElement(name = "trxdate")
    private String trxdate;
    @XmlElement(name = "partnerid")
    private String partnerid;
    @XmlElement(name = "productid")
    private String productid;
    @XmlElement(name = "customerid")
    private String customerid;
    @XmlElement(name = "totalamount")
    private String totalamount;
    @XmlElement(name = "trackingref")
    private String trackingref;
    @XmlElement(name = "terminalid")
    private String terminalid;
    @XmlElement(name = "signature")
    private String signature;

    public ReqPaymentXml(String trxid, String trxdate, String partnerid, String productid, String customerid
            , String totalamount, String trackingref, String terminalid, String signature) {
        this.trxid = trxid;
        this.trxdate = trxdate;
        this.partnerid = partnerid;
        this.productid = productid;
        this.customerid = customerid;
        this.totalamount = totalamount;
        this.trackingref = trackingref;
        this.terminalid = terminalid;
        this.signature = signature;
    }
}
