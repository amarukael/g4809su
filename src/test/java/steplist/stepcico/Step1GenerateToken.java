package steplist.stepcico;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Step1GenerateToken {
    Scenario scenario = Hooks.scenario;

    @Given("Valid Request \\({string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}) API Generate Token")
    public void valid_request_api_generate_token(String token, String name, String phone, String bankAccount
            , String noKtp, String channelTerm, String amount, String desc, String expiredDate, String productId
            , String partnerId) {
        scenario.log("OK");
    }

    @When("Biller hit API Generate Token")
    public void biller_hit_api_generate_token() {
        scenario.log("OK");
    }

    @Then("Biller get response {string} and response desc {string} API Generate Token")
    public void biller_get_response_and_response_desc_api_generate_token(String rc, String rcDesc) {
        scenario.log("OK");
    }
}
