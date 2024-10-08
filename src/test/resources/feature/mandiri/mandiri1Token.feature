Feature: Mandiri direct Token (b2b)
  Testing API Mandiri direct Token


  @auth_success
  Scenario: Mandiri hit API Authorization Token to IDS -- successfully
    Given Valid Request API Authorization TOKEN
    When Mandiri hits API Authorization TOKEN
    Then Mandiri gets the Success response "2007300" and http code "200" API Authorization TOKEN

  @auth_failed_
  Scenario Outline: Mandiri hit API Authorization Token with invalid <param> <condition> -- failed
    Given Invalid Request "<param>" with "<condition>" API Authorization TOKEN
    When Mandiri hits API Authorization TOKEN
    Then Mandiri gets the Failed response "<rc>" and http code "<httpCode>" API Authorization TOKEN
    Examples:
      | param         |condition      | rc        | httpCode |
      | clientKey     |null           | 4007302   |400       |
      | timeStamps    |null           | 4007302   |400       |
      | signature     |null           | 4007302   |400       |

      | timeStamps    |invalid format  | 4007301   |400      |
      | signature     |invalid format  | 4017300   |401      |

      | grantType     |unsupported    | 4007300   |400       |


