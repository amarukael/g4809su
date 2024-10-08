Feature: Dims Token
    Testing API Dims Token

    @auth_sukses
    Scenario: Partner hit API Authorization DIMS -- successfully
        Given Valid Request API Authorization DIMS
        When Partner hits API Authorization DIMS
        Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

    @auth_failed
    Scenario Outline: Partner hit API Authorization DIMS with invalid <param> -- failed
        Given Invalid Request "<param>" API Authorization DIMS
        When Partner hits API Authorization DIMS
        Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Authorization DIMS
        Examples:
            | param         | rc        | httpCode |
            | clientKey     | 4007337   | 400      |
            | timeStamps    | 4007336   | 400      |
            | signature     | 4017301   | 401      |
            | grantType     | 4007335   | 400      |