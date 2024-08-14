Feature: Disi Partner Deposit

#  @navigate_disi_partner_deposit_page
#  Scenario Outline: Navigate into Deposit Sistem Partner Deposit Page with <typeUser> User -- <condition>
#    Given I open SOB Website
#    When I logged in with username "<username>"
#    Then I "<condition>" navigate to DISI Partner Deposit
#    Examples:
#      | condition    | username             | typeUser  |
#      | Successfully | pettycashpartner     | HO        |

#  @disi_partner_deposit_filter_date
#  Scenario: Fill From Date more than 7 days in DISI Partner Deposit Page -- Successfully
#    Given I am in DISI Partner Deposit Page
#    * I hit Filter Button on DISI Partner Deposit
#    When I fill From Date ("more_7", "01,12", "") on Filter Field on DISI Partner Deposit
#    Then I hit Apply Button on DISI Partner Deposit
#
  @disi_partner_deposit_filter_status_withdraw
  Scenario Outline: Fill Status <status> in DISI Partner Deposit Page -- Successfully
    Given I am in DISI Partner Deposit Page
    * I hit Filter Button on DISI Partner Deposit
    * I fill From Date ("", "03,04", "") on Filter Field on DISI Partner Deposit
    * I fill List Box Transaction Type ("WITHDRAW") on Filter Field on DISI Partner Deposit
    When I fill List Box Status ("<status>") on Filter Field on DISI Partner Deposit
    Then I hit Apply Button on DISI Partner Deposit
    Examples:
      | status  |
      | All     |
      | Success |
      | Pending |
      | Failed  |
      | Refund  |

  @disi_partner_withdraw
  Scenario: Withdraw into registered Bank Account -- Successfully
    Given I am in DISI Partner Deposit Page
    When I choose and fill fields ("10000") in Withdraw Section
    Then I "Successfully" Withdraw into registered Bank Account

  @disi_partner_withdraw_failed
  Scenario Outline: Failed Withdraw into registered Bank Account -- Successfully
    Given I am in DISI Partner Deposit Page
    When I choose and fill fields in Withdraw Section with invalid "<param>" "<desc>"
    Then I "<condition>" Withdraw into registered Bank Account
    Examples:
      | condition | param       | desc          |
      | Failed    | otp         | Invalid       |
      | Failed    | amount      | Invalid       |
      | Failed    | balance     | Insufficient  |
      | Failed    | bankAccount | NotFound      |

  @disi_partner_deposit_export_history_transaction
  Scenario: Export data history transaction in DISI Partner Deposit Page -- Successfully
    Given I am in DISI Partner Deposit Page
    * I hit Filter Button on DISI Partner Deposit
    * I fill From Date ("", "03,04", "") on Filter Field on DISI Partner Deposit
    * I fill List Box Transaction Type ("WITHDRAW") on Filter Field on DISI Partner Deposit
    When I fill List Box Status ("All") on Filter Field on DISI Partner Deposit
    Then I hit Apply Button on DISI Partner Deposit
    Then I hit Export Button on DISI Partner Deposit