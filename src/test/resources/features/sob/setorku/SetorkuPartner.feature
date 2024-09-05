@SOB_Setorku_Partner
Feature: Setorku Partner

  @navigate_setorku_product
  Scenario Outline: [SOB][SetorKu][FE] Partner - Navigate into SetorKu Partner Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Partner"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @setorku_product_filter_by_date
  Scenario: [stg][SOB][SetorKu][FE] Partner - Get data product using Filter by Date
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I fill From Date ("01,01,2020") and To Date ("27,08") on "SetorKu Partner"
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

  @setorku_product_filter_by_field
  Scenario Outline: [stg][SOB][SetorKu][FE] Partner - Get data product using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I click field "<label>" and fill with "<value>" on Setorku Partner
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

    Examples:
      | label        | value                  |
      | Partner Name | PartnerTemp            |
      | Partner Name | Partner                |
      | Partner Name | randomCase,PartnerTemp |
      | Partner Name | random,255             |
      | Status       | Active                 |
      | Status       | Inactive               |

  @setorku_product_filter_by_all_field
  Scenario Outline: [SOB][SetorKu][FE] Partner - Get data product using Filter by all field
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I fill From Date ("01,01,2019") and To Date ("30,12") on "SetorKu Partner"
    * I click field "Partner Name" and fill with "<name>" on Setorku Partner
    * I click field "Status" and fill with "<status>" on Setorku Partner
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

    Examples:
      | name        | status   |
      | PartnerTemp | Active   |
      | PartnerTemp | Inactive |
      | Part        | Active   |

  @setorku_product_add_partner
  Scenario Outline: [dum][SOB][SetorKu][FE] Partner - Add Partner
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    When I click add partner button on page
    * I click field "Partner ID" and fill with "<id>" on Setorku Partner
    * I click field "Partner Name" and fill with "<name>" on Setorku Partner
    * I click field "Status" and fill with "<status>" on Setorku Partner
    When I click button submit form
    Then "SetorKu partner" Show Alert Success

    Examples:
      | id        | name       | status   |
      | ATQ       | TEST QA    | Active   |
      | ATQ       | TEST QA    | Active   |
      | ATQ       | TEST QA1   | Active   |
      | ATQ1      | TEST QA    | Active   |
      | random,20 | TEST QA2   | Inactive |
      | ATQ2      | random,100 | Inactive |
      | random,21 | random,101 | Inactive |
      | ATQ3      | space,20   | Inactive |
      | space,6   | space,21   |          |
      # | space,5   | TEST QA3   | Inactive |

  @setorku_product_change_status_partner
  Scenario Outline: [stg][SOB][SetorKu][FE] Partner - Change status partner
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I click field "Partner Name" and fill with "<name>" on Setorku Partner
    When I click apply button filter
    Then I Hit Switch Button Product Row "<row>" to "<switch>"
    Then I Hit "<btn>" Button in Status Confirmation
    Given SetorKu partner "<snackbar>" snackbar success

    Examples:
      | name | row | switch   | btn |
      | TEST |   1 | active   | yes |
      | TEST |   2 | inactive | yes |
      | TEST |   1 | active   | yes |

  @setorku_product_delete_partner
  Scenario Outline: [stg][SOB][SetorKu][FE] Partner - Delete Partner
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I click field "Partner Name" and fill with "<name>" on Setorku Partner
    When I click apply button filter
    When I hit icon trash can on row "<row>"
    Then I Hit "<btn>" Button in Status Confirmation
    Given SetorKu partner "<snackbar>" snackbar success

    Examples:
      | name | row | btn    | snackbar |
      | TEST |   2 | delete | Show     |
      | TEST |   2 | cancel | Not Show |
      | TEST |   1 | delete | Show     |
