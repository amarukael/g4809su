package model.newapiproj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqReversal {
    private String AgentID;
    private String AgentPIN;
    private String AgentTrxID;
    private String AgentStoreID;
    private String ProductID;
    private String CustomerID;
    private String DateTimeRequest;
    private String Signature;

    public ReqReversal(String agentID, String agentPIN, String agentTrxID, String agentStoreID, String productID
            , String customerID, String dateTimeRequest, String signature) {
        AgentID = agentID;
        AgentPIN = agentPIN;
        AgentTrxID = agentTrxID;
        AgentStoreID = agentStoreID;
        ProductID = productID;
        CustomerID = customerID;
        DateTimeRequest = dateTimeRequest;
        Signature = signature;
    }
}
