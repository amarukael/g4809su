Feature: OTOTrans Inquiry V2
  Testing API OTOTrans Inquiry V2

  @inquiry_sukses
  Scenario: Partner hit API Inquiry V2 OTOTrans -- successfully
    Given Valid Request ("SLS", "014", "240318008", "{\"bankCode\":\"014\",\"bankAccount\":\"8760673566\",\"amountTransfer\":\"10000\",\"purpose\":\"Test Transfer\",\"senderId\":\"240318008\",\"senderName\":\"Tester 1\"}", "STG") API Inquiry "V2" OTOTrans
    When Partner hits API Inquiry "V2" OTOTrans
    Then Partner gets response "00" API Inquiry "V2" OTOTrans

