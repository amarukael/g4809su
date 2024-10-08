package model.dims.payment;

import model.dims.AddtDataInfo;
import model.dims.Amount;

public class ReqPayment {
    private String productId;
    private String originalPartnerReferenceNo;
    private AddtDataInfo additionalDataInfo;
    private Amount amount;

    public ReqPayment(String productId, String originalPartnerReferenceNo, AddtDataInfo additionalDataInfo
            , Amount amount) {
        this.productId = productId;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.additionalDataInfo = additionalDataInfo;
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOriginalPartnerReferenceNo() {
        return originalPartnerReferenceNo;
    }

    public void setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
    }

    public AddtDataInfo getAdditionalDataInfo() {
        return additionalDataInfo;
    }

    public void setAdditionalDataInfo(AddtDataInfo additionalDataInfo) {
        this.additionalDataInfo = additionalDataInfo;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
