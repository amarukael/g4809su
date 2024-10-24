package validator.ppob.plnpost.strategy;

import java.util.ArrayList;
import java.util.List;

import model.ppob.inquiry.ResInquiry;
import model.ppob.payment.ReqPayment;
import model.ppob.payment.ResPayment;
import validator.ppob.plnpost.payment.PpobPlnpostPayValidator;
import validator.ppob.strategy.PaymentValidationStrategy;

public class PayPlnPostValidationStrategy implements PaymentValidationStrategy {
    private List<String> validationMessage = new ArrayList<>();
    private PpobPlnpostPayValidator validator;

    public PayPlnPostValidationStrategy() {
        this.validator = new PpobPlnpostPayValidator();
    }

    @Override
    public boolean validate(ResInquiry resInquiry, ReqPayment reqPayment, ResPayment resPayment, String status) {
        boolean valid = false;
        if (status.equals("SUCCESS")) {
            valid = validator.valid(resInquiry, reqPayment, resPayment);
            validationMessage.addAll(validator.getMessage());
        } else if (status.equals("SUSPECT")) {
            valid = validator.validSuspect(resInquiry, reqPayment, resPayment);
            validationMessage.addAll(validator.getMessage());
        } else if (status.equals("REFUND")) {
            valid = validator.validRefund(resInquiry, reqPayment, resPayment);
            validationMessage.addAll(validator.getMessage());
        }
        return valid;
    }

    @Override
    public List<String> getValidationMessage() {
        return validationMessage;
    }
}
