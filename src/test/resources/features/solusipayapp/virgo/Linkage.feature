@SolusiPayAPP
Feature: Solusipayapp

  Background:
    Given user at landing page
    Then user click login
    Then user at login page
    When user fill login form with username "" and password ""
    Then user "Successfully" to login


  @Linkage
  Scenario: User linkage to virgo e-wallet
    Given user click linkage
    Then virgo web view display
    When user follow all virgo web view step with passcode "123123"
    Then virgo account linked with solpay account