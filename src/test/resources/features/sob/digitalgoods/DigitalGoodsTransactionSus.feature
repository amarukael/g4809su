@SOB_DigitalGoods_Transaction_Pending
Feature: Digital Goods Transaction_Pending

  @navigate_digitalgoods_transaction_pending
  Scenario Outline: Navigate into Digital Goods Transaction Pending Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to DigitalGoods Transaction Pending
    Examples:
      | condition    | username |
      | Successfully | adminqa  |

  @new_browser_access_dgms_transaction_pending
  Scenario: New Browser Access DGMS Transaction Page
    Given I am in DigitalGoods Transaction
    When DGMS show Introduction Feature
    Then I Check Functional Introduction Tab
    Then I Check Functional Introduction Transaction Pending

  @dgms_transaction_pending_filter_date
  Scenario: DGMS Transaction Pending Filter Date
    Given I am in DigitalGoods Transaction Pending
    Then I Hit Filter Button
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction Pending
    When I click apply button filter
    Then DGMS Datatable show data Transaction Pending

  @dgms_transaction_pending_filter_data
  Scenario Outline: DGMS Transaction Pending Filter Data
    Given I am in DigitalGoods Transaction Pending
    Then I Hit Filter Button
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction Pending
    * I Click Field "<field>" and Fill "<value>" on DGMS Transaction Pending
    When I click apply button filter
    Then DGMS Datatable show data Transaction Pending
    Examples:
      | field           | value                        |
      | Transactions ID | DUMMY20240125165057343626381 |
      | Product Name    | PLN PREPAID TEKTAYA          |
      | Customer ID     | 32170864527                  |
      | Ref             | DUMMY20240125165057343626381 |

  @dgms_transaction_pending_force_failed
  Scenario: DGMS Transaction Pending Force Success
    Given I am in DigitalGoods Transaction Pending
    Then I Hit Filter Button
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction Pending
    When I click apply button filter
    Then I Hit Button Force "Success" on Row "1" DGMS Transaction Pending
    Then I Fill Receipt with "76215376921830" and Code with "8738715325871203" and Confirm
    Then DGMS Show Alert "Success"

  @dgms_transaction_pending_force_failed
  Scenario: DGMS Transaction Pending Force Failed
    Given I am in DigitalGoods Transaction Pending
    Then I Hit Filter Button
    * I fill From Date ("01,01") and To Date ("30,01") on DGMS Transaction Pending
    When I click apply button filter
    Then I Hit Button Force "failed" on Row "1" DGMS Transaction Pending
    Then I Choose Reject Reason with RC "90"
    Then DGMS Show Alert "success"