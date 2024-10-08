package model.cico;

public class ReqCashOutIDM {
    private String Timestamp;
    private String ClientID;
    private String Key;
    private String BranchID;
    private String CounterID;
    private String ProductType;
    private String TrxType;
    private Detail Detail;
    private String Timeout;
    private String VersiProgram;
    private String RespCode;
    private String RespDetail;

    public ReqCashOutIDM(String timestamp, String clientID, String key, String branchID, String counterID
            , String productType, String trxType, model.cico.Detail detail, String timeout, String versiProgram
            , String respCode, String respDetail) {
        Timestamp = timestamp;
        ClientID = clientID;
        Key = key;
        BranchID = branchID;
        CounterID = counterID;
        ProductType = productType;
        TrxType = trxType;
        Detail = detail;
        Timeout = timeout;
        VersiProgram = versiProgram;
        RespCode = respCode;
        RespDetail = respDetail;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public String getCounterID() {
        return CounterID;
    }

    public void setCounterID(String counterID) {
        CounterID = counterID;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getTrxType() {
        return TrxType;
    }

    public void setTrxType(String trxType) {
        TrxType = trxType;
    }

    public model.cico.Detail getDetail() {
        return Detail;
    }

    public void setDetail(model.cico.Detail detail) {
        Detail = detail;
    }

    public String getTimeout() {
        return Timeout;
    }

    public void setTimeout(String timeout) {
        Timeout = timeout;
    }

    public String getVersiProgram() {
        return VersiProgram;
    }

    public void setVersiProgram(String versiProgram) {
        VersiProgram = versiProgram;
    }

    public String getRespCode() {
        return RespCode;
    }

    public void setRespCode(String respCode) {
        RespCode = respCode;
    }

    public String getRespDetail() {
        return RespDetail;
    }

    public void setRespDetail(String respDetail) {
        RespDetail = respDetail;
    }
}
