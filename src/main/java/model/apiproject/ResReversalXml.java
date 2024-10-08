package model.apiproject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"trxid", "trxdate", "partnerid", "productid", "customerid", "additionaldata", "rc", "rcdesc", "trackingref", "terminalid", "signature"})
public class ResReversalXml {
    private String trxid;
    private String trxdate;
    private String partnerid;
    private String productid;
    private String customerid;
    private String additionaldata;
    private String rc;
    private String rcdesc;
    private String trackingref;
    private String terminalid;
    private String signature;

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

    public String getAdditionaldata() {
        return additionaldata;
    }

    public void setAdditionaldata(String additionaldata) {
        this.additionaldata = additionaldata;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getRcdesc() {
        return rcdesc;
    }

    public void setRcdesc(String rcdesc) {
        this.rcdesc = rcdesc;
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
