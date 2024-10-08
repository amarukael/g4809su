package model.mnm.api;

public class ReqSendMessages {
    private String messagingProduct;
    private String to;
    private String originalPartnerReferenceNo;
    private String typeMessaging;
    private AdditionalDataInfo additionalDataInfo;

    public ReqSendMessages(String messagingProduct, String to, String originalPartnerReferenceNo
            , String typeMessaging, AdditionalDataInfo additionalDataInfo) {
        this.messagingProduct = messagingProduct;
        this.to = to;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.typeMessaging = typeMessaging;
        this.additionalDataInfo = additionalDataInfo;
    }

    public String getMessagingProduct() {
        return messagingProduct;
    }

    public void setMessagingProduct(String messagingProduct) {
        this.messagingProduct = messagingProduct;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getOriginalPartnerReferenceNo() {
        return originalPartnerReferenceNo;
    }

    public void setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
    }

    public String getTypeMessaging() {
        return typeMessaging;
    }

    public void setTypeMessaging(String typeMessaging) {
        this.typeMessaging = typeMessaging;
    }

    public AdditionalDataInfo getAdditionalDataInfo() {
        return additionalDataInfo;
    }

    public void setAdditionalDataInfo(AdditionalDataInfo additionalDataInfo) {
        this.additionalDataInfo = additionalDataInfo;
    }
}
