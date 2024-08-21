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
      # | Failed       | rhesa    |

  @dgms_product_filter
  Scenario Outline: [SOB][DGMS][FE] Product Master - Product Using Filter by Field
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click filter button on "Digital Goods Product"
    Then I click field "<label>" and fill with "<value>" on DGMS Product Master Product
    When I click apply button filter
    Then Datatable show data "Digital Goods Product"

    Examples:
      | label             | value                                      |
      | PPOB Name         | VOUCHER TOPUP IN GAME( UNIPIN )            |
      | Product Name      | PBB Kab. Brebes                            |
      | PPOB Name         | randomcase,VOUCHER TOPUP IN GAME( UNIPIN ) |
      | Product Name      | randomcase,PBB Kab. Brebes                 |
      | Status            | Inactive                                   |
      | Supplier Name     | JPA                                        |
      | Category Name     | MULTIFINANCE                               |
      | Product ID        | PDAMLAMPUNGKOT                             |
      | Product ID        | randomcase,PDAMLAMPUNGKOT                  |
      | Category & Sub    | VOUCHER GAMES,Steam                        |
      | Sub Category Name | Steam                                      |
      | PPOB Name         | random,100                                 |
      | Product ID        | random,100                                 |
      | Product Name      | random,100                                 |
      | PPOB Name         | random,200                                 |
      | Product ID        | random,101                                 |
      | Product Name      | random,101                                 |

  @dgms_product_filter_all_field
  Scenario Outline: [SOB][DGMS][FE] Product Master - Product Using Filter by All Field
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
      | productId  | productName      | ppobName    | categoryName   | subCatName | supplierName  | status   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Active   |
      |            | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       |                  | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 |             | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) |                | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID |            | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  |               | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | ABM (Regular) |          |
      | random,100 | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       | random,100       | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 | random,200  | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | random,10      | SMARTFREN  | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | random,10  | ABM (Regular) | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | JPA           | Active   |
      | FH60       | SMARTFREN 60.000 | PULSA (ABM) | MOBILE PREPAID | SMARTFREN  | ABM (Regular) | Inactive |

  @dgms_add_new_single_product
  Scenario Outline: [SOB][DGMS][FE] Product Master - Product Add New Single Product
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click add button on page
    Then I choose Single Add on page
    * I click field "PPOB ID - PPOB Name" and fill with "<ppobName>" on DGMS Product Master Product
    * I click field "Product ID" and fill with "<productName>" on DGMS Product Master Product
    * I click field "Description" and fill with "<keterangan>" on DGMS Product Master Product
    * I click field "Category Name" and fill with "<catName>" on DGMS Product Master Product
    * I click field "Sub Category Name" and fill with "<subCatName>" on DGMS Product Master Product
    * I click field "Supplier Name" and fill with "<supplierName>" on DGMS Product Master Product
    * I click field "Status" and fill with "<status>" on DGMS Product Master Product
    When I click button submit form
    Then "Digital Goods Product" Show Alert Success

    Examples:
      | ppobName | productName        | keterangan | catName       | subCatName  | supplierName | status   |
      | TEST QA2 | Test Single Bruh1  | OnlyTest   | TestQA2       | TEST SUB QA | TEST QA3     | Active   |
      | TEST QA2 | Test Single Bruh1  | OnlyTest   | TestQA2       | TEST SUB QA | TEST QA3     | Active   |
      | TEST QA2 | Test Single Bruh1  | OnlyTest   | VOUCHER GAMES | Mango Live  | TEST QA3     | Active   |
      | TEST QA2 | Test Single Bruh1  | OnlyTest   | TestQA2       | TEST SUB QA | TEST QA1     | Active   |
      | TEST QA3 | Test Single Bruh1  | OnlyTest   | TestQA2       | TEST SUB QA | TEST QA3     | Active   |
      | TEST QA2 | Test Single Bruh1  | OnlyTest   | TestQA2       | TEST SUB QA | TEST QA1     | Inactive |
      | TEST QA2 | random,100         | OnlyTest2  | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh3  | random,100 | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh4  | OnlyTest4  | TestQA2       |             | TEST QA3     | Inactive |
      | TEST QA2 | random,101         | OnlyTest5  | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh6  | random,101 | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh7  | OnlyTest7  | random,100    | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh8  | OnlyTest8  | TestQA2       | random,100  | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh9  | OnlyTest9  | TestQA2       | TEST SUB QA |              | Inactive |
      | TEST QA2 |                    | OnlyTest10 | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh10 |            | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh11 | OnlyTest11 |               | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh12 | OnlyTest12 | TestQA2       | TEST SUB QA | TEST QA3     |          |
      | TEST QA2 | space,5            | OnlyTest13 | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |
      | TEST QA2 | Test Single Bruh13 | space,5    | TestQA2       | TEST SUB QA | TEST QA3     | Inactive |

  @dgms_switch_status_product
  Scenario Outline: [SOB][DGMS][FE] Product Master - Switch Product Status
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    Then I Hit Switch Button Product Row "<row>" to "<switch>"
    Given I Hit "<btn>" Button in Status Confirmation and Show Alert

    Examples:
      | row | switch   | btn |
      |   1 | active   | yes |
      |   2 | inactive | yes |
