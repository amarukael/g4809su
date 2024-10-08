package model.dims.inquiry;

public class ReqInquiry {
    private String productId;
    private String bankCode;
    private String accountName;
    private String accountNo;
    private String originalPartnerReferenceNo;
    private String categoryPurchaseCode;

    // Constructor
    public ReqInquiry(String productId, String bankCode, String accountName, String accountNo,
                      String originalPartnerReferenceNo, String categoryPurchaseCode) {
        this.productId = productId;
        this.bankCode = bankCode;
        this.accountName = accountName;
        this.accountNo = accountNo;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.categoryPurchaseCode = categoryPurchaseCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getOriginalPartnerReferenceNo() {
        return originalPartnerReferenceNo;
    }

    public void setOriginalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
    }

    public String getCategoryPurchaseCode() {
        return categoryPurchaseCode;
    }

    public void setCategoryPurchaseCode(String categoryPurchaseCode) {
        this.categoryPurchaseCode = categoryPurchaseCode;
    }
}
