package model.mandiri.inquiry.res;

public class ResInq {
    private String responseCode;
    private String responseMessage;

    private VirtualAccountData virtualAccountData;

    public ResInq(){

    }

    public ResInq(String responseCode, String responseMessage, VirtualAccountData virtualAccountData) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.virtualAccountData = virtualAccountData;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public VirtualAccountData getVirtualAccountData() {
        return virtualAccountData;
    }

    public void setVirtualAccountData(VirtualAccountData virtualAccountData) {
        this.virtualAccountData = virtualAccountData;
    }
}
