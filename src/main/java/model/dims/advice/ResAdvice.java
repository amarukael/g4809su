package model.dims.advice;

import model.dims.Amount;

public class ResAdvice {
    private String transactionDate;
    private String transactionStatusDesc;
    private Amount amount;
    private String originalPartnerReferenceNo;
    private String originalReferenceNo;
    private String referenceNo;
    private String responseCode;
    private String responseMessage;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionStatusDesc() {
        return transactionStatusDesc;
    }

    public void setTransactionStatusDesc(String transactionStatusDesc) {
        this.transactionStatusDesc = transactionStatusDesc;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getOriginalPartnerReferenceNo() {
        return originalPartnerReferenceNo;
    }

    public void setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
    }

    public String getOriginalReferenceNo() {
        return originalReferenceNo;
    }

    public void setOriginalReferenceNo(String originalReferenceNo) {
        this.originalReferenceNo = originalReferenceNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
