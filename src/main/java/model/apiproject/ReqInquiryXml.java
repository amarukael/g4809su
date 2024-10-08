package model.apiproject;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

    public ReqInquiryXml(String trxid, String trxdate, String partnerid, String productid, String customerid,
            String trackingref, String terminalid, String signature) {
        this.trxid = trxid;
        this.trxdate = trxdate;
        this.partnerid = partnerid;
        this.productid = productid;
        this.customerid = customerid;
        this.trackingref = trackingref;
        this.terminalid = terminalid;
        this.signature = signature;
    }

    public String gettrxid() {
        return trxid;
    }

    public void settrxid(String trxid) {
        this.trxid = trxid;
    }

    public String gettrxdate() {
        return trxdate;
    }

    public void settrxdate(String trxdate) {
        this.trxdate = trxdate;
    }

    public String getpartnerid() {
        return partnerid;
    }

    public void setpartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getproductid() {
        return productid;
    }

    public void setproductid(String productid) {
        this.productid = productid;
    }

    public String getcustomerid() {
        return customerid;
    }

    public void setcustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String gettrackingref() {
        return trackingref;
    }

    public void settrackingref(String trackingref) {
        this.trackingref = trackingref;
    }

    public String getterminalid() {
        return terminalid;
    }

    public void setterminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getsignature() {
        return signature;
    }

    public void setsignature(String signature) {
        this.signature = signature;
    }

    public String printModelXML() {
        return "<data>\n" +
                "    <trxid>" + trxid + "</trxid>\n" +
                "    <trxdate>" + trxdate + "</trxdate>\n" +
                "    <partnerid>" + partnerid + "</partnerid>\n" +
                "    <productid>" + productid + "</productid>\n" +
                "    <customerid>" + customerid + "</customerid>\n" +
                "    <trackingref>" + trackingref + "</trackingref>\n" +
                "    <terminalid>" + terminalid + "</terminalid>\n" +
                "    <signature>" + signature + "</signature>\n" +
                "</data>";
    }
}
