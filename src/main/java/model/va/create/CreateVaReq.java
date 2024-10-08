package model.va.create;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVaReq {
    public String tx_id;
    public String tx_date;
    public String bank_code;
    public String tx_amt;
    public String is_open;
    public String va_expired_time;
    public String is_lifetime;
    public String username;
    public String trx_expiration_time;
    public String partner_id;
    public String extendinfo;
    public String terminal;
    public String signature;

    public String getTx_id() {
        return tx_id;
    }

    public void setTx_id(String tx_id) {
        this.tx_id = tx_id;
    }

    public String getTx_date() {
        return tx_date;
    }

    public void setTx_date(String tx_date) {
        this.tx_date = tx_date;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getTx_amt() {
        return tx_amt;
    }

    public void setTx_amt(String tx_amt) {
        this.tx_amt = tx_amt;
    }

    public String getIs_open() {
        return is_open;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public String getVa_expired_time() {
        return va_expired_time;
    }

    public void setVa_expired_time(String va_expired_time) {
        this.va_expired_time = va_expired_time;
    }

    public String getIs_lifetime() {
        return is_lifetime;
    }

    public void setIs_lifetime(String is_lifetime) {
        this.is_lifetime = is_lifetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTrx_expiration_time() {
        return trx_expiration_time;
    }

    public void setTrx_expiration_time(String trx_expiration_time) {
        this.trx_expiration_time = trx_expiration_time;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getExtendinfo() {
        return extendinfo;
    }

    public void setExtendinfo(String extendinfo) {
        this.extendinfo = extendinfo;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getStringSign(String secretKey , Boolean isSingleUse){
        StringBuilder sb = new StringBuilder();
        sb.append(this.tx_id)
                .append(this.tx_date)
                .append(this.bank_code)
                .append(this.tx_amt)
                .append(this.is_open)
                .append(this.va_expired_time)
                .append(this.is_lifetime)
                .append(this.username)
                .append(this.trx_expiration_time)
                .append(this.partner_id);

        if(isSingleUse){
            sb.append(this.extendinfo);
        }

        sb.append(this.terminal)
                .append(secretKey);

        return sb.toString();
    }
}
