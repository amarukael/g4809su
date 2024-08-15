Feature: Asuransi

  @navigate_to_asuransi_on_solusipayWeb
  Scenario: I navigate to asuransi on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb asuransi
    Then I'm now on the asuransi page

  @transaction_asuransi_with_payment_va
  Scenario Outline: Do transaction asuransi with "<condition>"
    Given I open solusipayweb page
    
  
