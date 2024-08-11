package model.disi;

public class PartnerDetail {
    String availableBalance;
    String accountsReceivable;
    String balance;
    String pendingBalance;

    public PartnerDetail(String availableBalance, String accountsReceivable, String balance, String pendingBalance) {
        this.availableBalance = availableBalance;
        this.accountsReceivable = accountsReceivable;
        this.balance = balance;
        this.pendingBalance = pendingBalance;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getAccountsReceivable() {
        return accountsReceivable;
    }

    public void setAccountsReceivable(String accountsReceivable) {
        this.accountsReceivable = accountsReceivable;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPendingBalance() {
        return pendingBalance;
    }

    public void setPendingBalance(String pendingBalance) {
        this.pendingBalance = pendingBalance;
    }
}
