Feature: Pulsa

  @navigate_to_pulsa_page_on_solusipayWeb
  Scenario: I navigate to pulsa on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb pulsa
    Then I'm now on the pulsa page

  @Create_pulsa_transaction_with_virgo
  Scenario Outline: Create pulsa transactionwith virgo
    Given I on solusipayweb pulsa
    When I fill phone number on pulsa page "<phoneNumber>"
    When I choose nominal pulsa "<denomPulsa>"
    When I click buy button
    When I click button pilih pembayaran
    Then I now on detail pembayaran pulsa page "<condition>"
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | condition        | phoneNumber  | denomPulsa   |
#      | discount admin   | 085738273748 | IM3 Rp 5.000 |
      | no discount      | 085738273748 | IM3 Rp 5.000 |
#      | discount nominal | 085738273748 | IM3 Rp 5.000 |

  @Create_pulsa_transaction_with_paymentgateway_dana
  Scenario Outline: Create pulsa transaction with paymentgateway dana
    Given I on solusipayweb pulsa
    When I fill phone number on pulsa page "<phoneNumber>"
    When I choose nominal pulsa "<denomPulsa>"
    When I click buy button
    When I click button pilih pembayaran
    Then I now on detail pembayaran pulsa page "<condition>"
    When I scroll to buttom page on paymentgateway page
    When I choose a payment method dana on paymentgateway page
    When I click button bayar on paymentgateway page
    When I click button bayar again on paymentgateway page
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | condition   | phoneNumber  | denomPulsa   | phoneNumberDana | passcodeDana |
      | no discount | 085738273748 | IM3 Rp 5.000 | 82114017471     | 574514       |

  @Create_pulsa_transaction_with_paymentcheckout_dana
  Scenario Outline: Create pulsa transaction with paymentcheckout dana
    Given I on solusipayweb pulsa
    When I fill phone number on pulsa page "<phoneNumber>"
    When I choose nominal pulsa "<denomPulsa>"
    When I click buy button
    When I click button pilih pembayaran
    Then I now on detail pembayaran pulsa page "<condition>"
    When I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | condition   | phoneNumber  | denomPulsa   | phoneNumberDana | passcodeDana | payMethod |
      | no discount | 085738273748 | IM3 Rp 5.000 | 82114017471     | 574514       | DANA      |


  @Verify_error_message_invalid_input_phone_number
  Scenario Outline: Verify error message invalid input phone number
    Given I on solusipayweb pulsa
    When I fill phone number on pulsa page "<phoneNumber>"
    Then I verify error message phone number field on pulsa page
    Examples:
      | phoneNumber                                |
      | 085738                                     |
      | 123456789101                               |

  @Filter_data_product_pulsa
    Scenario Outline: Filter data product pulsa
      Given I on solusipayweb pulsa
      When I fill phone number on pulsa page "<phoneNumber>"
      When I click button filter
      When I select filter "<condition>", "<priceMin>", "<priceMax>", "<filterRange>"
      When I click button save filter
      Then I can see denom pulsa base on filter
      Examples:
        | phoneNumber  | condition     | priceMin | priceMax | filterRange      |
        | 085738273748 | select filter |          |          | Rp 5rb - Rp 50rb |
        | 085738273748 | fill filter   | 5000     | 50000    |                  |

