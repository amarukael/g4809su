@SOB_Setorku_Transaction
Feature: Setorku Transaction

  @navigate_setorku_transaction
  Scenario Outline: [SOB SetorKu] Navigate into SetorKu Transaction Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Transaction"
    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @setorku_transaction_filter_by_date
  Scenario: [SOB SetorKu] Get data transaction using Filter by Date -- Successfully
    Given I am in Menu "SetorKu" and Sub-Menu "Transaction"
    * I click filter button on "SetorKu Transaction"
    * I fill From Date ("06,07") and To Date ("05,08") on "SetorKu Transaction"
    When I click apply button filter
    Then Datatable show data "SetorKu Transaction"

  @setorku_transaction_filter_by_field
  Scenario Outline: [SOB SetorKu] Get data transaction using Filter by field "<label>" --"<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Transaction"
    * I click filter button on "SetorKu Transaction"
    * I fill From Date ("01,01") and To Date ("31,01") on "SetorKu Transaction"
    * I click field "<label>" and fill with "<value>" on Setorku Transaction
    When I click apply button filter
    Then Datatable show data "SetorKu Transaction"
    Examples:
      | label         | value               | condition |
      | Tracking Reff | 387589446           | Success   |
      | Tracking Reff | 387589347           | Success   |
      | Tracking Reff | 190665057701        | Success   |
      | Product ID    | Mega Auto Finance   | Success   |
      | Product ID    | Oto finance Mobil   | Success   |
      | Payment Code  | 11122400234001      | Success   |
      | Payment Code  | 21952400267001      | Success   |
      | Payment Code  | STX3202401054411002 | Success   |
      | Receipt No    | 2024013121353421718 | Success   |
      | Receipt No    | 20240131213400ST388 | Success   |
      | Receipt No    | 20240131213035ST459 | Success   |
      | Status        | Success             | Success   |
      | Status        | Failed              | Success   |
      | Status        | Pending             | Success   |
      | Status        | Expired             | Success   |
      | Tracking Reff | random,20           | Failed    |
      | Tracking Reff | random,21           | Failed    |
      | Product ID    | PISANG GORENG       | Failed    |
      | Payment Code  | random,64           | Failed    |
      | Payment Code  | random,65           | Failed    |
      | Receipt No    | random,20           | Failed    |
      | Receipt No    | random,21           | Failed    |


  @merchant_oy_transaction_filter_by_all_field
  Scenario Outline: [SOB SetorKu] Get data transaction using Filter by all field --"<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Transaction"
    * I click filter button on "SetorKu Transaction"
    * I fill From Date ("01,01") and To Date ("31,01") on "SetorKu Transaction"
    * I click field "Tracking Reff" and fill with "<tracking>" on Setorku Transaction
    * I click field "Product ID" and fill with "<product>" on Setorku Transaction
    * I click field "Payment Code" and fill with "<paycode>" on Setorku Transaction
    * I click field "Receipt No" and fill with "<receipt>" on Setorku Transaction
    * I click field "Status" and fill with "<status>" on Setorku Transaction
    When I click apply button filter
    Then Datatable show data "SetorKu Transaction"
    Examples:
      | tracking  | product           | paycode        | receipt             | status  | condition |
      | 387588731 | Mega Auto Finance | 11112400696001 | 2024013121372811695 | Success | Success   |
      | random,20 | Mega Auto Finance | 11112400696001 | 2024013121372811695 | Success | Failed    |
      | 387588731 | Oto finance Mobil | 11112400696001 | 2024013121372811695 | Success | Failed    |
      | 387588731 | Mega Auto Finance | random,64      | 2024013121372811695 | Success | Failed    |
      | 387588731 | Mega Auto Finance | 11112400696001 | random,20           | Success | Success   |
      | 387588731 | Mega Auto Finance | 11112400696001 | 2024013121372811695 | Failed  | Failed    |
      | random,20 | PISANG GORENG     | random,64      | random,20           | Failed  | Failed    |
      | random,21 | PISANG GORENG     | random,65      | random,21           | Failed  | Failed    |