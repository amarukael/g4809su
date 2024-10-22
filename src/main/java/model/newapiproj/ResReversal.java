package model.newapiproj;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static helper.Helper.validateResponseBody;

@Data
public class ResReversal {
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
    @NotNull
    @Size(max = 40)
    private String ResponseDesc;
    @NotNull
    @Size(max = 14)
    private String DatetimeResponse;
    @NotNull
    @Size(max = 20)
    private String ReffCodeProvider;
    @NotNull
    @Size(max = 40)
    private String AdditionalData;
    @NotNull
    @Size(max = 10)
    private String ProductID;

    public ResReversal(String ApiProjectAlfaBody) {
        String[] parts = ApiProjectAlfaBody.split("\\|");
        this.AgentID = parts[0];
        this.AgentPIN = parts[1];
        this.AgentTrxID = parts[2];
        this.AgentStoreID = parts[3];
        this.CustomerID = parts[4];
        this.DateTimeRequest = parts[5];
        this.ResponseCode = parts[6];
        this.ResponseDesc = parts[7];
        this.DatetimeResponse = parts[8];
        this.ReffCodeProvider = parts[9];
        this.AdditionalData = parts[10];
        this.ProductID = parts[11];
    }

    public String getBody() {
        return AgentID + "|" + AgentPIN + "|" + AgentTrxID + "|" + AgentStoreID + "|" + CustomerID + "|" + DateTimeRequest + "|" + ResponseCode + "|" + ResponseDesc + "|" + DatetimeResponse + "|" + ReffCodeProvider + "|" + AdditionalData + "|" + ProductID;
    }

//    public String validate() {
//        return validateResponseBody(this) ;
//    }
}
