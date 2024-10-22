package model.external.dana;

public class ReqHeadBodyOrderDetail {
    ReqHead head;
    ReqBodyOrderDetail body;

    public ReqHeadBodyOrderDetail(ReqHead head, ReqBodyOrderDetail body) {
        this.head = head;
        this.body = body;
    }

    public ReqHead getHead() {
        return head;
    }

    public void setHead(ReqHead head) {
        this.head = head;
    }

    public ReqBodyOrderDetail getBody() {
        return body;
    }

    public void setBody(ReqBodyOrderDetail body) {
        this.body = body;
    }
}
