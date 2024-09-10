@SOB_Dana_Transaction_List
Feature: Merchant OY Transaction

  @navigate_merchant_oy_transaction
  Scenario Outline: [SOB Merchant OY] Navigate into Merchant OY Transaction Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "Merchant OY, Transaction"
    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @merchant_oy_transaction_filter_by_date
  Scenario: [SOB Merchant OY] Get data transaction using Filter by Date -- Successfully
    Given I am in Menu "Merchant OY" and Sub-Menu "Transaction"
    * I click filter button on "Merchant OY Transaction"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Transaction"
    When I click apply button filter
    Then Datatable show data "Merchant OY"

  @merchant_oy_transaction_filter_by_field
  Scenario Outline: [SOB Merchant OY] Get data transaction using Filter by field "<label>" --"<condition>"
    Given I am in Menu "Merchant OY" and Sub-Menu "Transaction"
    * I click filter button on "Merchant OY Transaction"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Transaction"
    * I click Field "<label>" and Fill With "<value>" on Merchant OY Transaction
    When I click apply button filter
    Then Datatable show data "Merchant OY Transaction"
    Examples:
      | label           | value                | condition |
      | Transactions ID | OYGN0F95P383IF1BTQFG | Success   |
      | Product ID      | 37                   | Success   |
      | Payment Code    | 0910606713960419     | Success   |
      | Partner Name    | OY                   | Success   |
      | Transactions ID | random,5             | Failed    |
      | Product ID      | random,5             | Failed    |
      | Payment Code    | random,5             | Failed    |


  @merchant_oy_transaction_filter_by_all_field
  Scenario: [SOB Merchant OY] Get data transaction using Filter by all field
    Given I am in Menu "Merchant OY" and Sub-Menu "Transaction"
    * I click filter button on "Merchant OY Transaction"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Transaction"
    Then I fill all fields on filter Merchant OY Transaction
    When I click apply button filter
    Then Datatable show data "Merchant OY Transaction"

  @merchant_oy_transaction_filter_by_all_field_failed
  Scenario Outline: [SOB Merchant OY] Get data transaction using Filter by all field --failed
    Given I am in Menu "Merchant OY" and Sub-Menu "Transaction"
    * I click filter button on "Merchant OY Transaction"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Transaction"
    Then I fill all fields on filter Merchant OY Transaction with random value field "<label>"
    When I click apply button filter
    Then Datatable show data "Merchant OY Transaction"
    Examples:
      | label           |
      | Transactions ID |
      | Product ID      |
      | Payment Code    |
