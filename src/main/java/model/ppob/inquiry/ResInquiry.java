package model.ppob.inquiry;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResInquiry {
    private String trxid;
    private String trxdate;
    private String partnerid;
    private String productid;
    private String customerid;
    private String extendinfo;
    private String customername;
    private String totalamount;
    private List<T> additionaldata;
    private String rc;
    private String rcdesc;
    private String trackingref;
    private String terminalid;
    private String signature;
    private String additionaldatanew;
    private String additionaldata_new;

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

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public List<T> getAdditionaldata() {
        return additionaldata;
    }

    public void setAdditionaldata(List<T> additionaldata) {
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

    public String getAdditionaldatanew() {
        return additionaldatanew;
    }

    public void setAdditionaldatanew(String additionaldatanew) {
        this.additionaldatanew = additionaldatanew;
    }

    public String getAdditionaldata_new() {
        return additionaldata_new;
    }

    public void setAdditionaldata_new(String additionaldata_new) {
        this.additionaldata_new = additionaldata_new;
    }
}
