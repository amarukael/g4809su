package model.external.dana;

public class ReqHeadBodyInquiry {
    private ReqHead head;
    private ReqInqBody body;

    public ReqHeadBodyInquiry(ReqHead head, ReqInqBody body) {
        this.head = head;
        this.body = body;
    }

    public ReqHead getHead() {
        return head;
    }

    public void setHead(ReqHead head) {
        this.head = head;
    }

    public ReqInqBody getBody() {
        return body;
    }

    public void setBody(ReqInqBody body) {
        this.body = body;
    }
}
