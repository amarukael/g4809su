package model.idsproject.oypayment.payment;

public class ResPayment {
    private String message;
    private String messageID;
    private String receiptCode;
    private String responseCode;
    private String responseDesc;
    private String storeID;
    private String timeStamp;
    private String trackingRef;

    public ResPayment(String message, String messageID, String receiptCode, String responseCode, String responseDesc
            , String storeID, String timeStamp, String trackingRef) {
        this.message = message;
        this.messageID = messageID;
        this.receiptCode = receiptCode;
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.storeID = storeID;
        this.timeStamp = timeStamp;
        this.trackingRef = trackingRef;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
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
