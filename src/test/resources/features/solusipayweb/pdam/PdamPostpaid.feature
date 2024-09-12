Feature: PdamPostpaid

  @navigate_to_pdam_page_on_solusipayweb
  Scenario: User navigate to PDAM Page on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb PDAM
    Then I'm now on solusipayweb PDAM

  @transaction_pdam_with_payment_virgo
  Scenario Outline: Do transaction pdam with "<condition>"
    Given I on solusipayweb PDAM page
    When I choose "<pdam>" wilayah penyedia
    When I fill "<customerId>" nomor pelanggan
    When I click buy button
    When I click detail payment button
    Then I now on detail pembayaran PDAM page "<condition>"
    When I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction

    Examples:
      | pdam         | customerId | condition   |
      | PDAM CILACAP | 0102362102 | no discount |
#      | PDAM Kota Surabaya | 243432432  | no discount      |
#      | PDAM AETRA         | 20040428   | discount nominal |

  @transaction_pdam_with_payment_DANA
  Scenario Outline: Do transaction pdam with "<condition>"
    Given I on solusipayweb PDAM page
    When I choose "<pdam>" wilayah penyedia
    When I fill "<customerId>" nomor pelanggan
    When I click buy button
    When I click detail payment button
    Then I now on detail pembayaran PDAM page "<condition>"
    When I click button pilih pembayaran
    When I scroll to buttom page on payment checkout page
    When I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    And I navigate to check detail transaction

    Examples:
      | pdam               | customerId | condition   | payMethod | phoneNumberDana | passcodeDana |
      | PDAM Kota Surabaya |  243432432 | no discount | DANA      |     82114017471 |       574514 |
#      | PAM AETRA          | 00000021   | discount admin   | DANA      | 8996647676      | 123321       |
#      | PDAM CILACAP       | 0105041625 | discount nominal | DANA      | 8996647676      | 123321       |

  @transaction_pdam_with_payment_PCO_VA
  Scenario Outline: Do transaction pdam with "<condition>"
    Given I on solusipayweb PDAM page
    When I choose "<pdam>" wilayah penyedia
    When I fill "<customerId>" nomor pelanggan
    When I click buy button
    When I click detail payment button
    Then I now on detail pembayaran PDAM page "<condition>"
    When I click button pilih pembayaran
    # Then I choose a payment method "BTPN"
    # Then I click button bayar on payment checkout
    # * I success choose a payment method

    Examples:
      | pdam               | customerId | condition   |
      | PDAM CILACAP       | 0102362102 | no discount |
      # | PDAM DKI Aetra     |   00000011 | no discount |
      # | PDAM Kota Surabaya |   00000011 | no discount |

  @verify_error_message_invalid_input_customerId
  Scenario Outline: Verify error message invalid input nomor pelanggan
    Given I on solusipayweb PDAM page
    When I choose "<pdam>" wilayah penyedia
    When I fill "<customerId>" nomor pelanggan
    Then I verify error message "<condition>" invalid input nomor pelanggan

    Examples:
      | pdam      | customerId         | condition                |
      | PAM AETRA |                200 | less then 5 characters   |
      | PAM AETRA | 200404281111111111 | more then 15 characters  |
      | PAM AETRA |            0987223 | wrong customer id        |
      | PAM AETRA |           00000021 | customer id has been pay |
