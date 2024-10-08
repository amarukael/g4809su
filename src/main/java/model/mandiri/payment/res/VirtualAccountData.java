package model.mandiri.payment.res;


public class VirtualAccountData {
    public String customerNo;
    public PaidAmount paidAmount;
    public String partnerServiceId;
    public String paymentRequestId;
    public String trxDateTime;
    public String virtualAccountName;
    public String virtualAccountNo;

    public VirtualAccountData() {
    }

    public VirtualAccountData(String customerNo, PaidAmount paidAmount, String partnerServiceId, String paymentRequestId, String trxDateTime, String virtualAccountName, String virtualAccountNo) {
        this.customerNo = customerNo;
        this.paidAmount = paidAmount;
        this.partnerServiceId = partnerServiceId;
        this.paymentRequestId = paymentRequestId;
        this.trxDateTime = trxDateTime;
        this.virtualAccountName = virtualAccountName;
        this.virtualAccountNo = virtualAccountNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public PaidAmount getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(PaidAmount paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPartnerServiceId() {
        return partnerServiceId;
    }

    public void setPartnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getTrxDateTime() {
        return trxDateTime;
    }

    public void setTrxDateTime(String trxDateTime) {
        this.trxDateTime = trxDateTime;
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
