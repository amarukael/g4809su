Feature: Telkom

  @navigate_to_telkom_page_on_solusipayWeb
  Scenario: I navigate to telkom on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb Telkom
    Then I'm now on solusipayweb Telkom

  @Create_telkom_transaction_with_virgo
  Scenario Outline: Create telkom transaction with virgo
    Given I on solusipayweb Telkom
    When I choose penyedia layanan "<layanan>" telkom page
    When I fill nomor pengguna "<customerId>" on telkom page
    When I click buy button
    When I click detail payment button
    When I click button pilih pembayaran
    Then I now on detail pembayaran Telkom page "<condition>"
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | condition        | layanan                    | customerId  |
#      | discount admin   | Tagihan Telepon / Indihome | 02175910000 |
      | no discount      | Tagihan Telepon / Indihome | 02175910000 |
#      | discount nominal | Tagihan Telepon / Indihome | 02175910000 |

  @Create_telkom_transaction_with_paymentgateway_dana
  Scenario Outline: Create telkom transaction with paymentgateway dana
    Given I on solusipayweb Telkom
    When I choose penyedia layanan "<layanan>" telkom page
    When I fill nomor pengguna "<customerId>" on telkom page
    When I click buy button
    When I click detail payment button
    When I click button pilih pembayaran
    Then I now on detail pembayaran Telkom page "<condition>"
    When I scroll to buttom page on paymentgateway page
    When I choose a payment method dana on paymentgateway page
    When I click button bayar on paymentgateway page
    When I click button bayar again on paymentgateway page
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | condition        | layanan                    | customerId  | phoneNumberDana | passcodeDana |
#      | discount admin   | Tagihan Telepon / Indihome | 02175910000 | 82114017471     | 574514       |
      | no discount      | Tagihan Telepon / Indihome | 02175910000 | 82114017471     | 574514       |
#      | discount nominal | Tagihan Telepon / Indihome | 02175910000 | 82114017471     | 574514       |

  @Create_telkom_transaction_with_paymentcheckout_dana
  Scenario Outline: Create telkom transaction with paymentcheckout dana
    Given I on solusipayweb Telkom
    When I choose penyedia layanan "<layanan>" telkom page
    When I fill nomor pengguna "<customerId>" on telkom page
    When I click buy button
    When I click detail payment button
    When I click button pilih pembayaran
    Then I now on detail pembayaran Telkom page "<condition>"
    When I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | condition        | layanan                    | customerId  | payMethod | phoneNumberDana | passcodeDana |
#      | discount admin   | Tagihan Telepon / Indihome | 02175910000 | DANA      | 82114017471     | 574514       |
      | no discount      | Tagihan Telepon / Indihome | 02175910000 | DANA      | 82114017471     | 574514       |
#      | discount nominal | Tagihan Telepon / Indihome | 02175910000 | DANA      | 82114017471     | 574514       |

  @Verify_error_message_field_input_customer_id
  Scenario Outline: Verify error message field customer id
    Given I on solusipayweb Telkom
    When I choose penyedia layanan "<layanan>" telkom page
    When I fill nomor pengguna "<customerId>" on telkom page
    Then I verify error message field customerId on Telkom page
    Examples:
      | layanan                    | customerId |
      | Tagihan Telepon / Indihome | 02175      |