Feature: External Partner Dana services

  @external_dana_inq_sukses
  Scenario Outline: [EXTERNAL-DANA] Partner Dana hit API Inquiry External
    Given Valid Request ("<productCatagory>", "<supplierId>", "<productId>", "<customerId>", "<rc>", "<rcDesc>") API Inquiry External-Dana
    When Partner hit API Inquiry External-Dana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry External-Dana
    Examples:
      | productCatagory | supplierId     | productId | customerId   | rc | rcDesc  |
      | PLN Post        | MKM            | 3002      | 232001104757 | 10 | SUCCESS |
      | PLN Post        | PPOB-JPA       | 3002      | 534684755902 | 10 | SUCCESS |
      | PLN Post        | PPOB-BUKALAPAK | 3002      | 520000000000 | 10 | SUCCESS |
      | PLN Post        | PPOB-MKM       | 3002      | 232001104758 | 10 | SUCCESS |
      | PLN Post        | PPOB-ALTERRA   | 3002      | 512345610000 | 10 | SUCCESS |
#      | PLN Post        | PPOB-TEKTAYA   | 3002      |            | 10 | SUCCESS |

  @external_dana_pay_sukses
  Scenario Outline: [EXTERNAL-DANA] Partner Dana hit API Payment External
    Given Valid Request ("<productCatagory>", "<supplierId>", "<productId>", "<customerId>", "<requestId>", "<rc>", "<rcDesc>") API Payment External-Dana
    When Partner hit API Payment External-Dana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Payment External-Dana
    Examples:
      | productCatagory | supplierId     | productId | customerId   | requestId                  | rc | rcDesc  |
      | PLN Post        | MKM            | 3002      | 232001104757 | 20240827000000000000000008 | 10 | SUCCESS |
      | PLN Post        | PPOB-JPA       | 3002      | 534684755902 | 20240827000000000000000010 | 10 | SUCCESS |
      | PLN Post        | PPOB-BUKALAPAK | 3002      | 520000000000 | 20240828000000000000000002 | 10 | SUCCESS |
      | PLN Post        | PPOB-MKM       | 3002      | 232001104758 | 20240827000000000000000009 | 10 | SUCCESS |
      | PLN Post        | PPOB-ALTERRA   | 3002      | 512345610000 | 20240828000000000000000003 | 10 | SUCCESS |
#      | PLN Post        | PPOB-TEKTAYA   | 3002      |            |           | 10 | SUCCESS |

  @external_dana_adv_sukses
  Scenario Outline: [EXTERNAL-DANA] Partner Dana hit API Advice External
    Given Valid Request ("<productCatagory>", "<supplierId>", "<productId>", "<customerId>", "<requestId>", "<rc>", "<rcDesc>") API Advice External-Dana
    When Partner hit API Advice External-Dana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Advice External-Dana
    Examples:
      | productCatagory | supplierId     | productId | customerId   | requestId                  | rc | rcDesc  |
      | PLN Post        | MKM            | 3002      | 232001104757 | 20240827000000000000000008 | 10 | SUCCESS |
      | PLN Post        | PPOB-JPA       | 3002      | 534684755902 | 20240827000000000000000010 | 10 | SUCCESS |
      | PLN Post        | PPOB-BUKALAPAK | 3002      | 520000000000 | 20240828000000000000000002 | 10 | SUCCESS |
      | PLN Post        | PPOB-MKM       | 3002      | 232001104758 | 20240827000000000000000009 | 10 | SUCCESS |
      | PLN Post        | PPOB-ALTERRA   | 3002      | 512345610000 | 20240828000000000000000003 | 10 | SUCCESS |