package model.external.dana;

public class ReqOrderCreate {
    ReqHeadBodyOrderCreate request;
    String signature;

    public ReqOrderCreate(ReqHeadBodyOrderCreate request, String signature) {
        this.request = request;
        this.signature = signature;
    }

    public ReqHeadBodyOrderCreate getRequest() {
        return request;
    }

    public void setRequest(ReqHeadBodyOrderCreate request) {
        this.request = request;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
