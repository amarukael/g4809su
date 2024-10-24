Feature: ALTERRA API
  @PULSA
  Scenario: Pulsa
    Given I have Excel data file "Excel Data/alterra.xlsx" and sheet "ALTERRA_PULSA"
    Then I perform post for inq,pay,adv and then verify the rc