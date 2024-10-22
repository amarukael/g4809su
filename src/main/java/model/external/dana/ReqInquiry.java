package model.external.dana;

public class ReqInquiry {
    private ReqHeadBodyInquiry request;
    private String signature;

    public ReqInquiry(ReqHeadBodyInquiry request, String signature) {
        this.request = request;
        this.signature = signature;
    }

    public ReqHeadBodyInquiry getRequest() {
        return request;
    }

    public void setRequest(ReqHeadBodyInquiry request) {
        this.request = request;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
