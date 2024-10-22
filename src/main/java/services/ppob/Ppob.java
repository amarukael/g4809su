package services.ppob;

import java.util.LinkedHashMap;

import lombok.Getter;
import utility.Rand;

public class Ppob {
    String trackingref = null;
    Rand r = new Rand();

    @Getter
    LinkedHashMap<String, InquiryPpob> InquiryPpobList = new LinkedHashMap<>();
    @Getter
    LinkedHashMap<String, PaymentPpob> PaymentPpobList = new LinkedHashMap<>();
    // @Getter
    // LinkedHashMap<String, AdviceApiproj> AdviceApiProjList = new
    // LinkedHashMap<>();
    // @Getter
    // LinkedHashMap<String, ReversalApiproj> ReversalApiProjList = new
    // LinkedHashMap<>();

    public void postInquiry(
            String TC,
            String partnerId,
            String productId,
            String customerId,
            String extendinfo,
            String terminalId) {

        InquiryPpob inquiry = new InquiryPpob();
        inquiry.generateRequest(
                partnerId,
                productId,
                customerId,
                extendinfo,
                terminalId);
        inquiry.hitRequest();
        InquiryPpobList.put(TC, inquiry);
    }

    public void postPayment(
            String tmpTC,
            String partnerId,
            String productId,
            String customerId,
            String extendInfo,
            String terminalId,
            String totalAmount) {
        trackingref = InquiryPpobList.get(tmpTC).getResInq().getTrackingref();
        if (trackingref == null) {
            trackingref = r.getRandomTrxId(partnerId, "");
        }
        totalAmount = getTotalAmountFromInquiry(totalAmount, InquiryPpobList.get(tmpTC));
        PaymentPpob payment = new PaymentPpob();
        payment.generateRequest(
                trackingref,
                partnerId,
                productId,
                customerId,
                extendInfo,
                totalAmount,
                terminalId);
        payment.hitRequest();
        PaymentPpobList.put(tmpTC, payment);
    }

    private String getTotalAmountFromInquiry(String checkTotalAmount, InquiryPpob inquiry) {
        String totalamount = null;
        try {
            if (checkTotalAmount.equalsIgnoreCase("INQ")) {
                totalamount = inquiry.getResInq().getTotalamount();
            } else {
                totalamount = checkTotalAmount;
            }
        } catch (Exception e) {
            totalamount = "0";
        }
        return totalamount;
    }

}
