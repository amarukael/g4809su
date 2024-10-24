package validator.ppob.strategy;

import java.util.List;

import model.ppob.inquiry.ResInquiry;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;

public interface PaymentValidationStrategy {
    boolean validate(ResInquiry resInquiry, ReqPayment reqPayment, ResPayment resPayment, String status);

    List<String> getValidationMessage();
}