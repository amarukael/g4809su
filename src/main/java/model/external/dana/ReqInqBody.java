package model.external.dana;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReqInqBody {
    private ArrayList<ReqInqDestinations> destinationInfos;
    private String productId;

    public ReqInqBody(ArrayList<ReqInqDestinations> destinationInfos, String productId) {
        this.destinationInfos = destinationInfos;
        this.productId = productId;
    }

    public ArrayList<ReqInqDestinations> getDestinationInfos() {
        return destinationInfos;
    }

    public void setDestinationInfos(ArrayList<ReqInqDestinations> destinationInfos) {
        this.destinationInfos = destinationInfos;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
