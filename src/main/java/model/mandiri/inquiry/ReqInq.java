package model.mandiri.inquiry;

import java.util.Date;

public class ReqInq {
    public String partnerServiceId;
    public String customerNo;
    public String virtualAccountNo;
    public String trxDateInit;
    public String channelCode;
    public String language;

    public Amount amount;
    public String inquiryRequestId;

    public String hashedSourceAccountNo;

    public ReqInq(){

    }

    public ReqInq(String partnerServiceId, String customerNo, String virtualAccountNo,
                  String trxDateInit, String channelCode, String language, Amount amount,
                  String inquiryRequestId,String hashedSourceAccountNo) {
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.trxDateInit = trxDateInit;
        this.channelCode = channelCode;
        this.language = language;
        this.amount = amount;
        this.inquiryRequestId = inquiryRequestId;
        this.hashedSourceAccountNo = hashedSourceAccountNo;
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

    public String getTrxDateInit() {
        return trxDateInit;
    }

    public void setTrxDateInit(String trxDateInit) {
        this.trxDateInit = trxDateInit;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getInquiryRequestId() {
        return inquiryRequestId;
    }

    public void setInquiryRequestId(String inquiryRequestId) {
        this.inquiryRequestId = inquiryRequestId;
    }

    @Override
    public String toString() {
        return "ReqInq{\n" +
                "partnerServiceId='" + partnerServiceId + '\n' +
                ", customerNo='" + customerNo + '\n' +
                ", virtualAccountNo='" + virtualAccountNo + '\n' +
                ", trxDateInit='" + trxDateInit + '\n' +
                ", channelCode='" + channelCode + '\n' +
                ", language='" + language + '\n' +
                ", amount=" + amount +
                ", inquiryRequestId='" + inquiryRequestId + '\n' +
                ", hashedSourceAccountNo='" + hashedSourceAccountNo + '\n' +
                '}';
    }
}
