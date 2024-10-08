package model.dims;

public class AddtDataInfo {
    private String receiverBankCode;
    private String receiverAccountNumber;
    private String receiverName;
    private String receiverCustomerType;
    private String receiverCustomerResidence;
    private String receiverIdNumber;
    private String receiverEmail;
    private String categoryPurposeCode;
    private String purpose;

    public AddtDataInfo(String receiverBankCode, String receiverAccountNumber, String receiverName
            , String receiverCustomerType, String receiverCustomerResidence, String receiverIdNumber
            , String receiverEmail, String categoryPurposeCode, String purpose) {
        this.receiverBankCode = receiverBankCode;
        this.receiverAccountNumber = receiverAccountNumber;
        this.receiverName = receiverName;
        this.receiverCustomerType = receiverCustomerType;
        this.receiverCustomerResidence = receiverCustomerResidence;
        this.receiverIdNumber = receiverIdNumber;
        this.receiverEmail = receiverEmail;
        this.categoryPurposeCode = categoryPurposeCode;
        this.purpose = purpose;
    }

    public String getReceiverBankCode() {
        return receiverBankCode;
    }

    public void setReceiverBankCode(String receiverBankCode) {
        this.receiverBankCode = receiverBankCode;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverCustomerType() {
        return receiverCustomerType;
    }

    public void setReceiverCustomerType(String receiverCustomerType) {
        this.receiverCustomerType = receiverCustomerType;
    }

    public String getReceiverCustomerResidence() {
        return receiverCustomerResidence;
    }

    public void setReceiverCustomerResidence(String receiverCustomerResidence) {
        this.receiverCustomerResidence = receiverCustomerResidence;
    }

    public String getReceiverIdNumber() {
        return receiverIdNumber;
    }

    public void setReceiverIdNumber(String receiverIdNumber) {
        this.receiverIdNumber = receiverIdNumber;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getCategoryPurposeCode() {
        return categoryPurposeCode;
    }

    public void setCategoryPurposeCode(String categoryPurposeCode) {
        this.categoryPurposeCode = categoryPurposeCode;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
