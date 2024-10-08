Feature: PPOB Payment V2
  Testing API PPOB Payment V2

  @inquiry_v2_sukses
  Scenario: [PPOB] Partner hit API Inquiry V2 PPOB
    Given Valid Request ("DMY", "SIM50K1", "081234000001", "", "IDS") API Inquiry V2 PPOB
    When Partner hits API Inquiry V2 PPOB
    Then Partner gets response "00" API Inquiry V2 PPOB

  @payment_v2_sukses
  Scenario: [PPOB] Partner hit API Payment V2 PPOB
    Given Valid Request ("DMY", "SIM50K1", "081234000001", "51000", "", "IDS", 0) API Payment V2 PPOB
    When Partner hits API Payment V2 PPOB
    Then Partner gets response "00" API Payment V2 PPOB

  @payment_v2_async_sukses
  Scenario Template: [PPOB] Partner hit API Payment V2 Async PPOB
    Given Valid Request ("<partnerId>", "<productId>", "<customerId>", "<totalAmount>", "<extendInfo>", "<terminalId>", <flgInq>) API Payment V2 Async PPOB
    When Partner hits API Payment V2 Async PPOB
    Then Partner gets response "<rc>" API Payment V2 Async PPOB
    Examples:
      | partnerId | productId | customerId   | totalAmount | extendInfo | terminalId | flgInq | rc |
      | DMY       | SIM50K1   | 081234000001 | 51000       |            | IDS        | 0      | 10 |
      | DMY       | SIM50     | 081234000001 | 50000       |            | IDS        | 0      | 10 |