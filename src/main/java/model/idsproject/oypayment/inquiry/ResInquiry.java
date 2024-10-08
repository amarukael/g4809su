package model.idsproject.oypayment.inquiry;

public class ResInquiry {
    private String amount;
    private String customer;
    private String desc;
    private String expiredDateTime;
    private String extraParam1;
    private String extraParam2;
    private String extraParam3;
    private String fee;
    private String message;
    private String messageID;
    private String minAmount;
    private String productCode;
    private String responseCode;
    private String responseDesc;
    private String storeID;
    private String timeStamp;
    private String totalTagihan;
    private String trackingRef;

    public ResInquiry(String amount, String customer, String desc, String expiredDateTime, String extraParam1
            , String extraParam2, String extraParam3, String fee, String message, String messageID, String minAmount
            , String productCode, String responseCode, String responseDesc, String storeID, String timeStamp
            , String totalTagihan, String trackingRef) {
        this.amount = amount;
        this.customer = customer;
        this.desc = desc;
        this.expiredDateTime = expiredDateTime;
        this.extraParam1 = extraParam1;
        this.extraParam2 = extraParam2;
        this.extraParam3 = extraParam3;
        this.fee = fee;
        this.message = message;
        this.messageID = messageID;
        this.minAmount = minAmount;
        this.productCode = productCode;
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.storeID = storeID;
        this.timeStamp = timeStamp;
        this.totalTagihan = totalTagihan;
        this.trackingRef = trackingRef;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExpiredDateTime() {
        return expiredDateTime;
    }

    public void setExpiredDateTime(String expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
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

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getTotalTagihan() {
        return totalTagihan;
    }

    public void setTotalTagihan(String totalTagihan) {
        this.totalTagihan = totalTagihan;
    }

    public String getTrackingRef() {
        return trackingRef;
    }

    public void setTrackingRef(String trackingRef) {
        this.trackingRef = trackingRef;
    }
}
