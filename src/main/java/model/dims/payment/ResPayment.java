package model.dims.payment;

import model.dims.Amount;
import model.dims.ResAddtDataInfo;

public class ResPayment {
    private ResAddtDataInfo additionalDataInfo;
    private Amount amount;
    private String originalPartnerReferenceNo;
    private String originalReferenceNo;
    private String responseCode;
    private String responseMessage;

    public ResAddtDataInfo getAdditionalDataInfo() {
        return additionalDataInfo;
    }

    public void setAdditionalDataInfo(ResAddtDataInfo additionalDataInfo) {
        this.additionalDataInfo = additionalDataInfo;
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
