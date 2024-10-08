package model.mandiri.payment.res;

public class PaidAmount {
    public String currency;
    public String value;


    public PaidAmount() {
    }

    public PaidAmount(String currency, String value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
