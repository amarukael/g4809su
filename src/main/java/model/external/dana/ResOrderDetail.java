package model.external.dana;

public class ResOrderDetail {
    ResHeadBodyOrderDetail response;
    String signature;

    public ResHeadBodyOrderDetail getResponse() {
        return response;
    }

    public void setResponse(ResHeadBodyOrderDetail response) {
        this.response = response;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
