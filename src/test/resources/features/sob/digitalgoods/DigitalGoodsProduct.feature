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
  Scenario Outline: [SOB Digital Goods] Using Filter (<desc>) -- <condition>
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click filter button on "Digital Goods Product"
    Then I click field "<label>" and fill with "<value>" on DGMS Product Master Product
    When I click apply button filter
    Then Datatable show data "Digital Goods Product"

    Examples:
      | label         | value        | condition | desc             |
      | Category Name | MULTIFINANCE | Success   | Valid Product ID |
      | Category Name | MULTIFINANCE | Success   | Valid Product ID |

  @dgms_product_filter_all_field
  Scenario Outline: [SOB Digital Goods] Using All Field Filter -- <condition>
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click filter button on "Digital Goods Product"
    * I fill Product ID with "<id>" on DGMS Product Master Product
    * I fill Product Name with "<name>" on DGMS Product Master Product
    When I click apply button filter
    Then Datatable show data "Digital Goods Product"

    Examples:
      | id | name         | condition |
      | 11 | MOBILE PULSA | Success   |
      | 11 | MObilE PulSa | Success   |
      | 10 | MOBILE PULSA | Failed    |
      | 10 | MOBILE       | Failed    |

  @dgms_add_new_product
  Scenario Outline: [SOB Digital Goods] Add New Product ("<desc>")
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab Product on DGMS Product Master
    When I click add button on page
    * I fill Product Name with "<product>" on DGMS Product Master Product
    * I fill Product Type with "<type>" on DGMS Product Master Product
    * I fill Masking Name with "<masking>" on DGMS Product Master Product
    When I click button submit form
    Then "Digital Goods Product" Show Alert Success

    Examples:
      | product   | type        | masking   | desc                        |
      | TEST QA1  | MULTIBILLER | IDS-QA-1  | Success                     |
      | random,45 | random,20   | random,20 | Success - Random value All  |
      | random,45 | MULTIBILLER | IDS-QA-1  | Success - Random product    |
      | TEST QA2  | random,20   | IDS-QA-1  | Success - Random type       |
      | TEST QA3  | MULTIBILLER | random,20 | Success - Random Masking    |
      | space     | space       | space     | Success - " " value all     |
      | TEST QA8  | space       | IDS-QA-1  | Success - " " type          |
      | TEST QA9  | MULTIBILLER | space     | Success - " " masking       |
      | null      | MULTIBILLER | IDS-QA-1  | Failed - null value suplier |
      | TEST QA4  | null        | IDS-QA-1  | Failed - null value type    |
      | TEST QA5  | MULTIBILLER | null      | Failed - null value masking |
      | null      | null        | null      | Failed - null value all     |
      | random,46 | MULTIBILLER | IDS-QA-1  | Failed - Over value product |
      | TEST QA6  | random,21   | IDS-QA-1  | Failed - Over value type    |
      | TEST QA7  | MULTIBILLER | random,21 | Failed - Over value masking |
      | random,46 | random,21   | random,21 | Failed - Over value all     |
      | TEST QA1  | random,20   | random,20 | Failed - Duplicate Product  |
