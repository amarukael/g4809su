package model.external.lja;

public class ReqAdvice {
    private String trx_type;
    private String merchant;
    private String terminal;
    private String pwd;
    private String msisdn;
    private String acc_no;
    private String trx_date;
    private String amount;
    private String bill_ref;
    private String trx_id;
    private String prd_id;

    public ReqAdvice(String trx_type, String merchant, String terminal, String pwd, String msisdn
            , String acc_no, String trx_date, String amount, String bill_ref, String trx_id, String prd_id) {
        this.trx_type = trx_type;
        this.merchant = merchant;
        this.terminal = terminal;
        this.pwd = pwd;
        this.msisdn = msisdn;
        this.acc_no = acc_no;
        this.trx_date = trx_date;
        this.amount = amount;
        this.bill_ref = bill_ref;
        this.trx_id = trx_id;
        this.prd_id = prd_id;
    }

    public String getTrx_type() {
        return trx_type;
    }

    public void setTrx_type(String trx_type) {
        this.trx_type = trx_type;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getTrx_date() {
        return trx_date;
    }

    public void setTrx_date(String trx_date) {
        this.trx_date = trx_date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBill_ref() {
        return bill_ref;
    }

    public void setBill_ref(String bill_ref) {
        this.bill_ref = bill_ref;
    }

    public String getTrx_id() {
        return trx_id;
    }

    public void setTrx_id(String trx_id) {
        this.trx_id = trx_id;
    }

    public String getPrd_id() {
        return prd_id;
    }

    public void setPrd_id(String prd_id) {
        this.prd_id = prd_id;
    }
}
