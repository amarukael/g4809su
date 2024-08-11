@SolusiPayAPP
Feature: Solusipayapp

  Background:
    Given user open solusipayapp

    @LoginWithPhoneNumber
  Scenario Outline: User "<condition>" Login to solusi pay app with phone number
    Given user at landing page
    Then user click login
    Then user at login page
    When user fill login form with username "<username>" and password "<password>"
    Then user "<condition>" to login
    Examples:
    | condition    | username | password |
    | Successfully | 08100000001  | Dummy123@|
    | failed | 08116391011  | 12345678 |

    @LoginWithEmail
  Scenario Outline: User Login to solusi pay app with email
    Given user at landing page
    Then user click login
    Then user at login page
    When user click "<login method>" login
    And user choose "<email>"
    Then user "<condition>" to login
    Examples:
    | condition    | login method | email |
    | Successfully | google  | hubert.kelby@gmail.com|
