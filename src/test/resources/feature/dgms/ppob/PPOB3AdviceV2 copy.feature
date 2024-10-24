Feature: PPOB Advice
  Testing API PPOB Advice

  @inquiry_v2_sukses
  Scenario: [PPOB] Partner hit API Inquiry V2 PPOB
    Given Valid Request ("SLS", "edu-institut-bisnis-nusantara-ibn", "21000098", "", "DEV") API Inquiry V2 PPOB
    When Partner hits API Inquiry V2 PPOB
    Then Partner gets response "00" API Inquiry V2 PPOB

  @payment_v2_sukses
  Scenario: [PPOB] Parner hit API Payment V2 PPOB
    Given Valid Request ("DMY", "SIM50K1", "081234000001", "51000", "", "IDS", 0) API Payment V2 PPOB
    When Partner hits API Payment V2 PPOB
    Then Partner gets response "00" API Payment V2 PPOB

  @advice_v2_sukses
  Scenario: [PPOB] Partner hit API Advice V2 PPOB
    Given Valid Request ("21000098", "") API Advice V2 PPOB
    When Partner hits API Advice V2 PPOB
    Then Partner gets response "00" API Advice V2 PPOB