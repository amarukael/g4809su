Feature: OTOTrans Payment V2
  Testing API OTOTrans Payment V2

  @inquiry_sukses
  Scenario: Partner hit API Inquiry V2 OTOTrans -- successfully
    Given Valid Request ("SLS", "014", "240318009", "{\"bankCode\":\"014\",\"bankAccount\":\"8760673566\",\"amountTransfer\":\"10000\",\"purpose\":\"Test Transfer\",\"senderId\":\"240318009\",\"senderName\":\"Tester 1\"}", "STG") API Inquiry "V2" OTOTrans
    When Partner hits API Inquiry "V2" OTOTrans
    Then Partner gets response "00" API Inquiry "V2" OTOTrans

  @payment_sukses
  Scenario: Parner hit API Payment V2 OTOTrans -- successfully
    Given Valid Request ("{\"bankCode\":\"014\",\"bankAccount\":\"8760673566\",\"amountTransfer\":\"10000\",\"purpose\":\"Test Transfer\",\"senderId\":\"240318009\",\"senderName\":\"Tester 1\"}", "10000") API Payment "V2" OTOTrans
    When Partner hits API Payment "V2" OTOTrans
    Then Partner gets response "68" API Payment "V2" OTOTrans