package model.external.dana;

public class DestinationInfo {
    String primaryParam;
    String secondaryParam;

    public DestinationInfo(String primaryParam) {
        this.primaryParam = primaryParam;
    }

    public String getPrimaryParam() {
        return primaryParam;
    }

    public void setPrimaryParam(String primaryParam) {
        this.primaryParam = primaryParam;
    }

    public String getSecondaryParam() {
        return secondaryParam;
    }

    public void setSecondaryParam(String secondaryParam) {
        this.secondaryParam = secondaryParam;
    }
}
