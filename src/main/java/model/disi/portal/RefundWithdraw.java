package model.disi.portal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefundWithdraw {
    String trxDate;
    String merchantId;
    String partnerId;
    String trackingRef;
    String status;

    public RefundWithdraw(String trxDate, String merchantId, String partnerId, String trackingRef, String status) {
        this.trxDate = trxDate;
        this.merchantId = merchantId;
        this.partnerId = partnerId;
        this.trackingRef = trackingRef;
        this.status = status;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getTrackingRef() {
        return trackingRef;
    }

    public void setTrackingRef(String trackingRef) {
        this.trackingRef = trackingRef;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
