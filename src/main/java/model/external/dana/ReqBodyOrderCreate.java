package model.external.dana;

public class ReqBodyOrderCreate {
    String requestId;
    String productId;
    DestinationInfo destinationInfo;
    Money danaSellingPrice;
    private String extendInfo;

    public ReqBodyOrderCreate(String requestId, String productId, DestinationInfo destinationInfo, Money danaSellingPrice, String extendInfo) {
        this.requestId = requestId;
        this.productId = productId;
        this.destinationInfo = destinationInfo;
        this.danaSellingPrice = danaSellingPrice;
        this.extendInfo = extendInfo;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public DestinationInfo getDestinationInfo() {
        return destinationInfo;
    }

    public void setDestinationInfo(DestinationInfo destinationInfo) {
        this.destinationInfo = destinationInfo;
    }

    public Money getDanaSellingPrice() {
        return danaSellingPrice;
    }

    public void setDanaSellingPrice(Money danaSellingPrice) {
        this.danaSellingPrice = danaSellingPrice;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }
}
