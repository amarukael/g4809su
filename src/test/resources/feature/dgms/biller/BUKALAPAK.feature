Feature: Bukalapak API

  @inq
  Scenario: Inquiry
    Given I have Excel data file "Excel Data/bukalapak.xlsx" and sheet "PLNPOST"
    Then I perform POST request "/inquiry" and get Response

  Scenario: Payment
    Given I have Excel data file "Excel Data/bukalapak.xlsx" and sheet "Sheet2"
    Then I perform POST request "/payment" and get Response

  @PLNPOST
  Scenario: Bukalapak
    Given I have Excel data file "Excel Data/bukalapak.xlsx" and sheet "PLNPOST"
    Then I perform post for inq,pay,adv and then verify the rc

  @PLNPRE
  Scenario: Bukalapak
    Given  I have Excel data file "Excel Data/bukalapak.xlsx" and sheet "PLNPRE"
    Then  I perform post for inq,pay,adv and then verify the rc

  @EWALLET
  Scenario: Bukalapak
    Given  I have Excel data file "Excel Data/bukalapak.xlsx" and sheet "EWALLET"
    Then  I perform post for inq,pay,adv and then verify the rc