Feature: Asuransi

  @navigate_to_asuransi_on_solusipayWeb
  Scenario: I navigate to asuransi on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb asuransi
    Then I'm now on the asuransi page

  @transaction_asuransi_with_payment_va
  Scenario Outline: Do transaction asuransi with "<condition>"
    Given I on solusipayweb Asuransi page
    * I choose "Insurance CAR" Asuransi
    * I fill "2377711010671745" nomor pelanggan Asuransi
    When I click buy button
    Then I click detail payment button
    When I click button pilih pembayaran
    Given I choose a payment method "BNI"
    Then I click button bayar on payment checkout
    Given I success choose a payment method
    Given Solusipayweb show process payment
    Then I navigate to check detail transaction

  @transaction_token_listrik_with_payment_va
  Scenario: Do transaction asuransi with "<condition>"
    Given I on solusipayweb Token Listrik page
    * I fill "520000000000" nomor pelanggan Asuransi
    * I choose "50.000" on "Token Listrik"
    When I click buy button
    When I click button pilih pembayaran
    Given I choose a payment method "BNI"
    Then I click button bayar on payment checkout
    Given I success choose a payment method
    Given Solusipayweb show process payment
    Then I navigate to check detail transaction

  @transaction_tagihan_listrik_with_payment_va
  Scenario: Do transaction asuransi with "<condition>"
    Given I on solusipayweb Tagihan Listrik page
    * I fill "520000000000" nomor pelanggan Asuransi
    When I click button bayar on Pln Post
    Then I click detail payment button
    When I click button pilih pembayaran
    Given I choose a payment method "BNI"
    Then I click button bayar on payment checkout
    Given I success choose a payment method
    Given Solusipayweb show process payment
    Then I navigate to check detail transaction

  @transaction_pascabayar_with_payment_va
  Scenario: Do transaction asuransi with "<condition>"
    Given I on solusipayweb Pascabayar page
    * I choose "TELKOMSEL Pascabayar" Asuransi
    * I fill "081130525558" nomor pelanggan Asuransi
    When I click buy button
    Then I click detail payment button
    When I click button pilih pembayaran
    Given I choose a payment method "BNI"
    Then I click button bayar on payment checkout
    Given I success choose a payment method
    Given Solusipayweb show process payment
    Then I navigate to check detail transaction

  @transaction_TVcable_with_payment_va
  Scenario: Do transaction asuransi with "<condition>"
    Given I on solusipayweb TV Cable page
    * I choose "INDOVISION, TOP TV, OKE VISION" Asuransi
    * I fill "506194715" nomor pelanggan Asuransi
    When I click buy button
    Then I click detail payment button
    When I click button pilih pembayaran
    Given I choose a payment method "BNI"
    Then I click button bayar on payment checkout
    Given I success choose a payment method
    Given Solusipayweb show process payment
    Then I navigate to check detail transaction

  @transaction_Internet_with_payment_va
  Scenario: Do transaction asuransi with "<condition>"
    Given I on solusipayweb Internet page
    * I choose "Tagihan Telepon / Indihome" Asuransi
    * I fill "021123456001" nomor pelanggan Asuransi
    When I click buy button
    Then I click detail payment button
    When I click button pilih pembayaran
    Given I choose a payment method "BNI"
    Then I click button bayar on payment checkout
    Given I success choose a payment method
    Given Solusipayweb show process payment
    Then I navigate to check detail transaction

  @transaction_pulsa_with_payment_va
  Scenario: Do transaction asuransi with "<condition>"
    Given I on solusipayweb Pulsa page
    * I fill phone number on pulsa page "085738273748"
    * I choose "10.000" on Product "IM3"
    When I click buy button
    When I click button pilih pembayaran
    Given I choose a payment method "BNI"
    Then I click button bayar on payment checkout
    Given I success choose a payment method
    Given Solusipayweb show process payment
    Then I navigate to check detail transaction

  @transaction_ehe_with_payment_va
  Scenario Outline: Atur keuntungan
    Given I open solusipayweb page
    When I navigate to atur keuntungan asuransi
    Then I click category "<category>" on keuntungan

    Examples:
      | category            |
      | Token Listrik       |
      | Tagihan Listrik     |
      | Pascabayar          |
      | Asuransi            |
      | Internet & TV Kabel |
      | Telkom / Indihome   |
      | Pulsa               |
