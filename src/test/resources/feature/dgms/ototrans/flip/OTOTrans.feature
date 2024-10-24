Feature: OTOTrans V1 Biller Flip
  Testing API OTOTrans V1 Biller Flip

  @inquiry_sukses
  Scenario: Partner hit API Inquiry V1 OTOTrans -- successfully
    Given Valid Request ("SLS", "014", "240319002", "{\"bankCode\":\"014\",\"bankAccount\":\"8760673566\",\"amountTransfer\":\"10000\",\"purpose\":\"Test Transfer\",\"senderId\":\"240319002\",\"senderName\":\"Tester 1\"}", "STG") API Inquiry "V1" OTOTrans
    When Partner hits API Inquiry "V1" OTOTrans
    Then Partner gets response "00" API Inquiry "V1" OTOTrans

  @payment_sukses
  Scenario: Parner hit API Payment V1 OTOTrans -- successfully
    Given Valid Request ("{\"bankCode\":\"014\",\"bankAccount\":\"8760673566\",\"amountTransfer\":\"10000\",\"purpose\":\"Test Transfer\",\"senderId\":\"240319002\",\"senderName\":\"Tester 1\"}", "10000") API Payment "V1" OTOTrans
    When Partner hits API Payment "V1" OTOTrans
    Then Partner gets response "68" API Payment "V1" OTOTrans

  @advice_sukses
  Scenario: Partner hit API Advice V1 OTOTrans -- successfully
    Given Valid Request ("FLIP", "") API Advice "V1" OTOTrans
    When Partner hits API Advice "V1" OTOTrans
    Then Partner gets response "00" API Advice "V1" OTOTrans