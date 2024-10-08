package model.mandiri.payment;

public class ReqPayment {
    public String partnerServiceId;
    public String customerNo;
    public String virtualAccountNo;
    public String virtualAccountName;
    public String trxDateTime;
    public String channelCode;
    public String referenceNo;
    public String hashedSourceAccountNo;
    public PaidAmount paidAmount;
    public String paymentRequestId;
    public String paidBills;
    public String flagAdvise;

    public ReqPayment() {

    }

    public ReqPayment(String partnerServiceId, String customerNo, String virtualAccountNo, String virtualAccountName, String trxDateTime, String channelCode, String referenceNo, String hashedSourceAccountNo, PaidAmount paidAmount, String paymentRequestId, String paidBills, String flagAdvise) {
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.virtualAccountName = virtualAccountName;
        this.trxDateTime = trxDateTime;
        this.channelCode = channelCode;
        this.referenceNo = referenceNo;
        this.hashedSourceAccountNo = hashedSourceAccountNo;
        this.paidAmount = paidAmount;
        this.paymentRequestId = paymentRequestId;
        this.paidBills = paidBills;
        this.flagAdvise = flagAdvise;
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

    public String getVirtualAccountName() {
        return virtualAccountName;
    }

    public void setVirtualAccountName(String virtualAccountName) {
        this.virtualAccountName = virtualAccountName;
    }

    public String getTrxDateTime() {
        return trxDateTime;
    }

    public void setTrxDateTime(String trxDateTime) {
        this.trxDateTime = trxDateTime;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getHashedSourceAccountNo() {
        return hashedSourceAccountNo;
    }

    public void setHashedSourceAccountNo(String hashedSourceAccountNo) {
        this.hashedSourceAccountNo = hashedSourceAccountNo;
    }

    public PaidAmount getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(PaidAmount paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public String getPaidBills() {
        return paidBills;
    }

    public void setPaidBills(String paidBills) {
        this.paidBills = paidBills;
    }

    public String getFlagAdvise() {
        return flagAdvise;
    }

    public void setFlagAdvise(String flagAdvise) {
        this.flagAdvise = flagAdvise;
    }
}
