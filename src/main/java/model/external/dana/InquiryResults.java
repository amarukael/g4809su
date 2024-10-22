package model.external.dana;

import java.util.List;

public class InquiryResults {
    String inquiryId;
    InquiryStatus inquiryStatus;
    DestinationInfo destinationInfo;
    String customerName;
    //    List<DateTime> period;
    List<String> period;
    Money totalAmount;
    Money baseAmount;
    Money adminFee;
    String providerName;
    List<InquiryResultsDetailAmt> detailAmount;
    Money fineAmount;
    String dueDate;
    int paymentCount;
    String quantity;
    String fare;
    String totalEnergy;
    String meterNumber;
    Money amount;
    String familyNumber;
    int familyCount;

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public InquiryStatus getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(InquiryStatus inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public DestinationInfo getDestinationInfo() {
        return destinationInfo;
    }

    public void setDestinationInfo(DestinationInfo destinationInfo) {
        this.destinationInfo = destinationInfo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getPeriod() {
        return period;
    }

    public void setPeriod(List<String> period) {
        this.period = period;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Money getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Money baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Money getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(Money adminFee) {
        this.adminFee = adminFee;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<InquiryResultsDetailAmt> getDetailAmount() {
        return detailAmount;
    }

    public void setDetailAmount(List<InquiryResultsDetailAmt> detailAmount) {
        this.detailAmount = detailAmount;
    }

    public Money getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(Money fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(int paymentCount) {
        this.paymentCount = paymentCount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(String totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }

    public int getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(int familyCount) {
        this.familyCount = familyCount;
    }
}
