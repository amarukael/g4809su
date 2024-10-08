package model.idsproject.idm;

public class DetailPayment {
    private String adminFee;
    private String installmentAmount;
    private String installmentNumber;
    private String installmentPenalty;
    private String installmentPeriod;

    public String getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(String adminFee) {
        this.adminFee = adminFee;
    }

    public String getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(String installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public String getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(String installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public String getInstallmentPenalty() {
        return installmentPenalty;
    }

    public void setInstallmentPenalty(String installmentPenalty) {
        this.installmentPenalty = installmentPenalty;
    }

    public String getInstallmentPeriod() {
        return installmentPeriod;
    }

    public void setInstallmentPeriod(String installmentPeriod) {
        this.installmentPeriod = installmentPeriod;
    }
}
