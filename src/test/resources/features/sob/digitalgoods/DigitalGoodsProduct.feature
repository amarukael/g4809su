@SOB_DigitalGoods_Product
Feature: Digital Goods Product

  @navigate_digitalgoods_product
  Scenario Outline: [SOB Digital Goods] Navigate into Digital Goods Product Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "Digital Goods, Product Master"
    Then I click Tab Product on DGMS Product Master

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @dgms_product_filter
  Scenario Outline: [SOB Digital Goods] Using Filter
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click filter button on "Digital Goods Product"
    Then I click field "<label>" and fill with "<value>" on DGMS Product Master Product
    When I click apply button filter
    Then Datatable show data "Digital Goods Product"

    Examples:
      | label         | value           |
      | Status        | Inactive        |
      | Supplier Name | JPA             |
      | Category Name | MULTIFINANCE    |
      | Product ID    | PDAMLAMPUNGKOT  |
      | Product Name  | PBB Kab. Brebes |
      # | Category & Sub | VOUCHER GAMES,Steam | Success   | Valid Product ID |
      # | Sub Category Name | test         | Success   | Valid Product ID |

  @dgms_product_filter_all_field
  Scenario Outline: [SOB Digital Goods] Using All Field Filter -- <condition>
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click filter button on "Digital Goods Product"
    * I click field "Product ID" and fill with "<productId>" on DGMS Product Master Product
    * I click field "Product Name" and fill with "<productName>" on DGMS Product Master Product
    * I click field "PPOB Name" and fill with "<ppobName>" on DGMS Product Master Product
    * I click field "Category Name" and fill with "<categoryName>" on DGMS Product Master Product
    * I click field "Sub Category Name" and fill with "<subCatName>" on DGMS Product Master Product
    * I click field "Supplier Name" and fill with "<supplierName>" on DGMS Product Master Product
    * I click field "Status" and fill with "<status>" on DGMS Product Master Product
    When I click apply button filter
    Then Datatable show data "Digital Goods Product"

    Examples:
      | productId | productName                 | ppobName | categoryName | subCatName | supplierName       | status |
      |     15058 | PDAM KAB. BALANGAN (KALSEL) | PDAM     | PDAM         |            | IDSRESTProjectDana | Active |

  @dgms_add_new_product
  Scenario Outline: [SOB Digital Goods] Add New Product ("<desc>")
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click add button on page
    Then I choose Single Add on page
    * I click field "PPOB ID - PPOB Name" and fill with "<ppobName>" on DGMS Product Master Product
    * I click field "Product Name" and fill with "<productName>" on DGMS Product Master Product
    * I click field "Keterangan" and fill with "<keterangan>" on DGMS Product Master Product
    * I click field "Category Name" and fill with "<catName>" on DGMS Product Master Product
    * I click field "Sub Category Name" and fill with "<subCatName>" on DGMS Product Master Product
    * I click field "Supplier Name" and fill with "<supplierName>" on DGMS Product Master Product
    * I click field "Status" and fill with "<status>" on DGMS Product Master Product
    # When I click button submit form
    # Then "Digital Goods Product" Show Alert Success

    Examples:
      | ppobName | productName | keterangan   | catName        | subCatName | supplierName | status   |
      | TEST QA  | Test bruh   | etaterangkan | MOBILE PREPAID | THREE      | TEST QA8     | Inactive |
