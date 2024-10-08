package model.external.dana;

public class ReqInq {
    private ReqInqRequest request;
    private String signature;

    public ReqInq(ReqInqRequest request, String signature) {
        this.request = request;
        this.signature = signature;
    }

    public ReqInqRequest getRequest() {
        return request;
    }

    public void setRequest(ReqInqRequest request) {
        this.request = request;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
