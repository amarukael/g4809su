package model.disi;

public class PartnerData {
    PartnerDetail beforeAction;
    String amount;
    PartnerDetail afterAction;

    public PartnerDetail getBeforeAction() {
        return beforeAction;
    }

    public void setBeforeAction(PartnerDetail beforeAction) {
        this.beforeAction = beforeAction;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PartnerDetail getAfterAction() {
        return afterAction;
    }

    public void setAfterAction(PartnerDetail afterAction) {
        this.afterAction = afterAction;
    }
}
