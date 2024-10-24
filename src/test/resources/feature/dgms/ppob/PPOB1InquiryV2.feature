Feature: PPOB Inquiry V2
  Testing API PPOB Inquiry V2

  @inquiry_v2_sukses
  Scenario: [PPOB] Partner hit API Inquiry V2 PPOB
    Given Valid Request ("SLS", "edu-institut-bisnis-nusantara-ibn", "21000098", "", "DEV") API Inquiry V2 PPOB
    When Partner hits API Inquiry V2 PPOB
    Then Partner gets response "00" API Inquiry V2 PPOB

