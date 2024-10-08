package model.dims.authorization;

public class ReqAuth {
    private String grantType;

    public ReqAuth(String grantType) {
        this.grantType = grantType;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
