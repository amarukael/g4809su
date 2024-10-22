package model.newapiproj;
import static org.junit.jupiter.api.Assertions.*;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static helper.Helper.validateResponseBody;

@Data
public class ResPayment {
    @NotNull
    @Size(max = 10)
    private String AgentID;
    @NotNull
    @Size(max = 10)
    private String AgentPIN;
    @NotNull
    @Size(max = 20)
    private String AgentTrxID;
    @NotNull
    @Size(max = 5)
    private String AgentStoreID;
    @NotNull
    @Size(max = 20)
    private String CustomerID;
    @NotNull
    @Size(max = 14)
    private String DateTimeRequest;
    @NotNull
    @Size(max = 6)
    private String PaymentPeriod;
    @NotNull
    @Size(max = 20)
    private String Amount;
    @NotNull
    @Size(max = 20)
    private String Charge;
    @NotNull
    @Size(max = 20)
    private String Total;
    @NotNull
    @Size(max = 20)
    private String AdminFee;
    @NotNull
    @Size(max = 2)
    private String ResponseCode;
    @Size(max = 40)
    private String ResponseDesc;
    @Size(max = 14)
    private String DatetimeResponse;
    @Size(max = 20)
    private String ReffCodeProvider;
    @Size(max = 120)
    private String AdditionalData;
    @Size(max = 10)
    private String ProductID;

    public ResPayment(String ApiProjectAlfaBody) {
        String[] parts = ApiProjectAlfaBody.split("\\|");
        this.AgentID = parts[0];
        this.AgentPIN = parts[1];
        this.AgentTrxID = parts[2];
        this.AgentStoreID = parts[3];
        this.CustomerID = parts[4];
        this.DateTimeRequest = parts[5];
        this.PaymentPeriod = parts[6];
        this.Amount = parts[7];
        this.Charge = parts[8];
        this.Total = parts[9];
        this.AdminFee = parts[10];
        this.ResponseCode = parts[11];
        this.ResponseDesc = parts[12];
        this.DatetimeResponse = parts[13];
        this.ReffCodeProvider = parts[14];
        this.AdditionalData = parts[15];
        this.ProductID = parts[16];
    }

    public String getBody(){
        return AgentID + "|" + AgentPIN + "|" + AgentTrxID + "|" + AgentStoreID + "|" + CustomerID + "|" + DateTimeRequest + "|" + PaymentPeriod + "|" + Amount + "|" + Charge + "|" + Total + "|" + AdminFee + "|" + ResponseCode + "|" + ResponseDesc + "|" + DatetimeResponse + "|" + ReffCodeProvider + "|" + AdditionalData + "|" + ProductID;
    }
//    public String validate() {
//        return validateResponseBody(this) + validateTotalAmount();
//    }
//
//    private String validateTotalAmount() {
//        long calculateTotalAmount = Long.parseLong(Amount + Charge + AdminFee);
//        if (calculateTotalAmount != Long.parseLong(Total))
//            return "Failed: Total amount not same";
//        return "passed";
//    }
}
