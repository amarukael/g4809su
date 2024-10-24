package validator.ppob.plnpost.strategy;

import java.util.ArrayList;
import java.util.List;

import model.ppob.advice.ReqAdvice;
import model.ppob.advice.ResAdvice;
import validator.ppob.plnpost.advice.PpobPlnpostAdvValidator;
import validator.ppob.strategy.AdviceValidationStrategy;

public class AdvPlnPostValidationStrategy implements AdviceValidationStrategy {
    private List<String> validationMessage = new ArrayList<>();
    private PpobPlnpostAdvValidator validator;

    public AdvPlnPostValidationStrategy() {
        this.validator = new PpobPlnpostAdvValidator();
    }

    @Override
    public boolean validate(ReqAdvice reqAdvice, ResAdvice resAdvice, String status) {
        boolean valid = false;
        if (status.equals("SUCCESS")) {
            valid = validator.valid(reqAdvice, resAdvice);
            validationMessage.addAll(validator.getMessage());
        } else if (status.equals("SUSPECT")) {
            System.out.println("SUSPECT");
            // valid = validatorSuspect.valid(resInquiry, reqPayment, resPayment);
            // validationMessage.addAll(validatorSuspect.getMessage());
        } else if (status.equals("REFUND")) {
            System.out.println("REFUND");
            // valid = validatorRefund.valid(resInquiry, reqPayment, resPayment);
            // validationMessage.addAll(validatorRefund.getMessage());
        }
        return valid;
    }

    @Override
    public List<String> getValidationMessage() {
        return validationMessage;
    }
}
