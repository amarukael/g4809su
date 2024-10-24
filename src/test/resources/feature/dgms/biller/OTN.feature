Feature: OTN Api
  @PULSA
  Scenario: Pulsa
    Given I have Excel data file "Excel Data/otn.xlsx" and sheet "OTN_PULSA"
    Then I perform post for inq,pay,adv and then verify the rc

  @EWALLET
  Scenario: Ewallet
    Given I have Excel data file "Excel Data/otn.xlsx" and sheet "OTN_E_WALLET"
    Then I perform post for inq,pay,adv and then verify the rc

  @PPOB
  Scenario: inqpayadvOTNADFviaPPOB
    Given I have Excel data file "Excel Data/otn_multifinance.xlsx" and sheet "ADFviaPPOB"
    Then I perform post for inq,pay,adv and then verify the rc

  @PPOB
  Scenario: inqpayadvOTNFIFviaPPOB
    Given I have Excel data file "Excel Data/otn_multifinance.xlsx" and sheet "FIFviaPPOB"
    Then I perform post for inq,pay,adv and then verify the rc