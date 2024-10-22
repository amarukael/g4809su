package model.external.dana;

import java.util.List;

public class ReqInqBody {
    private List<DestinationInfo> destinationInfos;
    private String productId;

    public ReqInqBody(List<DestinationInfo> destinationInfos, String productId) {
        this.destinationInfos = destinationInfos;
        this.productId = productId;
    }

    public List<DestinationInfo> getDestinationInfos() {
        return destinationInfos;
    }

    public void setDestinationInfos(List<DestinationInfo> destinationInfos) {
        this.destinationInfos = destinationInfos;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
