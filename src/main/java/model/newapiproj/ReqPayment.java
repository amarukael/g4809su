package model.newapiproj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
