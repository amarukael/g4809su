package model.ppob.database;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PpobTbSuspect {
    private String trxid;
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
    private String rc;
    private String supplierRc;
    private String supplierRcDesc;
    private int orderIdIsNull;
    private String supplierId;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[DATABASE] ")
                .append("trxid: ").append(trxid).append(" | ")
                .append("trackingRef: ").append(trackingRef).append(" | ")
                .append("partnerId: ").append(partnerId).append(" | ")
                .append("customerId: ").append(customerId).append(" | ")
                .append("amount: ").append(amount).append(" | ")
                .append("receiptNo: ").append(receiptNo).append(" | ")
                .append("additionalData: ").append(additionalData).append(" | ")
                .append("transactionDate: ").append(transactionDate).append(" | ")
                .append("createdOn: ").append(createdOn).append(" | ")
                .append("productId: ").append(productId).append(" | ")
                .append("stan: ").append(stan).append(" | ")
                .append("extendInfo: ").append(extendInfo).append(" | ")
                .append("orderId: ").append(orderId).append(" | ")
                .append("switchId: ").append(switchId).append(" | ")
                .append("supplierRef: ").append(supplierRef).append(" | ")
                .append("nominal: ").append(nominal).append(" | ")
                .append("adminFee: ").append(adminFee).append(" | ")
                .append("rc: ").append(rc).append(" | ")
                .append("supplierRc: ").append(supplierRc).append(" | ")
                .append("supplierRcDesc: ").append(supplierRcDesc).append(" | ")
                .append("orderIdIsNull: ").append(orderIdIsNull).append(" | ")
                .append("supplierId: ").append(supplierId).append(" ")
                .append("[DATABASE]");
        return sb.toString();
    }
}
