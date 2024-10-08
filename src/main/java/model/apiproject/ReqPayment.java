package model.apiproject;

public class ReqPayment {
    private String AgentID;
    private String AgentPIN;
    private String AgentTrxID;
    private String AgentStoreID;
    private String ProductID;
    private String CustomerID;
    private String DateTimeRequest;
    private String PaymentPeriod;
    private String Amount;
    private String Charge;
    private String Total;
    private String AdminFee;
    private String Signature;

    public ReqPayment(String agentID, String agentPIN, String agentTrxID, String agentStoreID
            , String productID, String customerID, String dateTimeRequest, String paymentPeriod, String amount
            , String charge, String total, String adminFee, String signature) {
        AgentID = agentID;
        AgentPIN = agentPIN;
        AgentTrxID = agentTrxID;
        AgentStoreID = agentStoreID;
        ProductID = productID;
        CustomerID = customerID;
        DateTimeRequest = dateTimeRequest;
        PaymentPeriod = paymentPeriod;
        Amount = amount;
        Charge = charge;
        Total = total;
        AdminFee = adminFee;
        Signature = signature;
    }

    public String getAgentID() {
        return AgentID;
    }

    public void setAgentID(String agentID) {
        AgentID = agentID;
    }

    public String getAgentPIN() {
        return AgentPIN;
    }

    public void setAgentPIN(String agentPIN) {
        AgentPIN = agentPIN;
    }

    public String getAgentTrxID() {
        return AgentTrxID;
    }

    public void setAgentTrxID(String agentTrxID) {
        AgentTrxID = agentTrxID;
    }

    public String getAgentStoreID() {
        return AgentStoreID;
    }

    public void setAgentStoreID(String agentStoreID) {
        AgentStoreID = agentStoreID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getDateTimeRequest() {
        return DateTimeRequest;
    }

    public void setDateTimeRequest(String dateTimeRequest) {
        DateTimeRequest = dateTimeRequest;
    }

    public String getPaymentPeriod() {
        return PaymentPeriod;
    }

    public void setPaymentPeriod(String paymentPeriod) {
        PaymentPeriod = paymentPeriod;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getCharge() {
        return Charge;
    }

    public void setCharge(String charge) {
        Charge = charge;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getAdminFee() {
        return AdminFee;
    }

    public void setAdminFee(String adminFee) {
        AdminFee = adminFee;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }
}
