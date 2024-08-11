Feature: SOB Login

  @login
  Scenario Outline: Login as Admin
    Given I open SOB Website
    When I logged in with username "<username>"
    And I click submit button
    Then I on a Homepage
    Examples:
      | username |
      | admin    |
#      | adminstg |

  @login_same_user
  Scenario: Login with same User
    Given I open SOB Website
    And I logged in with username "admin"
    And I click submit button
    And I on a Homepage
    When I logged in with same username "admin"
    Then I back to window 1st login
    Then I back to Login Page

  @session_end
  Scenario: Login with expired session
    Given I open SOB Website
    When I logged in with username "admin"
    And I click submit button
    Then I on a Homepage
    Then I wait expired session and refresh Homepage
    Then I back to Login Page