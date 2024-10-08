package model.idsproject.idm.payment;

public class ReqPayment {
    private String amount;
    private String companyID;
    private String messageID;
    private String paymentCode;
    private String storeID;
    private String timeStamp;
    private String trackingRef;

    public ReqPayment(String amount, String companyID, String messageID, String paymentCode, String storeID
            , String timeStamp, String trackingRef) {
        this.amount = amount;
        this.companyID = companyID;
        this.messageID = messageID;
        this.paymentCode = paymentCode;
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

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
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
