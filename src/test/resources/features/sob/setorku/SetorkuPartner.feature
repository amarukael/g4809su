@SOB_Setorku_Partner
Feature: Setorku Partner

  @navigate_setorku_artner
  Scenario Outline: [SOB SetorKu] Navigate into SetorKu Partner Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "SetorKu, Partner"

    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @setorku_artner_filter_by_date
  Scenario: [SOB SetorKu] Get data artner using Filter by Date
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I fill From Date ("01,01,2024") and To Date ("20,08") on "SetorKu Partner"
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

  @setorku_artner_filter_by_field
  Scenario Outline: [SOB SetorKu] Get data artner using Filter by field
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I fill From Date ("01,01") and To Date ("30,12") on "SetorKu Partner"
    * I click field "<label>" and fill with "<value>" on Setorku Partner
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

    Examples:
      | label        | value |
      | Partner Name | THR   |
      | Partner Name | Test  |
      | Partner Name | THR   |

  @merchant_oy_artner_filter_by_all_field
  Scenario Outline: [SOB SetorKu] Get data artner using Filter by all field --"<condition>"
    Given I am in Menu "SetorKu" and Sub-Menu "Partner"
    * I click filter button on "SetorKu Partner"
    * I fill From Date ("01,01") and To Date ("31,01") on "SetorKu Partner"
    * I click field "Tracking Reff" and fill with "<tracking>" on Setorku Partner
    * I click field "Product ID" and fill with "<product>" on Setorku Partner
    * I click field "Payment Code" and fill with "<paycode>" on Setorku Partner
    * I click field "Receipt No" and fill with "<receipt>" on Setorku Partner
    * I click field "Status" and fill with "<status>" on Setorku Partner
    When I click apply button filter
    Then Datatable show data "SetorKu Partner"

    Examples:
