package validator.ppob.strategy;

import java.util.List;

import model.ppob.advice.ReqAdvice;
import model.ppob.advice.ResAdvice;

public interface AdviceValidationStrategy {
    boolean validate(ReqAdvice reqAdvice, ResAdvice resAdvice, String status);

    List<String> getValidationMessage();
}