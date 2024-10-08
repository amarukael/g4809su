package model.idsproject.oypayment.inquiry;

public class ReqInquiry {
    private String extraParam1;
    private String extraParam2;
    private String extraParam3;
    private String messageID;
    private String paymentCode;
    private String productCode;
    private String signature;
    private String storeID;
    private String timeStamp;
    private String trackingRef;

    public ReqInquiry(String extraParam1, String extraParam2, String extraParam3, String messageID, String paymentCode
            , String productCode, String signature, String storeID, String timeStamp, String trackingRef) {
        this.extraParam1 = extraParam1;
        this.extraParam2 = extraParam2;
        this.extraParam3 = extraParam3;
        this.messageID = messageID;
        this.paymentCode = paymentCode;
        this.productCode = productCode;
        this.signature = signature;
        this.storeID = storeID;
        this.timeStamp = timeStamp;
        this.trackingRef = trackingRef;
    }

    public String getExtraParam1() {
        return extraParam1;
    }

    public void setExtraParam1(String extraParam1) {
        this.extraParam1 = extraParam1;
    }

    public String getExtraParam2() {
        return extraParam2;
    }

    public void setExtraParam2(String extraParam2) {
        this.extraParam2 = extraParam2;
    }

    public String getExtraParam3() {
        return extraParam3;
    }

    public void setExtraParam3(String extraParam3) {
        this.extraParam3 = extraParam3;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTrackingRef() {
        return trackingRef;
    }

    public void setTrackingRef(String trackingRef) {
        this.trackingRef = trackingRef;
    }
}
