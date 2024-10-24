Feature: External-Dana Services

  @external_dana
  Scenario: Testing transaction External-Dana
    Given I have Excel data file for testing External-Dana "External-Dana.csv"
    When I perform testing prefer from Excel data
    Then I get the appropriate response
    Then I create report automation