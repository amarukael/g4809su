package steplist.stepcico;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Step2CashOutIDM {
    Scenario scenario = Hooks.scenario;

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}) API CashOut Transaction tipe Payment")
    public void valid_request_api_cash_out_transaction_tipe_payment(String clientId, String key, String branchId
            , String counterId, String productType, String detail, String timeOut, String versiProgram) {
        scenario.log("OK");
    }

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}) API CashOut Transaction tipe Reversal")
    public void valid_request_api_cash_out_transaction_tipe_reversal(String clientId, String key, String branchId
            , String counterId, String productType, String detail, String timeOut, String versiProgram) {
        scenario.log("OK");
    }

    @When("Partner hit API CashOut Transaction tipe Payment")
    public void partner_hit_api_cash_out_transaction_tipe_payment() {
        scenario.log("OK");
    }

    @When("Partner hit API CashOut Transaction tipe Reversal")
    public void partner_hit_api_cash_out_transaction_tipe_reversal() {
        scenario.log("OK");
    }

    @Then("Partner get response {string} and response desc {string} API CashOut Transaction tipe Payment")
    public void partner_get_response_and_response_desc_api_cash_out_transaction_tipe_payment(String rc, String rcDesc) {
        scenario.log("OK");
    }

    @Then("Partner get response {string} and response desc {string} API CashOut Transaction tipe Reversal")
    public void partner_get_response_and_response_desc_api_cash_out_transaction_tipe_reversal(String rc, String rcDesc) {
        scenario.log("OK");
    }
}
