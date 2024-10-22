package model.ppob.database;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PpobTbLogpay {

    private String trxId;
    private String trackingRef;
    private String partnerId;
    private String customerId;
    private BigDecimal amount;
    private String receiptNo;
    private String additionalData;
    private Date transactionDate;
    private Date createdOn;
    private String productId;
    private String stan;
    private String extendInfo;
    private String orderId;
    private String switchId;
    private String supplierRef;
    private BigDecimal nominal;
    private BigDecimal adminFee;
    private Date createdOnSuspect;
    private String finishedBy;
    private String prevRc;
    private String prevSupplierRc;
    private String prevSupplierRcDesc;
    private BigDecimal adminCharge;
    private Integer orderIdIsNull;
    private String supplierId;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[DATABASE]")
                .append(" trxId: ").append(trxId).append(" |")
                .append(" trackingRef: ").append(trackingRef).append(" |")
                .append(" partnerId: ").append(partnerId).append(" |")
                .append(" customerId: ").append(customerId).append(" |")
                .append(" amount: ").append(amount).append(" |")
                .append(" receiptNo: ").append(receiptNo).append(" |")
                .append(" additionalData: ").append(additionalData).append(" |")
                .append(" transactionDate: ").append(transactionDate).append(" |")
                .append(" createdOn: ").append(createdOn).append(" |")
                .append(" productId: ").append(productId).append(" |")
                .append(" stan: ").append(stan).append(" |")
                .append(" extendInfo: ").append(extendInfo).append(" |")
                .append(" orderId: ").append(orderId).append(" |")
                .append(" switchId: ").append(switchId).append(" |")
                .append(" supplierRef: ").append(supplierRef).append(" |")
                .append(" nominal: ").append(nominal).append(" |")
                .append(" adminFee: ").append(adminFee).append(" |")
                .append(" createdOnSuspect: ").append(createdOnSuspect).append(" |")
                .append(" finishedBy: ").append(finishedBy).append(" |")
                .append(" prevRc: ").append(prevRc).append(" |")
                .append(" prevSupplierRc: ").append(prevSupplierRc).append(" |")
                .append(" prevSupplierRcDesc: ").append(prevSupplierRcDesc).append(" |")
                .append(" adminCharge: ").append(adminCharge).append(" |")
                .append(" orderIdIsNull: ").append(orderIdIsNull).append(" |")
                .append(" supplierId: ").append(supplierId).append(" ")
                .append("[DATABASE]");
        return sb.toString();
    }

}