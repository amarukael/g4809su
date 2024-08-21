@SOB_Setorku_PaymentCodeList
Feature: Setorku Payment Code List

  @navigate_setorku_payment_code_list
  Scenario Outline: [SOB SetorKu] Navigate into SetorKu Payment Code List Page
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Payment Code List"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      # | Failed       | rhesa    |

  @setorku_payment_code_list_filter_by_field
  Scenario Outline: [SOB SetorKu] Get data product using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"
    * I click filter button on "SetorKu Payment Code List"
    # * I fill From Date ("20,07") and To Date ("20,08") on "SetorKu Payment Code List"
    * I click field "<label>" and fill with "<value>" on Setorku Payment Code List
    When I click apply button filter
    Then Datatable show data "SetorKu Payment Code List"

    Examples:
      | label                   | value                |
      | Merchant Transaction ID | 14838544601731207081 |
      | Payment Code            | 50120240725620888001 |
      | Product Name            | Oto finance Mobil    |
      | User Code               |            220726001 |
      | Username                | user4309             |
      | Merchant Transaction ID | random,20            |
      | Merchant Transaction ID | random,21            |
      | Payment Code            | random,64            |
      | Payment Code            | random,65            |
      | Product Name            | Mega Central Finance |
      | User Code               | random,12            |
      | User Code               | random,13            |
      | Username                | random,50            |
      | Username                | random,51            |

  @setorku_payment_code_list_filter_by_all_field
  Scenario Outline: [SOB SetorKu] Get data product using Filter by all field
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"
    * I click filter button on "SetorKu Payment Code List"
    * I fill From Date ("01,01,2019") and To Date ("30,12") on "SetorKu Payment Code List"
    * I click field "Payment Code List Name" and fill with "<name>" on Setorku Payment Code List
    * I click field "Status" and fill with "<status>" on Setorku Payment Code List
    When I click apply button filter
    Then Datatable show data "SetorKu Payment Code List"

    Examples:
      | name      | status   |
      | Test post | Active   |
      | Test post | Inactive |
      | tev       | Active   |

  @setorku_payment_code_list_add_payment_code_list
  Scenario Outline: [SOB SetorKu] Add Payment Code List
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"

  @setorku_payment_code_list_change_status_payment_code_list
  Scenario Outline: [SOB SetorKu] Change status product
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"

  @setorku_payment_code_list_delete_payment_code_list
  Scenario Outline: [SOB SetorKu] Delete Payment Code List
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"
