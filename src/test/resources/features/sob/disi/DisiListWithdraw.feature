Feature: Disi List Withdraw

  @navigate_disi_list_withdraw_page
  Scenario Outline: Navigate into DISI List Withdraw Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to DISI List Withdraw
    Examples:
      | condition    | username            |
#      | Successfully | admin               |
      | Successfully | adminstg            |
      | Failed       | ryormd              |

  @disi_list_withdraw_filter_data
  Scenario: Filter data from DISI List Withdraw -- Successfully
    Given I am in DISI List Withdraw
    * I hit Filter Button on DISI List Withdraw
    When I fill all Fields ("03,04", "", "DIGITAL_BALANCE", "HO") on Filter Field on DISI List Withdraw
    Then I hit Apply Button on DISI List Withdraw

  @disi_list_withdraw_export_list_withdraw
  Scenario: Filter data from DISI List Withdraw -- Successfully
    Given I am in DISI List Withdraw
    * I hit Filter Button on DISI List Withdraw
    When I fill From Date ("03,04") on Filter Field on DISI List Withdraw
    Then I hit Apply Button on DISI List Withdraw
    Then I hit Export Button on DISI List Withdraw

  @disi_list_withdraw_advice
  Scenario Outline: Filter data from DISI List Withdraw -- Successfully
    Given I am in DISI List Withdraw
    * I hit Filter Button on DISI List Withdraw
    * I fill From Date ("<fromDate>") on Filter Field on DISI List Withdraw
    * I fill Tracking Reff ("<trackingReff>") on Filter Field on Disi List Withdraw
    * I hit Apply Button on DISI List Withdraw
    When I hit Advice Button on DISI List Withdraw case "<condition>"
    Then I "<condition>" Advice Suspect Withdraw Transaction
    Examples:
      | condition   | fromDate  | trackingReff          |
      | Success     | 03,04     | DGS2024040310370001   |
      | Pending     | 03,04     | DGS2024040310360001   |
      | Failed      | 03,04     | DGS2024040315350001   |
