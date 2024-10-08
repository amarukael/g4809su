package model.mandiri.inquiry.res;

import java.util.List;

public class VirtualAccountData {

    private List<BillDetail> billDetails;
    private String customerNo;
    private String inquiryRequestId;
    private String partnerServiceId;
    private String virtualAccountName;
    private String virtualAccountNo;


    public VirtualAccountData(){

    }

    public VirtualAccountData(List<BillDetail> billDetails, String customerNo, String inquiryRequestId, String partnerServiceId, String virtualAccountName, String virtualAccountNo) {
        this.billDetails = billDetails;
        this.customerNo = customerNo;
        this.inquiryRequestId = inquiryRequestId;
        this.partnerServiceId = partnerServiceId;
        this.virtualAccountName = virtualAccountName;
        this.virtualAccountNo = virtualAccountNo;
    }

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getInquiryRequestId() {
        return inquiryRequestId;
    }

    public void setInquiryRequestId(String inquiryRequestId) {
        this.inquiryRequestId = inquiryRequestId;
    }

    public String getPartnerServiceId() {
        return partnerServiceId;
    }

    public void setPartnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
    }

    public String getVirtualAccountName() {
        return virtualAccountName;
    }

    public void setVirtualAccountName(String virtualAccountName) {
        this.virtualAccountName = virtualAccountName;
    }

    public String getVirtualAccountNo() {
        return virtualAccountNo;
    }

    public void setVirtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
    }
}
