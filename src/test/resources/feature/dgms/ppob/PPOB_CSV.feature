Feature: PPOB Advice
  Testing API PPOB Advice

  Scenario: test apiproject non-alfa
    Given I have CSV data file for testing PPOB "C:/Users/fahmi.amaruddin/Downloads/ppob-master-dataset - Inquiry -_ Payment.csv"
    When I perform testing prefer from CSV data for PPOB
    Then I get the appropriate response for PPOB
    Then I create EXCEL report automation PPOB
