package model.newapiproj;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static helper.Helper.validateResponseBody;

@Data
public class ResInquiry {
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
    @Size(max = 2)
    private String ResponseCode;
    @Size(max = 40)
    private String ResponseDesc;
    @Size(max = 14)
    private String DatetimeResp;
    @Size(max = 6)
    private String PaymentPeriod;
    @Size(max = 40)
    private String CustomerName;
    @Size(max = 100)
    private String CustomerInformation;
    @Size(max = 8)
    private String DeadlineTime;
    @Size(max = 20)
    private String Amount;
    @Size(max = 20)
    private String Charge;
    @Size(max = 20)
    private String Total;
    @NotNull
    @Size(max = 10)
    private String ProductID;

    public ResInquiry(String ApiProjectAlfaBody) {
        String[] parts = ApiProjectAlfaBody.split("\\|");
        this.AgentID = parts[0];
        this.AgentPIN = parts[1];
        this.AgentTrxID = parts[2];
        this.AgentStoreID = parts[3];
        this.CustomerID = parts[4];
        this.DateTimeRequest = parts[5];
        this.ResponseCode = parts[6];
        this.ResponseDesc = parts[7];
        this.DatetimeResp = parts[8];
        this.PaymentPeriod = parts[9];
        this.CustomerName = parts[10];
        this.CustomerInformation = parts[11];
        this.DeadlineTime = parts[12];
        this.Amount = parts[13];
        this.Charge = parts[14];
        this.Total = parts[15];
        this.ProductID = parts[16];
    }

    public String getBody() {
        return AgentID + "|" + AgentPIN + "|" + AgentTrxID + "|" + AgentStoreID + "|" + CustomerID + "|" + DateTimeRequest + "|" + ResponseCode + "|" + ResponseDesc + "|" + DatetimeResp + "|" + PaymentPeriod + "|" + CustomerName + "|" + CustomerInformation + "|" + DeadlineTime + "|" + Amount + "|" + Charge + "|" + Total + "|" + ProductID;
    }

//    public String validate() {
//        return validateResponseBody(this);
//    }


}
