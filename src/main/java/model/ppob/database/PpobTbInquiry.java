package model.ppob.database;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PpobTbInquiry {

    private String trackingRef;
    private String customerId;
    private String customerName;
    private BigDecimal amount;
    private String partnerId;
    private String productId;
    private String createdTime;
    private BigDecimal nominal;
    private BigDecimal adminFee;
    private int isSync;
    private String extendInfo;
    private String hpNo;
    private String rc;
    private String rcDesc;
    private String additionalData;
    private String additionalDataPay;
    private String email;
    private String oriTransDate;
    private String customerId2;
    private String customerId3;
    private String reffNo;
    private String reffNo2;
    private String reffNo3;
    private String switchId;
    private String customerId1;
    private String responseBiller;
    private String rcBiller;
    private String rcDescBiller;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[DATABASE]")
                .append(" Tracking Ref: ").append(trackingRef).append(" |")
                .append(" Customer ID: ").append(customerId).append(" |")
                .append(" Customer Name: ").append(customerName).append(" |")
                .append(" Amount: ").append(amount).append(" |")
                .append(" Partner ID: ").append(partnerId).append(" |")
                .append(" Product ID: ").append(productId).append(" |")
                .append(" Created Time: ").append(createdTime).append(" |")
                .append(" Nominal: ").append(nominal).append(" |")
                .append(" Admin Fee: ").append(adminFee).append(" |")
                .append(" Is Sync: ").append(isSync).append(" |")
                .append(" Extend Info: ").append(extendInfo).append(" |")
                .append(" HP No: ").append(hpNo).append(" |")
                .append(" RC: ").append(rc).append(" |")
                .append(" RC Desc: ").append(rcDesc).append(" |")
                .append(" Additional Data: ").append(additionalData).append(" |")
                .append(" Additional Data Pay: ").append(additionalDataPay).append(" |")
                .append(" Email: ").append(email).append(" |")
                .append(" Original Transaction Date: ").append(oriTransDate).append(" |")
                .append(" Customer ID 2: ").append(customerId2).append(" |")
                .append(" Customer ID 3: ").append(customerId3).append(" |")
                .append(" Reference No: ").append(reffNo).append(" |")
                .append(" Reference No 2: ").append(reffNo2).append(" |")
                .append(" Reference No 3: ").append(reffNo3).append(" |")
                .append(" Switch ID: ").append(switchId).append(" |")
                .append(" Customer ID 1: ").append(customerId1).append(" |")
                .append(" Response Biller: ").append(responseBiller).append(" |")
                .append(" RC Biller: ").append(rcBiller).append(" |")
                .append(" RC Desc Biller: ").append(rcDescBiller).append(" ")
                .append("[DATABASE]");
        return sb.toString();
    }

}
