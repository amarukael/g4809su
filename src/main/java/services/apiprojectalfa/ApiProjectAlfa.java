package services.apiprojectalfa;
import io.cucumber.java.Scenario;
import lombok.Getter;
import services.apiprojectnonalfa.InquiryApiproj;
import utility.Rand;

import java.util.LinkedHashMap;

import static helper.ApiProjectHelper.generateTrackingRef;

public class ApiProjectAlfa {
    @Getter
    public static Scenario scenario;
    String trackingref = null;
    Rand r = new Rand();
    String currentTC; // Update TC for the next iteration

    // Helper
    @Getter
    LinkedHashMap<String, InquiryAlfa> InquiryAlfaList = new LinkedHashMap<>();
    @Getter
    LinkedHashMap<String, PaymentAlfa> PaymentAlfaList = new LinkedHashMap<>();
    @Getter
    LinkedHashMap<String, ReversalAlfa> ReversalAlfaList = new LinkedHashMap<>();

    public ApiProjectAlfa(Scenario scenario) {
        this.scenario = scenario;
    }

    public void getInquiry(
            String TC,
            String AgentId,
            String AgentPin,
            String StoreId,
            String ProductId,
            String CustomerId
    ) {
        // waktu inquiry, jika TC masi baru, generate trackingref
        trackingref = generateTrackingRef(TC, currentTC, AgentId, trackingref);
        currentTC = TC;

        scenario.log("TestCase: " + TC);

        InquiryAlfa inquiry = new InquiryAlfa(scenario);
        inquiry.generateRequest(
                trackingref,
                AgentId,
                AgentPin,
                StoreId,
                ProductId,
                CustomerId);
        inquiry.hitRequest();
        InquiryAlfaList.put(TC, inquiry);
    }

    public void getPayment(
            String TC,
            String AgentID,
            String AgentPIN,
            String AgentStoreID,
            String ProductID,
            String CustomerId,
            String PaymentPeriod,
            String Amount,
            String Charge,
            String TotalAmount,
            String AdminFee) {
        // waktu inquiry, jika TC masi baru, generate trackingref
        trackingref = generateTrackingRef(TC, currentTC, AgentID, trackingref);
        currentTC = TC;

        // Jika mengambil value total amount dari inquiry
        if (TotalAmount.equals("INQ")) {
            InquiryAlfa inquiry = InquiryAlfaList.get(TC);
            Amount = inquiry.getResInq().getAmount();
            Charge = inquiry.getResInq().getCharge();
            TotalAmount = inquiry.getResInq().getTotal();
        }

        PaymentAlfa payment = new PaymentAlfa(scenario);
        payment.generateRequest(
                trackingref,
                AgentID,
                AgentPIN,
                AgentStoreID,
                ProductID,
                CustomerId,
                PaymentPeriod,
                Amount,
                Charge,
                TotalAmount,
                AdminFee
        );


        payment.hitRequest();
        PaymentAlfaList.put(TC, payment);
    }

    public void getReversal(
            String TC,
            String AgentID,
            String AgentPIN,
            String AgentStoreID,
            String CustomerId,
            String productId
    ) {
        // waktu inquiry, jika TC masi baru, generate trackingref
        trackingref = generateTrackingRef(TC, currentTC, AgentID, trackingref);
        currentTC = TC;

        ReversalAlfa reversal = new ReversalAlfa(scenario);
        reversal.generateRequest(
                trackingref,
                AgentID,
                AgentPIN,
                AgentStoreID,
                CustomerId,
                productId
                );
        reversal.hitRequest();
        ReversalAlfaList.put(TC, reversal);
    }
}
