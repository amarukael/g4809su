package model.mandiri.inquiry.res;

public class BillAmount {
    private String currency;
    private String value;

    public BillAmount(){

    }

    public BillAmount(String currency, String value) {
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
