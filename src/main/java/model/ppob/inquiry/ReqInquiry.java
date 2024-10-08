package model.ppob.inquiry;

public class ReqInquiry {
    private String trxid;
    private String trxdate;
    private String partnerid;
    private String productid;
    private String customerid;
    private String extendinfo;
    private String trackingref;
    private String terminalid;
    private String signature;

    public ReqInquiry(String trxid, String trxdate, String partnerid, String productid, String customerid
            , String extendinfo, String trackingref, String terminalid, String signature) {
        this.trxid = trxid;
        this.trxdate = trxdate;
        this.partnerid = partnerid;
        this.productid = productid;
        this.customerid = customerid;
        this.extendinfo = extendinfo;
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

    public String getExtendinfo() {
        return extendinfo;
    }

    public void setExtendinfo(String extendinfo) {
        this.extendinfo = extendinfo;
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
