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
    * I click field "Company Description" and fill with "<desc>" on Setorku Product
    * I click field "Product Name" and fill with "<name>" on Setorku Product
    When I click apply button filter
    Then Datatable show data "SetorKu Product"

    Examples:
      | desc      | name              |
      | OTO       | Oto finance Mobil |
      | OT        | Oto finance Mobil |
      | OTO       | Oto               |
      | random,45 | Oto finance Mobil |
      | OTO       | random,100        |

  @setorku_product_add_product
  Scenario Outline: [SOB SetorKu] Add Product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
    * I click add product button on page
    * I click field "Company ID" and fill with "<compID>" on Setorku Product
    * I click field "Company Description" and fill with "<compDesc>" on Setorku Product
    * I click field "Product ID" and fill with "<prodId>" on Setorku Product
    * I click field "Product Name" and fill with "<prodName>" on Setorku Product
    * I click field "Prefix" and fill with "<prefix>" on Setorku Product
    * I click field "Secret Key" and fill with "<secret>" on Setorku Product
    When I click button submit form
    Then "SetorKu Product" Show Alert Success

    Examples:
      | compID | compDesc | prodId | prodName | prefix | secret    |
      |    666 | itsDesc  |    666 | itsName  |    666 | itsSecret |

  @setorku_product_change_status_product
  Scenario Outline: [SOB SetorKu] Change status product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"

  @setorku_product_delete_product
  Scenario Outline: [SOB SetorKu] Delete Product
    Given I am in Menu "SetorKu" and Sub-Menu "Product"
