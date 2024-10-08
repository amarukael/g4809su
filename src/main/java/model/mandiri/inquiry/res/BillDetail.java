package model.mandiri.inquiry.res;

public class BillDetail {
    private BillAmount billAmount;
    private String billCode;
    private String billName;


    public BillDetail(){

    }
    public BillDetail(BillAmount billAmount) {
        this.billAmount = billAmount;
    }

    public BillAmount getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BillAmount billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }
}
