package model.dims;

public class ResAddtDataInfo extends AddtDataInfo {
    private String mtcn;

    public ResAddtDataInfo(String receiverBankCode, String receiverAccountNumber, String receiverName
            , String receiverCustomerType, String receiverCustomerResidence, String receiverIdNumber
            , String receiverEmail, String categoryPurposeCode, String purpose, String mtcn) {
        super(receiverBankCode, receiverAccountNumber, receiverName, receiverCustomerType
                , receiverCustomerResidence, receiverIdNumber, receiverEmail, categoryPurposeCode, purpose);
        this.mtcn = mtcn;
    }

    public String getMtcn() {
        return mtcn;
    }

    public void setMtcn(String mtcn) {
        this.mtcn = mtcn;
    }
}
