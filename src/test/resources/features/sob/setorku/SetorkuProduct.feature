@SOB_Setorku_Product
Feature: Setorku Product

  @navigate_setorku_product
  Scenario Outline: [SOB SetorKu] Navigate into SetorKu Product Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Product"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      # | Failed       | rhesa    |

  @setorku_product_filter_by_field
  Scenario Outline: [SOB SetorKu] Get data product using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    * I click filter button on "SetorKu Product"
    * I click field "<label>" and fill with "<value>" on Setorku Product
    When I click apply button filter
    Then Datatable show data "SetorKu Product"

    Examples:
      | label               | value                           |
      | Company Description | MCF                             |
      | Company Description | M                               |
      | Company Description | F                               |
      | Company Description | randomCase,MCF                  |
      | Product Name        | Mega Central Finance            |
      | Product Name        | randomCase,Mega Central Finance |
      | Product Name        | Mega                            |
      | Product Name        | Finance                         |
      | Company Description | random,45                       |
      | Company Description | random,46                       |
      | Product Name        | random,100                      |
      | Product Name        | random,101                      |

  @setorku_product_filter_by_all_field
  Scenario Outline: [SOB SetorKu] Get data product using Filter by all field
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    * I click filter button on "SetorKu Product"
    * I fill From Date ("01,01,2019") and To Date ("30,12") on "SetorKu Product"
    * I click field "Product Name" and fill with "<name>" on Setorku Product
    * I click field "Status" and fill with "<status>" on Setorku Product
    When I click apply button filter
    Then Datatable show data "SetorKu Product"

    Examples:
      | name      | status   |
      | Test post | Active   |
      | Test post | Inactive |
      | tev       | Active   |

  @setorku_product_add_product
  Scenario Outline: [SOB SetorKu] Add Product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"

  @setorku_product_change_status_product
  Scenario Outline: [SOB SetorKu] Change status product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"

  @setorku_product_delete_product
  Scenario Outline: [SOB SetorKu] Delete Product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
