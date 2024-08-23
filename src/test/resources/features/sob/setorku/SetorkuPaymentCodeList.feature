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
    # * I fill From Date ("01,01,2019") and To Date ("30,12") on "SetorKu Payment Code List"
    * I click field "Merchant Transaction ID" and fill with "<id>" on Setorku Payment Code List
    * I click field "Payment Code" and fill with "<code>" on Setorku Payment Code List
    * I click field "Product Name" and fill with "<productName>" on Setorku Payment Code List
    * I click field "User Code" and fill with "<userCode>" on Setorku Payment Code List
    * I click field "Username" and fill with "<username>" on Setorku Payment Code List
    When I click apply button filter
    Then Datatable show data "SetorKu Payment Code List"

    Examples:
      | id                   | code                 | productName       | userCode  | username  |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Mobil | FC        | user4309  |
      |                      | 50120240725620888001 | Oto finance Mobil | FC        | user4309  |
      | 65927867478676620888 |                      | Oto finance Mobil | FC        | user4309  |
      | 65927867478676620888 | 50120240725620888001 |                   | FC        | user4309  |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Mobil |           | user4309  |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Mobil | FC        |           |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Mobil | FC        | user4309  |
      | random,20            | 50120240725620888001 | Oto finance Mobil | FC        | user4309  |
      | 65927867478676620888 | random,64            | Oto finance Mobil | FC        | user4309  |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Motor | FC        | user4309  |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Mobil | random,12 | user4309  |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Mobil | FC        | random,50 |
      | 65927867478676620888 | 50120240725620888001 | Oto finance Mobil | FC        | user4309  |

  @setorku_payment_code_list_change_status_payment_code_list
  Scenario Outline: [SOB SetorKu] Change status product
    Given I am in Menu "SetorKu" and Sub-Menu "Payment Code List"
    * I click filter button on "SetorKu Payment Code List"
    * I fill From Date ("20,07") and To Date ("20,08") on "SetorKu Payment Code List"
    When I click apply button filter
    Then Datatable show data "SetorKu Payment Code List"
    Given I click detail Payment Code List on row "1"
