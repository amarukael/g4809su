Feature: External Partner Dana service Auto Advice

  @external_dana_inq_sukses
  Scenario Outline: [EXTERNAL-DANA] Partner Dana hit API Inquiry External
    Given Valid Request ("<productCatagory>", "<supplierId>", "<productId>", "<customerId>", "<rc>", "<rcDesc>") API Inquiry External-Dana
    When Partner hit API Inquiry External-Dana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Inquiry External-Dana
    Examples:
      | productCatagory | supplierId         | productId | customerId   | rc | rcDesc  |
      | PDAM            | IDSRestProjectDana | 29005     | 00000747     | 10 | SUCCESS |
      | Multi Finance   | APIProject         | 1BMF      | 9920010002   | 10 | SUCCESS |
      | PLN Post        | JPA                |           |              | 10 | SUCCESS |
      | PLN Post        | MKM                |           |              | 10 | SUCCESS |
      | PLN Pre         | JPA                | 3003      | 149999999933 | 10 | SUCCESS |
      | PLN Pre         | MKM                |           |              | 10 | SUCCESS |
      | PLN Pre         | BMS                |           |              | 10 | SUCCESS |
      | Telkom          | PPOB               |           |              | 10 | SUCCESS |
      | HPPOSTPAID      | PPOB               |           |              | 10 | SUCCESS |
      | SPEEDY          | PPOB               |           |              | 10 | SUCCESS |
      | TVCABLE         | PPOB               |           |              | 10 | SUCCESS |
      | ASURANSIBIMA    | PPOB               |           |              | 10 | SUCCESS |
      | HPPOSTPAID      | PPOB               |           |              | 10 | SUCCESS |

  @external_dana_adv_sukses
  Scenario Outline: [EXTERNAL-DANA] Partner Dana hit API Advice External
    Given Valid Request ("<productCatagory>", "<supplierId>", "<productId>", "<customerId>", "<requestId>", "<rc>", "<rcDesc>") API Advice External-Dana
    When Partner hit API Advice External-Dana
    Then Partner gets response "<rc>" and response desc "<rcDesc>" API Advice External-Dana
    Examples:
      | productCatagory | supplierId         | productId | customerId   | requestId                  | rc | rcDesc  |
      | PDAM            | IDSRestProjectDana | 29005     | 00000747     | 20240909000000000000000021 | 30 | FAILED  |
      | Multi Finance   | APIProject         | 1BMF      | BMF00011     | 20240909000000000000000022 | 30 | FAILED  |
      | PLN Post        | MKM                | 3002      | 232001104754 | 20240909000000000000000023 | 30 | FAILED  |
      | PLN Post        | BMS                | 3002      | 146300068064 | 20240909000000000000000024 | 30 | FAILED  |
      | PLN Pre         | JPA                | 3003      | 149999999933 | 20240909000000000000000025 | 30 | FAILED  |
      | PLN Post        | PPOB               | 3002      |              | 20240909000000000000000026 | 30 | FAILED  |
      | Telkom          | PPOB               |           |              | 20240909000000000000000027 | 30 | FAILED  |
      | HPPOSTPAID      | PPOB               |           |              | 20240909000000000000000028 | 30 | FAILED  |
      | SPEEDY          | PPOB               |           |              | 20240909000000000000000029 | 30 | FAILED  |
      | TVCABLE         | PPOB               | 6TVTLKMV  | 127608160220 | 20240909000000000000000030 | 30 | FAILED  |
      | ASURANSIBIMA    | PPOB               |           |              | 20240909000000000000000031 | 30 | FAILED  |
      | HPPOSTPAID      | PPOB               | 8HPFREN   | 088298626367 | 20240909000000000000000032 | 30 | FAILED  |
      |                 |                    |           |              |                            |    |         |
      | PDAM            | IDSRestProjectDana | 29005     | 00000747     | 20240909000000000000000001 | 10 | SUCCESS |
      | Multi Finance   | APIProject         | 1BMF      | BMF00011     | 20240909000000000000000002 | 10 | SUCCESS |
      | PLN Post        | MKM                | 3002      | 232001104754 | 20240909000000000000000003 | 10 | SUCCESS |
      | PLN Post        | BMS                | 3002      | 146300068064 | 20240909000000000000000004 | 10 | SUCCESS |
      | PLN Pre         | JPA                | 3003      | 149999999933 | 20240909000000000000000005 | 10 | SUCCESS |
      | PLN Post        | PPOB               | 3002      |              | 20240909000000000000000006 | 10 | SUCCESS |
      | Telkom          | PPOB               |           |              | 20240909000000000000000007 | 10 | SUCCESS |
      | HPPOSTPAID      | PPOB               |           |              | 20240909000000000000000008 | 10 | SUCCESS |
      | SPEEDY          | PPOB               |           |              | 20240909000000000000000009 | 10 | SUCCESS |
      | TVCABLE         | PPOB               | 6TVTLKMV  | 127608160220 | 20240909000000000000000010 | 10 | SUCCESS |
      | ASURANSIBIMA    | PPOB               |           |              | 20240909000000000000000011 | 10 | SUCCESS |
      | HPPOSTPAID      | PPOB               | 8HPFREN   | 088298626367 | 20240909000000000000000012 | 10 | SUCCESS |