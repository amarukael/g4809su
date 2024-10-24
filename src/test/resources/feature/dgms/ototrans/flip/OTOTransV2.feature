Feature: OTOTrans V2 Biller Flip
  Testing API OTOTrans V2 Biller Flip

  @inquiry_sukses
  Scenario: Partner hit API Inquiry V2 OTOTrans -- successfully
    Given Valid Request ("SLS", "002", "240319202", "{\"bankCode\":\"002\",\"bankAccount\":\"067201000299301\",\"amountTransfer\":\"10000\",\"purpose\":\"Test Transfer\",\"senderId\":\"240319202\",\"senderName\":\"Tester 1\"}", "STG") API Inquiry "V2" OTOTrans
    When Partner hits API Inquiry "V2" OTOTrans
    Then Partner gets response "00" API Inquiry "V2" OTOTrans

  @payment_sukses
  Scenario: Parner hit API Payment V2 OTOTrans -- successfully
    Given Valid Request ("{\"bankCode\":\"002\",\"bankAccount\":\"067201000299301\",\"amountTransfer\":\"10000\",\"purpose\":\"Test Transfer\",\"senderId\":\"240319202\",\"senderName\":\"Tester 1\"}", "10000") API Payment "V2" OTOTrans
    When Partner hits API Payment "V2" OTOTrans
    Then Partner gets response "68" API Payment "V2" OTOTrans

  @advice_sukses
  Scenario: Partner hit API Advice V2 OTOTrans -- successfully
    Given Valid Request ("FLIP", "") API Advice "V2" OTOTrans
    When Partner hits API Advice "V2" OTOTrans
    Then Partner gets response "00" API Advice "V2" OTOTrans