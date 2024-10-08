Feature: APIPROJECT

  @xml @bmi
  Scenario: inqpayrev
    Given I have Excel data file "exceldata/apiproject_masking.xlsx" and sheet "bmi"
    Then I perform POST for inq pay rev XML