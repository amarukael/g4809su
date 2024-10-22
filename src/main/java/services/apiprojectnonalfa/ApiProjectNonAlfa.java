package services.apiprojectnonalfa;

import java.util.LinkedHashMap;

import io.cucumber.java.Scenario;
import lombok.Getter;
import utility.Rand;

public class ApiProjectNonAlfa {
    @Getter
    public static Scenario scenario;
    String trackingref = null;
    Rand r = new Rand();

    // Helper
    @Getter
    LinkedHashMap<String, InquiryApiproj> InquiryApiProjList = new LinkedHashMap<>();
    @Getter
    LinkedHashMap<String, PaymentApiproj> PaymentApiProjList = new LinkedHashMap<>();
    @Getter
    LinkedHashMap<String, AdviceApiproj> AdviceApiProjList = new LinkedHashMap<>();
    @Getter
    LinkedHashMap<String, ReversalApiproj> ReversalApiProjList = new LinkedHashMap<>();

    public ApiProjectNonAlfa(Scenario scenario) {
        this.scenario = scenario;
    }

    public void postInquiry(
            String TC,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            int sigMethod) {
        // Jika tracking reff null
        if (trackingref == null)
            trackingref = r.getRandomTrxId(partnerId, "");

        InquiryApiproj inquiry = new InquiryApiproj(scenario);
        inquiry.generateRequest(
                trackingref,
                partnerId,
                productId,
                customerId,
                terminalId,
                sigMethod);
        inquiry.hitRequest();
        InquiryApiProjList.put(TC, inquiry);
    }

    public void postPayment(
            String TC,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            String totalAmount,
            int isMD5New) {
        // Jika tracking reff null
        if (trackingref == null) {
            trackingref = r.getRandomTrxId(partnerId, "");
        }
        // Jika mengambil value total amount dari inquiry
        totalAmount = getTotalAmountFromInquiry(totalAmount, InquiryApiProjList.get(TC));

        PaymentApiproj payment = new PaymentApiproj(scenario);
        payment.generateRequest(
                trackingref,
                partnerId,
                productId,
                customerId,
                terminalId,
                totalAmount,
                isMD5New);
        payment.hitRequest();
        PaymentApiProjList.put(TC, payment);
    }

    public void postAdvice(
            String TC,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            int sigMethod) {
        // Jika tracking reff null
        if (trackingref == null)
            trackingref = r.getRandomTrxId(partnerId, "");
        AdviceApiproj advice = new AdviceApiproj(scenario);
        advice.generateRequest(
                trackingref,
                partnerId,
                productId,
                customerId,
                terminalId,
                sigMethod);
        advice.hitRequest();
        AdviceApiProjList.put(TC, advice);
    }

    public void postReversal(
            String TC,
            String partnerId,
            String productId,
            String customerId,
            String terminalId,
            String totalAmount,
            int sigMethod) {
        // Jika tracking reff null
        if (trackingref == null) {
            trackingref = r.getRandomTrxId(partnerId, "");
        }

        // Jika mengambil value total amount dari inquiry
        totalAmount = getTotalAmountFromInquiry(totalAmount, InquiryApiProjList.get(TC));

        ReversalApiproj reversal = new ReversalApiproj(scenario);
        reversal.generateRequest(
                trackingref,
                partnerId,
                productId,
                customerId,
                terminalId,
                totalAmount,
                sigMethod);
        reversal.hitRequest();
        ReversalApiProjList.put(TC, reversal);
    }

    // *********************************** APIPROJECTNONALFA HELPER
    // ******************************************
    private String getTotalAmountFromInquiry(String checkTotalAmount, InquiryApiproj inquiry) {
        String totalamount = null;
        // Jika mengambil value total amount dari inquiry
        try {
            if (checkTotalAmount.equals("INQ")) {
                totalamount = inquiry.resInq.getTotalamount();
            } else {
                // Mengembalikan amount dari excel
                totalamount = checkTotalAmount;
            }
        } catch (Exception e) {
            totalamount = "0";
        }
        return totalamount;
    }
}
