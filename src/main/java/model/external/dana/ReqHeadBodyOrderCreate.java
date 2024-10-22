package model.external.dana;

public class ReqHeadBodyOrderCreate {
    ReqHead head;
    ReqBodyOrderCreate body;


    public ReqHeadBodyOrderCreate(ReqHead head, ReqBodyOrderCreate body) {
        this.head = head;
        this.body = body;
    }

    public ReqHead getHead() {
        return head;
    }

    public void setHead(ReqHead head) {
        this.head = head;
    }

    public ReqBodyOrderCreate getBody() {
        return body;
    }

    public void setBody(ReqBodyOrderCreate body) {
        this.body = body;
    }
}
