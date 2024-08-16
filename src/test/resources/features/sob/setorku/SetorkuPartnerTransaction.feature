@SOB_Setorku_Transaction
Feature: Setorku Transaction

  @navigate_setorku_partner_transaction
  Scenario Outline: [SOB SetorKu] Navigate into SetorKu Partner Transaction Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Partner Transaction"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @setorku_partner_transaction_filter_by_date
  Scenario Outline: [SOB SetorKu] Get data transaction using Filter by Date -- "<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Partner Transaction"
    * I click filter button on "SetorKu Partner Transaction"
    * I fill From Date ("30,07") and To Date ("05,08") on "SetorKu Partner Transaction"
    * I choose "<partner>" partnerID on filter field
    When I click apply button filter
    Then Datatable show data "SetorKu Partner Transaction"

    Examples:
      | partner | condition |
      | PTF     | Success   |
      |      25 | Failed    |

  @setorku_partner_transaction_filter_by_field
  Scenario Outline: [SOB SetorKu] Get data transaction using Filter by field "<label>" --"<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Partner Transaction"
    * I click filter button on "SetorKu Partner Transaction"
    * I fill From Date ("30,07") and To Date ("05,08") on "SetorKu Partner Transaction"
    * I choose "<partner>" partnerID on filter field
    * I click field "<label>" and fill with "<value>" on Setorku Partner Transaction
    When I click apply button filter
    Then Datatable show data "SetorKu Partner Transaction"

    Examples:
      | partner | label         | value                             | condition |
      | PTF     | Tracking Reff |                         412748815 | Success   |
      | OTO     | Payment Code  |              50120240802150479001 | Success   |
      | SOF     | User Name     | SOF.SSS.MASAMBA.PALOPO            | Success   |
      | SOF     | User Name     | randomcase,SOF.SSS.MASAMBA.PALOPO | Success   |
      | PTF     | Receipt No    |               20240731165048PT062 | Success   |
      | OTO     | Branch Name   | MAKASSAR                          | Success   |
      | SOF     | User Name     | randomcase,MASAMBA                | Failed    |
      | PTF     | Tracking Reff | random,20                         | Failed    |
      | OTO     | Payment Code  | random,64                         | Failed    |
      | SOF     | User Name     | random,50                         | Failed    |
      | PTF     | Receipt No    | random,20                         | Failed    |
      | OTO     | Branch Name   | SAMARINDA                         | Failed    |
      | PTF     | Tracking Reff | random,21                         | Failed    |
      | OTO     | Payment Code  | random,65                         | Failed    |
      | SOF     | User Name     | random,51                         | Failed    |
      | PTF     | Receipt No    | random,21                         | Failed    |

  @setorku_partner_transaction_filter_by_all_field
  Scenario Outline: [SOB SetorKu] Get data transaction using Filter by all field --"<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Partner Transaction"
    * I click filter button on "SetorKu Partner Transaction"
    * I fill From Date ("30,07") and To Date ("05,08") on "SetorKu Partner Transaction"
    * I choose "<partner>" partnerID on filter field
    * I click field "Tracking Reff" and fill with "<tracking>" on Setorku Partner Transaction
    * I click field "Payment Code" and fill with "<paycode>" on Setorku Partner Transaction
    * I click field "User Name" and fill with "<username>" on Setorku Partner Transaction
    * I click field "Receipt No" and fill with "<receipt>" on Setorku Partner Transaction
    * I click field "Branch Name" and fill with "<branch>" on Setorku Partner Transaction
    When I click apply button filter
    Then Datatable show data "SetorKu Partner Transaction"

    Examples:
      | tracking  | partner | paycode              | username                 | receipt             | branch  | condition |
      | 413208567 | SOF     | 50220240803178054001 | SOF.SSS.LEUWILIANG.BOGOR | 2024080312591450232 | BOGOR   | Success   |
      | random,20 | SOF     | 50220240803178054001 | SOF.SSS.LEUWILIANG.BOGOR | 2024080312591450232 | BOGOR   | Success   |
      | 413208567 | SOF     | random,64            | SOF.SSS.LEUWILIANG.BOGOR | 2024080312591450232 | BOGOR   | Success   |
      | 413208567 | SOF     | 50220240803178054001 | random,50                | 2024080312591450232 | BOGOR   | Success   |
      | 413208567 | SOF     | 50220240803178054001 | SOF.SSS.LEUWILIANG.BOGOR | random,20           | BOGOR   | Success   |
      | 413208567 | SOF     | 50220240803178054001 | SOF.SSS.LEUWILIANG.BOGOR | 2024080312591450232 | CIREBON | Success   |
      | 413208567 | SOF     | 50220240803178054001 | LEUWILIANG               | 2024080312591450232 | BOGOR   | Success   |
