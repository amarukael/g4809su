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
  Scenario Outline: [SOB SetorKu] Get data transaction using Filter by field --"<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Transaction"
    * I click filter button on "SetorKu Transaction"
    * I fill From Date ("06,07") and To Date ("05,08") on "SetorKu Transaction"
    * I click field "<label>" and fill with "<value>" on Setorku Transaction
    When I click apply button filter
    Then Datatable show data "SetorKu Transaction"

    Examples:
      | label  | value   | condition |
      | Tracking Reff |            413409887 | Success   |
      | Tracking Reff |         278485315344 | Success   |
      | Tracking Reff |            413385448 | Success   |
      | Product ID    | Mega Auto Finance    | Success   |
      | Product ID    | Oto Finance Motor    | Success   |
      | Payment Code  | STX3202408006222001  | Success   |
      | Payment Code  | 50220240804047187001 | Success   |
      | Payment Code  |       21702403795001 | Success   |
      | Receipt No    |  2024080418504724992 | Success   |
      | Receipt No    |  20240804183701ST429 | Success   |
      | Receipt No    |  2024080418243350751 | Success   |
      | Status        | Success              | Success   |
      | Tracking Reff | random,20            | Failed    |
      | Tracking Reff | random,21            | Failed    |
      | Product ID    | Produk TST           | Failed    |
      | Payment Code  | random,64            | Failed    |
      | Payment Code  | random,65            | Failed    |
      | Receipt No    | random,20            | Failed    |
      | Receipt No    | random,21            | Failed    |

  @setorku_transaction_filter_by_all_field
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
      | 387428899 | Mega Auto Finance | 18632400085001 | 2024013021315818072 | Success | Success   |
      | random,20 | Mega Auto Finance | 18632400085001 | 2024013021315818072 | Success | Failed    |
      | 387428899 | Oto finance Mobil | 18632400085001 | 2024013021315818072 | Success | Failed    |
      | 387428899 | Mega Auto Finance | random,64      | 2024013021315818072 | Success | Failed    |
      | 387428899 | Mega Auto Finance | 18632400085001 | random,20           | Success | Success   |
      | 387428899 | Mega Auto Finance | 18632400085001 | 2024013021315818072 | Failed  | Failed    |
      | random,20 | Produk TST        | random,64      | random,20           | Failed  | Failed    |
      | random,21 | Produk TST        | random,65      | random,21           | Failed  | Failed    |
