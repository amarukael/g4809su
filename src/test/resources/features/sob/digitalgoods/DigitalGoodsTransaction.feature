@SOB_DigitalGoods_Transaction
Feature: Digital Goods Transaction

  @navigate_digitalgoods_transaction
  Scenario Outline: Navigate into Digital Goods Transaction Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to DigitalGoods Transaction
    Examples:
      | condition    | username |
      | Successfully | adminqa  |

  @new_browser_access_dgms_transaction
  Scenario: New Browser Access DGMS Transaction Page
    Given I am in DigitalGoods Transaction
    When DGMS show Introduction Feature
    Then I Check Functional Introduction Tab
    Then I Check Functional Introduction Transaction

  @dgms_transaction_filter_date
  Scenario: DGMS Filter Date and Partner
    Given I am in DigitalGoods Transaction
    When DGMS not show Introduction Feature
    Then I hit Filter Button on DGMS Transaction
    * I fill From Date ("10,07") and To Date ("10,08") on DGMS Transaction
    When I click apply button filter
    Then DGMS Datatable show data Transaction

  @dgms_transaction_filter_data
  Scenario Outline: DGMS Filter Data
    Given I am in DigitalGoods Transaction
    When DGMS not show Introduction Feature
    Then I hit Filter Button on DGMS Transaction
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction
    * I Click Field "<field>" and Choose "<value>" on DGMS Transaction
    When I click apply button filter
    Then DGMS Datatable show data Transaction
    Examples:
      | field           | value                |
      | Transactions ID | SLS16001505254       |
      | Product Code    | EMVIRGO              |
      | Customer ID     | 083812854541         |
      | Serial Number   | 99970111447889       |
      | Ref             | SLS20240115224614352 |
      | Status          | SUCCESS              |
      | Partner         | Dana                 |

  @dgms_transaction_show_detail_transaction_success
  Scenario: DGMS Show Detail Transaction
    Given I am in DigitalGoods Transaction
    When DGMS not show Introduction Feature
    Then I hit Filter Button on DGMS Transaction
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction
    * I Choose "Dana" as Partner on DGMS Transaction
    When I click apply button filter
    Then DGMS Datatable show data Transaction
    Then I Hit Detail Transaction on Row "8"

  @dgms_transaction_see_more_top_product
  Scenario: DGMS Show Top Product
    Given I am in DigitalGoods Transaction
    When DGMS not show Introduction Feature
    Then I hit Filter Button on DGMS Transaction
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction
    * I Choose "Dana" as Partner on DGMS Transaction
    When I click apply button filter
    Then I Hit see more on Top Product

  @dgms_transaction_force_success_status_suspect
  Scenario: DGMS Transaction Force Success when Ststus Suspect
    Given I am in DigitalGoods Transaction
    When DGMS not show Introduction Feature
    Then I hit Filter Button on DGMS Transaction
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction
    * I Choose "Dana" as Partner on DGMS Transaction
    When I click apply button filter
    Then I Hit Button Force "Success" on Row "5" DGMS Transaction
    Then I Fill Receipt with "76215376921830" and Code with "8738715325871203" and Confirm
    Then DGMS Show Alert "Success"

  @dgms_transaction_force_failed_status_suspect
  Scenario: DGMS Transaction Force Failed when Ststus Suspect
    Given I am in DigitalGoods Transaction
    When DGMS not show Introduction Feature
    Then I hit Filter Button on DGMS Transaction
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction
    * I Choose "Dana" as Partner on DGMS Transaction
    When I click apply button filter
    Then I Hit Button Force "failed" on Row "1" DGMS Transaction
    Then I Hit All Confirmation on DGMS Transaction
    Then DGMS Show Alert "success"