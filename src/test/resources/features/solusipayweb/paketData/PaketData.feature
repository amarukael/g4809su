Feature: PaketData

  @navigate_to_PaketData_page_on_solusipayWeb
  Scenario: I navigate to Paket Data on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb paket data
    Then I'm now on the paket data page

  @Create_paket_data_transaction_with_virgo
  Scenario Outline: Create paket data transaction that have admin discount with virgo
    Given I on solusipayweb paket data
    When I fill phone number paket data page "<phoneNumber>"
    When I choose denom paket data "<denomPaketData>"
    When I click buy button
    When I click button pilih pembayaran
    Then I now on detail pembayaran paket data page "<condition>"
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | condition      | phoneNumber  | denomPaketData                    |
      | no discount    | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari |
      | discount admin | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari |
      | discount admin | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari |

  @Create_telkom_transaction_with_paymentgateway_dana
  Scenario Outline: Create paket data transaction with paymentgateway dana
    Given I on solusipayweb paket data
    When I fill phone number paket data page "<phoneNumber>"
    When I choose denom paket data "<denomPaketData>"
    When I click buy button
    When I click button pilih pembayaran
    Then I now on detail pembayaran paket data page "<condition>"
    When I scroll to buttom page on paymentgateway page
    When I choose a payment method dana on paymentgateway page
    When I click button bayar on paymentgateway page
    When I click button bayar again on paymentgateway page
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | condition      | phoneNumber  | denomPaketData                    | phoneNumberDana | passcodeDana |
      | no discount    | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari | 82114017471     | 574514       |
      | discount admin | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari | 82114017471     | 574514       |
      | discount admin | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari | 82114017471     | 574514       |

  @Create_telkom_transaction_with_paymentcheckout_dana
  Scenario Outline: Create paket data transaction with paymentcheckout dana
    Given I on solusipayweb paket data
    When I fill phone number paket data page "<phoneNumber>"
    When I choose denom paket data "<denomPaketData>"
    When I click buy button
    When I click button pilih pembayaran
    Then I now on detail pembayaran paket data page "<condition>"
    When I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | condition      | phoneNumber  | denomPaketData                    | phoneNumberDana | passcodeDana | payMethod |
      | no discount    | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari | 82114017471     | 574514       | DANA      |
      | discount admin | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari | 82114017471     | 574514       | DANA      |
      | discount admin | 085738273748 | Freedom Kuota Harian 7GB / 7 Hari | 82114017471     | 574514       | DANA      |

  @Verify_error_message_invalid_input_phone_number
  Scenario Outline: Verify error message invalid input phone number
    Given I on solusipayweb paket data
    When I fill phone number paket data page "<phoneNumber>"
    Then I verify error message phone number field on paket data page
    Examples:
      | phoneNumber                                |
      | 085738                                     |
      | 123456789101                               |

  @Filter_data_product_pulsa
  Scenario Outline: Filter data product pulsa
    Given I on solusipayweb paket data
    When I fill phone number paket data page "<phoneNumber>"
    When I click button filter
    When I select filter "<condition>", "<priceMin>", "<priceMax>", "<filterRange>"
    When I click button save filter
    Then I can see denom paket data base on filter
    Examples:
      | phoneNumber  | condition     | priceMin | priceMax | filterRange      |
      | 085738273748 | select filter |          |          | Rp 5rb - Rp 50rb |
      | 085738273748 | fill filter   | 5000     | 50000    |                  |