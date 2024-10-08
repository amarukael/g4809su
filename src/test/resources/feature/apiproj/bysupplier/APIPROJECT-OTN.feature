Feature: OTN Multifinance

  @xml
  Scenario: inqpayadvOTNADF
    Given I have Excel data file "exceldata/otn_multifinance.xlsx" and sheet "ADF"
    Then I perform POST for inq pay rev XML

  @xml
  Scenario: inqpayadvOTNFIF
    Given I have Excel data file "exceldata/otn_multifinance.xlsx" and sheet "FIF"
    Then I perform POST for inq pay rev XML
