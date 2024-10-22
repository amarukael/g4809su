package model.external.dana;

public class ReqOrderDetail {
    ReqHeadBodyOrderDetail request;
    String signature;

    public ReqOrderDetail(ReqHeadBodyOrderDetail request, String signature) {
        this.request = request;
        this.signature = signature;
    }

    public ReqHeadBodyOrderDetail getRequest() {
        return request;
    }

    public void setRequest(ReqHeadBodyOrderDetail request) {
        this.request = request;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
