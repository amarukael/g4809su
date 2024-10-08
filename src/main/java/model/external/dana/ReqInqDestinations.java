package model.external.dana;

public class ReqInqDestinations {
    private String primaryParam;

    public ReqInqDestinations(String primaryParam) {
        this.primaryParam = primaryParam;
    }

    public String getPrimaryParam() {
        return primaryParam;
    }

    public void setPrimaryParam(String primaryParam) {
        this.primaryParam = primaryParam;
    }
}
