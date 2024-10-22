package model.external.dana;

public class ResInquiry {
    ResHeadBodyInquiry response;
    String signature;

    public ResHeadBodyInquiry getResponse() {
        return response;
    }

    public void setResponse(ResHeadBodyInquiry response) {
        this.response = response;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
