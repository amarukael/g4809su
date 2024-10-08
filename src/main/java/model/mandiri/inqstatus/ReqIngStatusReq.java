package model.mandiri.inqstatus;

public class ReqIngStatusReq {
    public String partnerServiceId;
    public String customerNo;
    public String virtualAccountNo;
    public String inquiryRequestId;

    public ReqIngStatusReq(String partnerServiceId, String customerNo, String virtualAccountNo, String inquiryRequestId) {
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.inquiryRequestId = inquiryRequestId;
    }

    public String getPartnerServiceId() {
        return partnerServiceId;
    }

    public void setPartnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getVirtualAccountNo() {
        return virtualAccountNo;
    }

    public void setVirtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
    }

    public String getInquiryRequestId() {
        return inquiryRequestId;
    }

    public void setInquiryRequestId(String inquiryRequestId) {
        this.inquiryRequestId = inquiryRequestId;
    }
}
