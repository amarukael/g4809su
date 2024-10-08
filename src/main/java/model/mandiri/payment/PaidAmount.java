package model.mandiri.payment;

public class PaidAmount {
    public String value;
    public String currency;

    public PaidAmount() {
    }

    public PaidAmount(String value, String currency) {
        this.value = value;
        this.currency = currency;
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
