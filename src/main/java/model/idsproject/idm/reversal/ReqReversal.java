package model.idsproject.idm.reversal;

public class ReqReversal {
    private String amount;
    private String messageID;
    private String paymentCode;
    private String companyID;
    private String signature;
    private String storeID;
    private String timeStamp;
    private String trackingRef;

    public ReqReversal(String amount, String messageID, String paymentCode, String companyID, String signature
            , String storeID, String timeStamp, String trackingRef) {
        this.amount = amount;
        this.messageID = messageID;
        this.paymentCode = paymentCode;
        this.companyID = companyID;
        this.signature = signature;
        this.storeID = storeID;
        this.timeStamp = timeStamp;
        this.trackingRef = trackingRef;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
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
