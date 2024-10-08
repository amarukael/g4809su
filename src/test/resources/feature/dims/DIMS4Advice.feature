Feature: Dims Advice
  Testing API Dims Advice

  @auth_sukses
  Scenario: Partner hit API Authorization DIMS -- successfully
    Given Valid Request API Authorization DIMS
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

  @inq_sukses
  Scenario: Partner hit API Account Inquiry DIMS -- successfully
    Given Valid Request ("2", "014", "BCA", "000000000359", "04") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "2001500" and http code "200" API Account Inquiry DIMS

  @pay_sukses
  Scenario: Partner hit API Payment DIMS -- successfully
    Given Valid Request ("1", "014", "000000000359", "BCA", "1", "1", "04", "Test", "IDR", "10000.00", "", "") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Success response "2001600" and http code "200" API Payment DIMS

#  @pay_param_failed
#  Scenario: Partner hit API Payment DIMS with invalid param originalPartnerReferenceNo Double -- failed
#    Given Invalid Request param "originalPartnerReferenceNo" ("Double", "1", "014", "000000000359", "BCA", "1", "1", "04", "", "IDR", "10000.00", "", "") API Payment DIMS
#    When Partner hits API Payment DIMS
#    Then Partner gets the Failed response "2001650" and http code "200" API Payment DIMS

  @adv_sukses
  Scenario: Partner hit API Advice DIMS -- successfully
    Given Valid Request ("IDR", "10000.00") API Advice DIMS
    When Partner hits API Advice DIMS
    Then Partner gets the Success response "2001700" and http code "200" API Advice DIMS

  @adv_header_failed
  Scenario Outline: Partner hit API Advice DIMS with invalid header <param> -- failed
    Given Valid Request ("IDR", "10000.00") API Advice DIMS With Invalid header "<param>"
    When Partner hits API Advice DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Advice DIMS
    Examples:
      | param                 | rc        | httpCode  |
      | authorization         | 4017302   | 401       |
      | authorizationExpired  | 4017305   | 401       |
      | timeStamps            | 4007336   | 400       |
      | signature             | 4017302   | 401       |
#      | externalId            | 4007302   | 400      |

  @adv_param_failed
  Scenario Outline: Partner hit API Advice DIMS with invalid param <param> <descTest> -- failed
    Given Invalid Request param "<param>" ("<descTest>", "<currency>", "<amount>") API Advice DIMS
    When Partner hits API Advice DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Advice DIMS
    Examples:
      | param                      | descTest | currency | amount   | rc      | httpCode |
      | originalPartnerReferenceNo | Empty    | IDR      | 10000.00 | 4001703 | 400      |
      | originalPartnerReferenceNo | Wrong    | IDR      | 10000.00 | 4041700 | 404      |
#    | originalReferenceNo        |	Empty         | IDR       |	10000.00  |	4001703 | 400       |
#    | originalReferenceNo	      | Wrong         |	IDR       |	10000.00  |	5047368 | 504       |
      | currency                   | Empty    |          | 10000.00 | 4001703 | 400      |
#    | currency	                  |	Lower         |	idr	      | 10000.00  |	5047368 | 504       |
#    | currency	                  |	Wrong         |	AS!	      | 10000.00  |	2001750 | 200       |
#    | currency	                  |	< 3           |	ID        |	10000.00  |	2001750 | 200       |
#    | currency	                  |	> 3           |	IDR1      |	10000.00  |	2001750 | 200       |
      | amount                     | Empty    | IDR      |          | 4001703 | 400      |
#    | amount	                  |	Wrong Format  |	IDR       |	10000	  | 2001750 | 200       |
#    | amount	                  |	AlfaNumeric   |	IDR       |	10asd.00  |	5047368 | 504       |
#    | amount	                  |	< 10000       |	IDR       |	5000.00   |	2001750 | 200       |