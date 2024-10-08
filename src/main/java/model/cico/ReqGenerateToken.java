package model.cico;

public class ReqGenerateToken {
    private String tx_id;
    private String token;
    private String tx_date;
    private String name;
    private String phone;
    private String bankaccount;
    private String noktp;
    private String channelterm;
    private String tx_amt;
    private String description;
    private String expired_date;
    private String productid;
    private String partnerid;
    private String signature;

    public ReqGenerateToken(String tx_id, String token, String tx_date, String name, String phone, String bankaccount
            , String noktp, String channelterm, String tx_amt, String description, String expired_date
            , String productid, String partnerid, String signature) {
        this.tx_id = tx_id;
        this.token = token;
        this.tx_date = tx_date;
        this.name = name;
        this.phone = phone;
        this.bankaccount = bankaccount;
        this.noktp = noktp;
        this.channelterm = channelterm;
        this.tx_amt = tx_amt;
        this.description = description;
        this.expired_date = expired_date;
        this.productid = productid;
        this.partnerid = partnerid;
        this.signature = signature;
    }

    public String getTx_id() {
        return tx_id;
    }

    public void setTx_id(String tx_id) {
        this.tx_id = tx_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTx_date() {
        return tx_date;
    }

    public void setTx_date(String tx_date) {
        this.tx_date = tx_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getChannelterm() {
        return channelterm;
    }

    public void setChannelterm(String channelterm) {
        this.channelterm = channelterm;
    }

    public String getTx_amt() {
        return tx_amt;
    }

    public void setTx_amt(String tx_amt) {
        this.tx_amt = tx_amt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
