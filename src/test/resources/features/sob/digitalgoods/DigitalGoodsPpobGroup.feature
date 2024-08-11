@SOB_DigitalGoods_PPOB_Group
Feature: Digital Goods PPOB Group

  @navigate_digitalgoods_ppob_group
  Scenario Outline: [SOB Digital Goods] Navigate into Digital Goods PPOB Group Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "Digital Goods, Product Master"
    Then I click Tab PPOB Group on DGMS Product Master
    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
#      | Failed       | rhesa    |

  @dgms_ppob_group_filter
  Scenario Outline: [SOB Digital Goods] Using Filter -- <condition>
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab PPOB Group on DGMS Product Master
    When I click filter button on "Digital Goods PPOB Group"
    Then I click field "<label>" and fill with "<value>" on DGMS Product Master PPOB Group
    When I click apply button filter
    Then Datatable show data "Digital Goods PPOB Group"
    Examples:
      | label       | value            | condition |
      | Description | TV CABLE ( BMS ) | Success   |
      | Description | TV               | Success   |
      | Description | CABLE            | Success   |
      | Description | tv               | Success   |
      | Description | cable            | Success   |
      | Description | BMS              | Success   |
      | Description | bms              | Success   |
      | PPOB ID     | 1                | Success   |
      | PPOB ID     | 12               | Success   |
      | PPOB ID     | random,10        | Failed    |
      | PPOB ID     | random,11        | Failed    |
      | Description | random,255       | Failed    |
      | Description | random,256       | Failed    |

  @dgms_ppob_group_filter_all_field
  Scenario Outline: [SOB Digital Goods] Using Filter -- <condition>
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab PPOB Group on DGMS Product Master
    When I click filter button on "Digital Goods PPOB Group"
    Then I fill PPOB ID with "<id>" on DGMS Product Master PPOB Group
    Then I fill Description with "<description>" on DGMS Product Master PPOB Group
    When I click apply button filter
    Then Datatable show data "Digital Goods PPOB Group"
    Examples:
      | id | description                        |
      | 6  | INTERNET (BMS & MYREPUBLIC) |
      | 6  | internet (bms & myrepublic) |
      | 6  | MYREPUBLIC                  |
      | 6  | myrepublic                  |
      | 6  | &                           |
      | 7  | INTERNET (BMS & MYREPUBLIC) |

  @dgms_ppob_group_add_new
  Scenario Outline: [SOB Digital Goods] Using Add New PPOB Group
    Given I am in Menu "Digital Goods" and Sub-Menu "Product Master"
    Then I click Tab PPOB Group on DGMS Product Master
    When I click add button on page
    * I fill PPOB ID with "<id>" on DGMS Product Master PPOB Group
    * I fill PPOB Description with "<name>" on DGMS Product Master PPOB Group
    When I click button submit form
    Then "Digital Goods PPOB Group" Show Alert Success
    Examples:
      | id        | name       |
      | 72        | TEST QA    |
      | 73        | tEsT Qa    |
      | number,10 | TEST QA    |
      | 74        | random,255 |
      | space     | TEST QA    |
      | 75        | space      |
      | space     | space      |
      | number,11 | TEST QA    |
      | 76        | random,256 |
      | number,11 | random,256 |
