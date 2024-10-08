package model.external.dana;

public class ReqInqRequest {
    private ReqInqHead head;
    private ReqInqBody body;

    public ReqInqRequest(ReqInqHead head, ReqInqBody body) {
        this.head = head;
        this.body = body;
    }

    public ReqInqHead getHead() {
        return head;
    }

    public void setHead(ReqInqHead head) {
        this.head = head;
    }

    public ReqInqBody getBody() {
        return body;
    }

    public void setBody(ReqInqBody body) {
        this.body = body;
    }
}
