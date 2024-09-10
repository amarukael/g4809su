@SOB_Dana_Reconciliation_List
Feature: Merchant OY Reconciliation

  @navigate_merchant_oy_reconciliation
  Scenario Outline: [SOB Merchant OY] Navigate into Merchant OY Reconciliation Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to "Merchant OY, Reconciliation"
    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @merchant_oy_reconciliation_filter_by_date
  Scenario: [SOB Merchant OY] Get data reconciliation using Filter by Date -- Successfully
    Given I am in Menu "Merchant OY" and Sub-Menu "Reconciliation"
    * I click filter button on "Merchant OY Reconciliation"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Reconciliation"
    * I choose "OY" partnerID on filter field
    When I click apply button filter
    Then Datatable show data "Merchant OY Reconciliation"

  @merchant_oy_reconciliation_filter_by_field
  Scenario Outline: [SOB Merchant OY] Get data reconciliation using Filter by field "<label>" --"<condition>"
    Given I am in Menu "Merchant OY" and Sub-Menu "Reconciliation"
    * I click filter button on "Merchant OY Reconciliation"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Reconciliation"
    * I choose "OY" partnerID on filter field
    * I click Field "<label>" and Fill With "<value>" on Merchant OY Reconciliation
    When I click apply button filter
    Then Datatable show data "Merchant OY Reconciliation"
    Examples:
      | label               | value                | condition |
      | Ref                 | 20240716202419033755 | Success   |
      | Transactions ID     | OYXESNBCV3SY2MUNPQW4 | Success   |
      | Product ID          | 37                   | Success   |
      | Product Description | PROXINET             | Success   |
      | Payment Code List   | 3031039983614962     | Success   |
      | Receipt No          | Alfamart20968509     | Success   |
      | Through             | ALFA                 | Success   |
      | Ref                 | random,20            | Failed    |
      | Transactions ID     | random,20            | Failed    |
      | Product ID          | random,2             | Failed    |
      | Product Description | random,8             | Failed    |
      | Payment Code List   | random,16            | Failed    |
      | Receipt No          | random,16            | Failed    |

  @merchant_oy_reconciliation_filter_by_all_field
  Scenario: [SOB Merchant OY] Get data reconciliation using Filter by all field
    Given I am in Menu "Merchant OY" and Sub-Menu "Reconciliation"
    * I click filter button on "Merchant OY Reconciliation"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Reconciliation"
    * I choose "OY" partnerID on filter field
    * I fill all fields on filter Merchant OY Reconciliation
    When I click apply button filter
    Then Datatable show data "Merchant OY Reconciliation"

  @merchant_oy_reconciliation_filter_by_all_field_failed
  Scenario Outline: [SOB Merchant OY] Get data reconciliation using Filter by all field --failed
    Given I am in Menu "Merchant OY" and Sub-Menu "Reconciliation"
    * I click filter button on "Merchant OY Reconciliation"
    * I fill From Date ("11,07") and To Date ("17,07") on "Merchant OY Reconciliation"
    * I choose "OY" partnerID on filter field
    * I fill all fields on filter Merchant OY Reconciliation with random value field "<label>"
    When I click apply button filter
    Then Datatable show data "Merchant OY Reconciliation"
    Examples:
      | label               |
      | Ref                 |
      | Transactions ID     |
      | Product ID          |
      | Product Description |
      | Payment Code List   |
      | Receipt No          |


