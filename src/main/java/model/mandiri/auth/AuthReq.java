package model.mandiri.auth;

public class AuthReq {
    private String grantType;

    public AuthReq(String grantType) {
        this.grantType = grantType;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
