package model.external.dana;

public class ResHeadBodyInquiry {
    private ResInqHead head;
    private ResInqBody body;

    public ResHeadBodyInquiry(ResInqHead head, ResInqBody body) {
        this.head = head;
        this.body = body;
    }

    public ResInqHead getHead() {
        return head;
    }

    public void setHead(ResInqHead head) {
        this.head = head;
    }

    public ResInqBody getBody() {
        return body;
    }

    public void setBody(ResInqBody body) {
        this.body = body;
    }
}
