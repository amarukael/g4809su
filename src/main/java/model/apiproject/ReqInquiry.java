package model.apiproject;

public class ReqInquiry {
    private String AgentID;
    private String AgentPIN;
    private String AgentTrxID;
    private String AgentStoreID;
    private String ProductID;
    private String CustomerID;
    private String DateTimeRequest;
    private String Signature;

    public ReqInquiry(String agentID, String agentPIN, String agentTrxID, String agentStoreID
            , String productID, String customerID, String dateTimeRequest, String signature) {
        AgentID = agentID;
        AgentPIN = agentPIN;
        AgentTrxID = agentTrxID;
        AgentStoreID = agentStoreID;
        ProductID = productID;
        CustomerID = customerID;
        DateTimeRequest = dateTimeRequest;
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

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }
}
