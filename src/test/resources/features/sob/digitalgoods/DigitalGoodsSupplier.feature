@SOB_DigitalGoods_Supplier
Feature: Digital Goods Supplier

  @navigate_digitalgoods_supplier
  Scenario Outline: [SOB Digital Goods] Navigate into Digital Goods Supplier Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "Digital Goods, Product Master"
    Then I click Tab Supplier on DGMS Product Master
    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @dgms_supplier_filter
  Scenario Outline: [SOB Digital Goods] Using Filter (<desc>) -- <condition>
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Supplier on DGMS Product Master
    When I click filter button on "Digital Goods Supplier"
    Then I click field "<label>" and fill with "<value>" on DGMS Product Master Supplier
    When I click apply button filter
    Then Datatable show data "Digital Goods Supplier"
    Examples:
      | label         | value        | condition | desc                                   |
      | Supplier ID   | 36           | Success   | Valid Supplier ID                      |
      | Supplier Name | ULTRAVOUCHER | Success   | Valid Supplier Name                    |
      | Supplier Name | uLtRavOuCHER | Success   | Valid with random case Supplier Name   |
      | Supplier Name | ultra        | Failed    | Not match Supplier Name                |
      | Supplier ID   | number,5     | Failed    | Random Supplier ID with number   |
      | Supplier ID   | random,5     | Failed    | Random Supplier ID with alphanumeric   |
      | Supplier Name | random,5     | Failed    | Random Supplier Name with alphanumeric |
      | Supplier ID   | random,11    | Failed    | Random ID with alphanumeric 11 digit   |
      | Supplier Name | random,46    | Failed    | Random ID with alphanumeric 46 digit   |

  @dgms_supplier_filter_all_field
  Scenario Outline: [SOB Digital Goods] Using All Field Filter -- <condition>
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Supplier on DGMS Product Master
    When I click filter button on "Digital Goods Supplier"
    * I fill Supplier ID with "<id>" on DGMS Product Master Supplier
    * I fill Supplier Name with "<name>" on DGMS Product Master Supplier
    When I click apply button filter
    Then Datatable show data "Digital Goods Supplier"
    Examples:
      | id | name         | condition |
      | 11 | MOBILE PULSA | Success   |
      | 11 | MObilE PulSa | Success   |
      | 10 | MOBILE PULSA | Failed    |
      | 10 | MOBILE       | Failed    |

  @dgms_add_new_supplier
  Scenario Outline: [SOB Digital Goods] Add New Supplier ("<desc>")
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Supplier on DGMS Product Master
    When I click add button on page
    * I fill Supplier Name with "<supplier>" on DGMS Product Master Supplier
    * I fill Product Type with "<type>" on DGMS Product Master Supplier
    * I fill Masking Name with "<masking>" on DGMS Product Master Supplier
    When I click button submit form
    Then "Digital Goods Supplier" Show Alert Success
    Examples:
      | supplier  | type        | masking   | desc                         |
      | TEST QA1  | MULTIBILLER | IDS-QA-1  | Success                      |
      | random,45 | random,20   | random,20 | Success - Random value All   |
      | random,45 | MULTIBILLER | IDS-QA-1  | Success - Random supplier    |
      | TEST QA2  | random,20   | IDS-QA-1  | Success - Random type        |
      | TEST QA3  | MULTIBILLER | random,20 | Success - Random Masking     |
      | space     | space       | space     | Success - " " value all      |
      | TEST QA8  | space       | IDS-QA-1  | Success - " " type           |
      | TEST QA9  | MULTIBILLER | space     | Success - " " masking        |
      | null      | MULTIBILLER | IDS-QA-1  | Failed - null value suplier  |
      | TEST QA4  | null        | IDS-QA-1  | Failed - null value type     |
      | TEST QA5  | MULTIBILLER | null      | Failed - null value masking  |
      | null      | null        | null      | Failed - null value all      |
      | random,46 | MULTIBILLER | IDS-QA-1  | Failed - Over value supplier |
      | TEST QA6  | random,21   | IDS-QA-1  | Failed - Over value type     |
      | TEST QA7  | MULTIBILLER | random,21 | Failed - Over value masking  |
      | random,46 | random,21   | random,21 | Failed - Over value all      |
      | TEST QA1  | random,20   | random,20 | Failed - Duplicate Supplier  |






