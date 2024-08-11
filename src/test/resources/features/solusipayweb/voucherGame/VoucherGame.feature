Feature: VoucherGame

  @navigate_to_voucher_game_on_solusipayWeb
  Scenario: I navigate to voucher game on solusipay web
    Given I open solusipayweb page
    When I navigate to solusipayweb Voucher Game
    Then I'm now on the voucher game page

  @search_a_product_voucher_game
  Scenario Outline: User search a product voucher game or top up game
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    Then I "<condition>" to see the product I was looking for
    Examples:
    |condition   |searchInput  |
    |Successfully|Steam        |
    |Failed      |Mo           |

  @filter_product_by_category
  Scenario Outline: User filter product game by category
    Given I on Solusipay Web Voucher Game Page
    When I click button "<category>" product
    Then I can see product list "<type>" by filter category
    Examples:
      | category | type          |
      | Voucher  | Voucher Games |
      | TopUp    | Top Up Games  |

  @navigate_to_detail_product_topUp_game_with_userId_zoneId
  Scenario Outline: User navigate to product details topUp game with userId_zoneId
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    Then I verify product detail top up game "<game>" with userId_zoneId
    Examples:
      | searchInput   | product          | game          |
      | Mobile Legend | ids-game-mlbb-44 | Mobile Legend |

  @navigate_to_detail_product_topUp_game_with_userId
  Scenario Outline: User navigate to product details topUp game with userId
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    Then I verify product detail top up game "<game>" with userId
    Examples:
      |searchInput   |product       |game          |
      |Clash of Clans|ids-game-coc-2|Clash of Clans|

  @navigate_to_detail_product_topUp_game_with_userId_dropdown
  Scenario Outline: User navigate to product details topUp game with userId_dropdown
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    Then I verify product detail top up game "<game>" with userId_dropdown
    Examples:
      |searchInput      |product            |game            |
      |Honkai: Star Rail|ids-game-honkaisr-1|Honkai: Star Rail|

  @pay_detail_topUp_game_page
  Scenario Outline: User navigate to pay detail top up game
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I fill "<zoneId>" zone id
    * I click buy button
    Then I now on detail pembayaran top up game page
    Examples:
      | searchInput   | game          | product          | customerId | zoneId |
      | Mobile Legend | Mobile Legend | ids-game-mlbb-44 | 53549829   | 2012   |

  @verify_error_message_field_input_null
  Scenario Outline: Verify error message field input null on top up game datail
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I click buy button
    Then I verify error message User Id
    Then I verify error message Zone Id
    Examples:
      | searchInput   | game          | product          |
      | Mobile Legend | Mobile Legend | ids-game-mlbb-44 |

  @verify_error_message_field_input_invalid
    Scenario Outline: Verify error message field input null on top up game datail
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I fill "<zoneId>" zone id
    * I click buy button
    Then I verify the error message if the input is wrong
    Examples:
      | searchInput   | game          | product          | customerId | zoneId |
      | Mobile Legend | Mobile Legend | ids-game-mlbb-44 | 32132333   | 2012   |
      | Mobile Legend | Mobile Legend | ids-game-mlbb-44 | 53549829   | 2222   |

  @navigate_to_detail_product_voucher_game
  Scenario Outline: User navigate to detail voucher game page
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" voucher i was looking for
    * I choose one "<product>" of the products
    Then I verify product detail voucher game "<game>"
    Examples:
      | searchInput | game  | product                 | game  |
      | Steam       | Steam | ids-vgame-steamwallet-1 | Steam |

  @pay_detail_product_voucher_game
  Scenario Outline: User navigate to pay detail voucher game page
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" voucher i was looking for
    * I choose one "<product>" of the products
    * I click buy button
    Then I now on detail pembayaran voucher game page
    Examples:
      | searchInput | game  | product                 |
      | Steam       | Steam | ids-vgame-steamwallet-1 |

  @checkout_payment_for_product_topUp_game_have_zoneId_type_textbox
  Scenario Outline: Make checkout payments for game top up products have zoneId type textbox
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I fill "<zoneId>" zone id
    * I click buy button
    * I click button pilih pembayaran
    * I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | searchInput   | game          | product          | customerId | zoneId | payMethod | phoneNumberDana | passcodeDana |
      | Mobile Legend | Mobile Legend | ids-game-mlbb-44 | 53549829   | 2012   | DANA      | 82114017471     | 574514       |

  @checkout_payment_for_product_topUp_game_have_zoneId_type_dropdown
  Scenario Outline: Make checkout payments for game top up products have zoneId type dropdown
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I choose "<server>" zoneId
    * I click buy button
    * I click button pilih pembayaran
    * I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | searchInput       | game              | product             | customerId | server             | payMethod | phoneNumberDana | passcodeDana |
      | Honkai: Star Rail | Honkai: Star Rail | ids-game-honkaisr-1 | 800556131  | prod_official_asia | DANA      | 82114017471     | 574514       |

  @checkout_payment_for_product_topUp_game_not_have_zoneId
  Scenario Outline: Make checkout payments for game top up products not have zoneId
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I click buy button
    * I click button pilih pembayaran
    * I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    And I navigate to check detail transaction
    Examples:
      | searchInput    | game           | product        | customerId | payMethod | phoneNumberDana | passcodeDana |
      | Clash of Clans | Clash of Clans | ids-game-coc-2 | #QQYCYGUU8 | DANA      | 82114017471     | 574514       |

  @checkout_payment_for_product_voucher_game
  Scenario Outline: Make checkout payments for game voucher products
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" voucher i was looking for
    * I choose one "<product>" of the products
    * I click buy button
    * I click button pilih pembayaran
    * I choose a payment method "<payMethod>"
    When I click button bayar on payment checkout
    Then I proceed payment for DANA "<phoneNumberDana>" and "<passcodeDana>"
    Then Solusipayweb show process payment
    Examples:
      | searchInput | game  | product                 | payMethod | phoneNumberDana | passcodeDana |
      | Steam       | Steam | ids-vgame-steamwallet-1 | DANA      | 82114017471     | 574514       |

  @verify_length_error_message_product_topUp_game_userid_zoneId_textbox
  Scenario Outline: Verify error message product top up game where type textbox userid and zoneid
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    Then I verify error message User Id
    Examples:
      | searchInput   | game          | product          | customerId                       |
      | Mobile Legend | Mobile Legend | ids-game-mlbb-44 | 11111111111111111111111111111111 |

  @transaction_topup_game_ML_with_paymentgateway_virgo
  Scenario Outline: Make checkout payments for game top up products have zoneId type textbox
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    When I click product "<game>" top up i was looking for
    When I choose one "<product>" of the products
    When I fill "<customerId>" customer id
    When I fill "<zoneId>" zone id
    When I click buy button
    When I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | searchInput   | game          | product          | customerId | zoneId |
      | Mobile Legend | Mobile Legend | ids-game-mlbb-44 | 53549829   | 2012   |

  @transaction_topup_game_COC_with_paymentgateway_virgo
  Scenario Outline: Make checkout payments for game top up products not have zoneId
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I click buy button
    * I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | searchInput    | game           | product        | customerId |
      | Clash of Clans | Clash of Clans | ids-game-coc-2 | #QQYCYGUU8 |

  @transaction_topup_game_HSR_with_paymentgateway_virgo
  Scenario Outline: Make checkout payments for game top up products have zoneId type dropdown
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I choose "<server>" zoneId
    * I click buy button
    * I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | searchInput       | game              | product             | customerId | server             |
      | Honkai: Star Rail | Honkai: Star Rail | ids-game-honkaisr-1 | 800556131  | prod_official_asia |


  @transaction_topup_game_RagnarokO_with_paymentgateway_virgo
  Scenario Outline: Make checkout payments for game top up products have zoneId type dropdown
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" top up i was looking for
    * I choose one "<product>" of the products
    * I fill "<customerId>" customer id
    * I choose "<server>" zoneId
    * I fill "<username>" username
    * I click buy button
    * I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | searchInput     | game            | product              | customerId | server                        | username |
      | Ragnarok Origin | Ragnarok Origin | ids-game-ragnaroko-1 | TDRWS3PR   | Orc Village-4(-5,-6,-7,-8,-9) | Linnn    |

  @transaction_voucher_game_steam_with_paymentgateway_virgo
  Scenario Outline: Make checkout payments for game voucher products
    Given I on Solusipay Web Voucher Game Page
    When I fill "<searchInput>" a product game on search bar field
    * I click product "<game>" voucher i was looking for
    * I choose one "<product>" of the products
    * I click buy button
    * I click button pilih pembayaran
    Then I choose payment method Virgo
    Then I proceed payment for Virgo
    When Solusipayweb show Success Payment
    Then I can check detail transaction
    And I can check Invoice transaction
    Examples:
      | searchInput | game  | product                 |
      | Steam       | Steam | ids-vgame-steamwallet-1 |
