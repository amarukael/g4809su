package model.external.dana;

public class ReqHead {
    private String version;
    private String function;
    private String reqTime;
    private String reqMsgId;

    public ReqHead(String version, String function, String reqTime, String reqMsgId) {
        this.version = version;
        this.function = function;
        this.reqTime = reqTime;
        this.reqMsgId = reqMsgId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getReqMsgId() {
        return reqMsgId;
    }

    public void setReqMsgId(String reqMsgId) {
        this.reqMsgId = reqMsgId;
    }
}
