@SOB_Setorku_Partner
Feature: Setorku Partner

  @navigate_setorku_product
  Scenario Outline: [SOB SetorKu] Navigate into SetorKu Partner Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Partner"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @setorku_product_filter_by_date
  Scenario: [SOB SetorKu] Get data product using Filter by Date
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I fill From Date ("01,01,2024") and To Date ("20,08") on "SetorKu Partner"
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

  @setorku_product_filter_by_field
  Scenario Outline: [SOB SetorKu] Get data product using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I click field "<label>" and fill with "<value>" on Setorku Partner
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

    Examples:
      | label        | value               |
      | Partner Name | PATNER              |
      | Partner Name | Test                |
      | Partner Name | randomCase,Test UAT |
      | Partner Name | random,255          |
      | Status       | Active              |
      | Status       | Inactive            |

  @setorku_product_filter_by_all_field
  Scenario Outline: [SOB SetorKu] Get data product using Filter by all field
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I fill From Date ("01,01,2019") and To Date ("30,12") on "SetorKu Partner"
    * I click field "Partner Name" and fill with "<name>" on Setorku Partner
    * I click field "Status" and fill with "<status>" on Setorku Partner
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

    Examples:
      | name      | status   |
      | Test post | Active   |
      | Test post | Inactive |
      | tev       | Active   |

  @setorku_product_add_partner
  Scenario Outline: [SOB SetorKu] Add Partner
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"

  @setorku_product_change_status_partner
  Scenario Outline: [SOB SetorKu] Change status partner
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"

  @setorku_product_delete_partner
  Scenario Outline: [SOB SetorKu] Delete Partner
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
