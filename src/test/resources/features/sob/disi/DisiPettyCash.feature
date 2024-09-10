Feature: Disi Petty Cash

  @navigate_disi_petty_cash_page
  Scenario Outline: Navigate into Deposit Sistem Petty Cash Page with <typeUser> User -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to DISI Petty Cash
    Examples:
      | condition    | username             | typeUser  |
      | Successfully | pettycashpartner     | HO        |

  @disi_petty_cash_filter_date
  Scenario: Fill From Date more than 7 days in DISI Petty Cash Page -- Successfully
    Given I am in DISI Petty Cash Page
    * I hit Filter Button on DISI Petty Cash
    When I fill From Date on Filter Field on DISI Petty Cash
    Then I hit Apply Button on DISI Petty Cash