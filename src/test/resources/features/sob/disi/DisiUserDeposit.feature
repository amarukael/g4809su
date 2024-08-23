Feature: Disi User Deposit

  @navigate_disi_user_deposit_page
  Scenario Outline: Navigate into Deposit Sistem User Deposit Page with <typeUser> User -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to DISI User Deposit
    Examples:
      | condition    | username             | typeUser  |
      | Successfully | pettycashpartner     | HO        |

  @disi_user_deposit_filter_date
  Scenario: Fill From Date more than 7 days in DISI User Deposit Page -- Successfully
    Given I am in DISI User Deposit Page
    * I hit Filter Button on DISI User Deposit
    * I choose "Merchant 07" in Merchant Name Field
    * I hit Filter Button on DISI User Deposit Detail
    When I fill From Date on Filter Field on DISI User Deposit Detail
    Then I hit Apply Button on DISI User Deposit Detail