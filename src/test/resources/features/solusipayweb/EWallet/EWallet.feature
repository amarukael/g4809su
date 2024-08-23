Feature: E-Wallet

  @navigate_to_ewallet_on_solusipayWeb
  Scenario: I navigate to e-wallet on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb e-wallet
    Then I'm now on the e-wallet page

  @transaction_ewallet_with_payment_virgo
  Scenario Outline: Do transaction ewallet with "<condition>"
    Given I on solusipayweb E-Wallet page
    * I choose "<product>" E-Wallet
    * I fill "<nomor>" nomor pelanggan E-Wallet
    * I choose "<denom>" on "<product>"
    Then I click buy button and I see payment detail
    When I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | product | nomor        | denom | condition   |
      | OVO     | 081200000000 | 1     | no discount |
#      | Shopee Pay New || no discount |