@SOB_Setorku_PaymentCodeList
Feature: Setorku Payment Code List

  @navigate_setorku_payment_code_list
  Scenario Outline: [SOB][SetorKu][FE] Payment Code List Navigate into SetorKu Payment Code List Page
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Payment Code List"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      # | Failed       | rhesa    |

  @setorku_payment_code_list_filter_by_field
  Scenario Outline: [stg][SOB][SetorKu][FE] Payment Code List - Get data using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"
    * I click filter button on "SetorKu Payment Code List"
    * I fill From Date ("20,07") and To Date ("20,08") on "SetorKu Payment Code List"
    * I click field "<label>" and fill with "<value>" on Setorku Payment Code List
    When I click apply button filter
    Then Datatable show data "SetorKu Payment Code List"

    Examples:
      | label                   | value                     |
      | Merchant Transaction ID |      85204387742795111680 |
      | Payment Code            |      50120240724716945001 |
      | Product Name            | Oto Finance Motor         |
      | User Code               | FC                        |
      | Username                | agan.jalaludin            |
      | Username                | randomCase,agan.jalaludin |
      | Merchant Transaction ID | random,20                 |
      | Merchant Transaction ID | random,21                 |
      | Payment Code            | random,64                 |
      | Payment Code            | random,65                 |
      | Product Name            | Mega Central Finance      |
      | User Code               | random,12                 |
      | User Code               | random,13                 |
      | Username                | random,50                 |
      | Username                | random,51                 |

  @setorku_payment_code_list_filter_by_all_field
  Scenario Outline: [stg][SOB][SetorKu][FE] Payment Code List - Get data using Filter by all field --"<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"
    * I click filter button on "SetorKu Payment Code List"
    * I fill From Date ("20,07") and To Date ("20,08") on "SetorKu Payment Code List"
    * I click field "Merchant Transaction ID" and fill with "<id>" on Setorku Payment Code List
    * I click field "Payment Code" and fill with "<code>" on Setorku Payment Code List
    * I click field "Product Name" and fill with "<productName>" on Setorku Payment Code List
    * I click field "User Code" and fill with "<userCode>" on Setorku Payment Code List
    * I click field "Username" and fill with "<username>" on Setorku Payment Code List
    When I click apply button filter
    Then Datatable show data "SetorKu Payment Code List"

    Examples:
      | id                   | code                 | productName       | userCode  | username  | condition |
      | 85204387742795111680 | 50220240726111680001 | Oto Finance Motor | FC        | dedi.ridwan  | Success   |
      |                      | 50220240726111680001 | Oto Finance Motor | FC        | dedi.ridwan  | Success   |
      | 85204387742795111680 |                      | Oto Finance Motor | FC        | dedi.ridwan  | Success   |
      | 85204387742795111680 | 50220240726111680001 |                   | FC        | dedi.ridwan  | Success   |
      | 85204387742795111680 | 50220240726111680001 | Oto Finance Motor |           | dedi.ridwan  | Success   |
      | 85204387742795111680 | 50220240726111680001 | Oto Finance Motor | FC        |           | Success   |
      | random,20            | 50220240726111680001 | Oto Finance Motor | FC        | dedi.ridwan  | Failed    |
      | 85204387742795111680 | random,64            | Oto Finance Motor | FC        | dedi.ridwan  | Failed    |
      | 85204387742795111680 | 50220240726111680001 | Oto finance Mobil | FC        | dedi.ridwan  | Failed    |
      | 85204387742795111680 | 50220240726111680001 | Oto Finance Motor | random,12 | dedi.ridwan  | Failed    |
      | 85204387742795111680 | 50220240726111680001 | Oto Finance Motor | FC        | random,50 | Failed    |
      | 85204387742795111680 | 50220240726111680001 | Oto Finance Motor | FC        | dedi.ridwan  | Failed    |

  @setorku_payment_code_list_details
  Scenario Outline: [SOB][SetorKu][FE] Payment Code List - Detail
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"
    * I click filter button on "SetorKu Payment Code List"
    * I fill From Date ("20,07") and To Date ("20,08") on "SetorKu Payment Code List"
    When I click apply button filter
    Then Datatable show data "SetorKu Payment Code List"
    Given I click detail Payment Code List on row "<row>"

    Examples:
      | row |
      |   1 |
      |   2 |
      |   3 |
