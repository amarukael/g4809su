@SolusiPayAPP
Feature: Solusipayapp

  Background:
    Given user open solusipayapp


  @Register
  Scenario Outline: User "<condition>" Register to solusi pay app "<case>"
    Given user at landing page
    Then user click register
    Then user at register page
    When user fill register form ("<prefix>"<length>, "<name>", "<password>" , "<repassword>")
    Then user "<condition>" to get otp (message : "<message>")
    Examples:
      | condition    | prefix      | password |repassword   | name |length |case                      | message                                   |
      | Successfully | 081         | Dummy123@|  Dummy123@  | dummy|10     |success                   |                                           |
      | Failed       | 081         | Dummy123@|  Dummy123   | dummy|10     |repassword not same       |Your passwords do no match                 |
      | Failed       | 081         | Dummy123@|  Dummy123@  | dummy|0      |phone number length < 10  |Phone Numbers should be between 10 - 13 Digits|
      | Failed       | 081         | Dummy123@|  Dummy123@  | dummy|20     |phone number length > 10  |Phone Numbers should be between 10 - 13 Digits|
      | Failed       | 081         | Dummy123@|  Dummy123@  |      |0      |Name blank.               |Name is required.                          |
      | Failed       |             | Dummy123@|  Dummy123@  | dummy|0      |phone number blank.       |Mobile Phone Number is required.           |
      | Failed       |081          |          |             | dummy|0      |password blank.       |Password is required.                      |
      | Failed       |081          | qwertyuiqetwqu|             | dummy|0      |invalid format.       |Must include uppercase, lowercase, symbol character, and number|
