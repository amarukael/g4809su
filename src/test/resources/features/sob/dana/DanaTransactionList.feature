@SOB_Dana_Transaction_List
Feature: Dana Transaction List

  @navigate_DANA_transaction
  Scenario Outline: [SOB DANA] Navigate into DANA Transaction Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "DANA, Transaction History"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @DANA_Transaction_filter_by_date
  Scenario: [SOB DANA] Get data using Filter by Date -- Successfully
    Given I am in Menu "DANA" and Sub-Menu "Transaction History"
    * I click filter button on "DANA Transaction"
    * I fill From Date ("18,06") and To Date ("17,07") on "DANA Transaction"
    When I click apply button filter
    Then Datatable show data "DANA Transaction"

  @DANA_Transaction_filter_by_data
  Scenario Outline: [SOB DANA] Get data using Filter by field "<label>" --"<condition>"
    Given I am in Menu "DANA" and Sub-Menu "Transaction History"
    * I click filter button on "DANA Transaction"
    * I fill From Date ("18,06") and To Date ("17,07") on "DANA Transaction"
    * I Click Field "<label>" and Fill With "<value>" on DANA Transaction
    When I click apply button filter
    Then Datatable show data "DANA Transaction"

    Examples:
      | label  | value   | condition |
      | Status | Success | Success   |
      # | Tracking Reff  | 20240716022306903561192             | Success   |
      # | Acquisition ID | 20240716111212800100166730217811630 | Success   |
      # | Customer ID    | 552023060116                        | Success   |
      # | Product Name   | TELKOMSEL (HALO)                    | Success   |
      # | Ref/Token      | D00587                              | Success   |
      # | Amount         | 1108700                             | Success   |
      # | Tracking Reff  | random                              | failed    |
      # | Acquisition ID | random                              | failed    |
      # | Customer ID    | random                              | failed    |
      # | Product Name   | random                              | failed    |
      # | Ref/Token      | random                              | failed    |
      # | Amount         | 000000                              | failed    |

  @DANA_Transaction_filter_by_all_data
  Scenario: [SOB DANA] Get data using Filter by all field
    Given I am in Menu "DANA" and Sub-Menu "Transaction History"
    * I click filter button on "DANA Transaction"
    * I fill From Date ("18,06") and To Date ("17,07") on "DANA Transaction"
    Then I fill all fields on filter DANA Transaction
    When I click apply button filter
    Then Datatable show data "DANA Transaction"
