package model.ppob.inquiryaccount;

public class ResInquiryAcc {
    private String trackingref;
    private String trxdate;
    private String partnerid;
    private String productid;
    private String customerid;
    private String customername;
    private String rc;
    private String rcdesc;
    private String signature;

    public String getTrackingref() {
        return trackingref;
    }

    public void setTrackingref(String trackingref) {
        this.trackingref = trackingref;
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

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
