Feature: Transaction Product PLN Postpaid on SolusipayWeb

  Background:
    Given I open solusipayweb page
    When I navigate to solusipayweb PLN Postpaid


  @open
  Scenario: Open PLN Page
    Then I'm now on the PLNPost page


  @Transaction
  Scenario Outline: Transaction PLN Post
    When I input "<idPelanggan>" Pln Post
    When I click button bayar on Pln Post
    Then I click detail payment button
    When I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
    | idPelanggan    |
    | 520000000000   |


  @UnknownNumber
  Scenario Outline: Unknown ID Pelanggan PLN Post
    When I input "<idPelanggan>" Pln Post
    Then I can see "<error>" snackbar massage
    Examples:
      | idPelanggan  | error                              |
      | 123456789022 | ID Pelanggan yang Anda masuk salah |


  @LenghtIDPel
  Scenario Outline: Verify Lenght ID Pelanggan PLN Post
    When I input "<idPelanggan>" Pln Post
    Then I can see "<error>" warning massage
    Examples:
      | idPelanggan       | error                                        |
      | 1234567890        | Nomor terlalu pendek, minimal 11 karakter    |
      | 12345678912346879 | Nomor terlalu panjang, maksimal 12 karakter. |

