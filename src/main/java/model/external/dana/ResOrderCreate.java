package model.external.dana;

public class ResOrderCreate {
    ResHeadBodyOrderCreate response;
    String signature;

    public ResHeadBodyOrderCreate getResponse() {
        return response;
    }

    public void setResponse(ResHeadBodyOrderCreate response) {
        this.response = response;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
