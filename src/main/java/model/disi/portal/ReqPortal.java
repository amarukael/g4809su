package model.disi.portal;

public class ReqPortal {
    String tipe;
    String reqBody;

    public ReqPortal(String tipe, String reqBody) {
        this.tipe = tipe;
        this.reqBody = reqBody;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }
}
