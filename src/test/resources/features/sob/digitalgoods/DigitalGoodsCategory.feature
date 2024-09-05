@SOB_DigitalGoods_Category
Feature: Digital Goods Category

  @navigate_digitalgoods_category
  Scenario Outline: [SOB Digital Goods] Navigate into Digital Goods Category Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "Digital Goods, Product Master"
    Then I click Tab Category on DGMS Product Master

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |

  @dgms_category_filter
  Scenario Outline: [SOB Digital Goods][Product Master][Category] Using Filter -- "<condition>"
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Category on DGMS Product Master
    When I click filter button on "Digital Goods Category"
    Then I click field "<label>" and fill with "<value>" on DGMS Product Master Category
    When I click apply button filter
    Then Datatable show data "Digital Goods Category"

    Examples:
      | label           | value                  | condition |
      | Category ID     |                     31 | Success   |
      | Category Name   | Voucher Digital Metrox | Success   |
      | Sub Category ID |                    294 | Success   |
      | Status          | Inactive               | Success   |
      | Status          | Active                 | Success   |
      | Category ID     | random,10              | Failed    |
      | Category ID     | number,10              | Failed    |
      | Category ID     | number,11              | Failed    |
      | Category Name   | random,50              | Failed    |
      | Category Name   | random,51              | Failed    |
      | Sub Category ID | random,10              | Failed    |
      | Sub Category ID | number,10              | Failed    |
      | Sub Category ID | number,11              | Failed    |

  @dgms_category_filter_all_field
  Scenario Outline: [SOB Digital Goods] Using Filter All -- "<condition>"
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Category on DGMS Product Master
    When I click filter button on "Digital Goods Category"
    Then I click field "Category ID" and fill with "<categoryId>" on DGMS Product Master Category
    Then I click field "Category Name" and fill with "<categoryName>" on DGMS Product Master Category
    Then I click field "Sub Category ID" and fill with "<subCategoryID>" on DGMS Product Master Category
    Then I click field "Status" and fill with "<status>" on DGMS Product Master Category
    When I click apply button filter
    Then Datatable show data "Digital Goods Category"

    Examples:
      | categoryId | categoryName                      | subCategoryID | status   | condition |
      |         79 | Voucher Digital Socall Ribs & BBQ |             6 | Active   | Success   |
      |            | Voucher Digital Socall Ribs & BBQ |             6 | Active   | Success   |
      |         79 |                                   |             6 | Active   | Success   |
      |         79 | Voucher Digital Socall Ribs & BBQ |               | Active   | Success   |
      |         79 | Voucher Digital Socall Ribs & BBQ |             6 |          | Success   |
      | random,10  | Voucher Digital Socall Ribs & BBQ |             6 | Active   | Failed    |
      | number,10  | Voucher Digital Socall Ribs & BBQ |             6 | Active   | Failed    |
      | number,11  | Voucher Digital Socall Ribs & BBQ |             6 | Active   | Failed    |
      |         79 | random,50                         |             6 | Active   | Failed    |
      |         79 | random,51                         |             6 | Active   | Failed    |
      |         79 | Voucher Digital Socall Ribs & BBQ | random,10     | Active   | Failed    |
      |         79 | Voucher Digital Socall Ribs & BBQ | number,10     | Active   | Failed    |
      |         79 | Voucher Digital Socall Ribs & BBQ | number,11     | Active   | Failed    |
      |         79 | Voucher Digital Socall Ribs & BBQ |             6 | Inactive | Failed    |

  @dgms_category_add_new
  Scenario Outline: [SOB Digital Goods] Using Add New Category
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Category on DGMS Product Master
    When I click add button on page
    * I click field "Category Name" and fill with "<categoryName>" on DGMS Product Master Category
    * I click field "Sub Category ID" and fill with "<subCategoryID>" on DGMS Product Master Category
    * I click field "PPOB Description" and fill with "<ppobDesc>" on DGMS Product Master Category
    * I click field "Product Description" and fill with "<productDesc>" on DGMS Product Master Category
    * I click field "Status" and fill with "<status>" on DGMS Product Master Category
    When I click button submit form
    Then "Digital Goods Category" Show Alert Success

    Examples:
      | categoryName | subCategoryID | ppobDesc   | productDesc    | status   |
      | TestQA       |           189 | onlyTest   | test only bruh | Active   |
      |              |           189 | onlyTest   | test only bruh | Inactive |
      | TestQA2      |               | onlyTest   | test only bruh | Inactive |
      | TestQA3      |           189 |            | test only bruh | Inactive |
      | TestQA4      |           189 | onlyTest   |                | Inactive |
      | TestQA5      |           189 | onlyTest   | test only bruh | null     |
      | space,5      |           189 | onlyTest   | test only bruh | Inactive |
      | TestQA6      |           189 | space,5    | test only bruh | Inactive |
      | TestQA7      |           189 | onlyTest   | space,5        | Inactive |
      | random,50    |           189 | onlyTest   | test only bruh | Inactive |
      | TestQA8      | random,10     | onlyTest   | test only bruh | Inactive |
      | TestQA9      |           189 | random,255 | test only bruh | Inactive |
      | TestQA10     |           189 | onlyTest   | random,255     | Inactive |
      | random,51    |           189 | onlyTest   | test only bruh | Inactive |
      | TestQA11     | number,11     | onlyTest   | test only bruh | Inactive |
      | TestQA12     |           189 | random,256 | test only bruh | Inactive |
      | TestQA13     |           189 | onlyTest   | random,256     | Inactive |
      | random,50    |           189 | random,255 | random,255     | Inactive |
      | space,5      | space,5       | space,5    | space,5        | Inactive |
      |              |               |            |                | null     |

  @dgms_switch_status_category
  Scenario Outline: [SOB][DGMS][FE] Category - Switch Category Status
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Category on DGMS Product Master
    When I click filter button on "Digital Goods Category"
    Then I click field "Category Name" and fill with "test" on DGMS Product Master Category
    When I click apply button filter
    Then I Hit Switch Button Product Row "<row>" to "<switch>"
    Given I Hit "<btn>" Button in Status Confirmation
    Then I wait snackbar show and button switched

    Examples:
      | row | switch   | btn |
      |   1 | active   | yes |
      |   2 | inactive | yes |
