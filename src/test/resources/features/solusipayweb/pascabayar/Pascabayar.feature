Feature: Transaction Product Pascabayar on SolusipayWeb

  Background:
    Given I open solusipayweb page
    When I navigate to solusipayweb Pascabayar


  @open
  Scenario: Open Pascabayar Page
    Then I'm now on the Pascabayar page


  @Transaction
  Scenario Outline: Transaction Pascabayar
    When I choose "<provider>" Pascabayar
    When I input "<idPelanggan>" Pascabayar
    When I click button bayar on Pascabayar
    Then I click detail payment button
    When I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | provider | idPelanggan  |
      | INDOSAT Pascabayar  | 085624271809 |