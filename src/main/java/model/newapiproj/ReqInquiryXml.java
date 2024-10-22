package model.newapiproj;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "data")
public class ReqInquiryXml implements Serializable {
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
    @XmlElement(name = "trackingref")
    private String trackingref;
    @XmlElement(name = "terminalid")
    private String terminalid;
    @XmlElement(name = "signature")
    private String signature;

    public ReqInquiryXml(String trxid, String trxdate, String partnerid, String productid, String customerid
            , String trackingref, String terminalid, String signature) {
        this.trxid = trxid;
        this.trxdate = trxdate;
        this.partnerid = partnerid;
        this.productid = productid;
        this.customerid = customerid;
        this.trackingref = trackingref;
        this.terminalid = terminalid;
        this.signature = signature;
    }
}
