package model.apiproject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "data")
public class ReqAdviceXml implements Serializable {
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

    public ReqAdviceXml(String trxid, String trxdate, String partnerid, String productid, String customerid
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

    public String getTrxid() {
        return trxid;
    }

    public void setTrxid(String trxid) {
        this.trxid = trxid;
    }

    public String getTrxdate() {
        return trxdate;
    }

    public void setTrxdate(String trxdate) {
        this.trxdate = trxdate;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getTrackingref() {
        return trackingref;
    }

    public void setTrackingref(String trackingref) {
        this.trackingref = trackingref;
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
