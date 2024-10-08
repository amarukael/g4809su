Feature: Advice Dims Via Biller Danamon

  @auth_sukses
  Scenario: [DIMS] Partner hit API Authorization DIMS Biller Danamon
    Given Valid Request API Authorization DIMS
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

  @inq_sukses
  Scenario: [DIMS] Partner hit API Account Inquiry DIMS Biller Danamon
    Given Valid Request ("2", "002", "BRI", "?", "04") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "2001500" and http code "200" API Account Inquiry DIMS

  @pay_sukses
  Scenario: [DIMS] Partner hit API Payment DIMS Biller Danamon
    Given Valid Request ("1", "002", "?", "", "1", "1", "04", "", "IDR", "10000.00", "", "") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Success response "2001600" and http code "200" API Payment DIMS

  @adv_sukses
  Scenario: [DIMS] Partner hit API Advice DIMS Biller Danamon
    Given Valid Request ("IDR", "10000.00") API Advice DIMS
    When Partner hits API Advice DIMS
    Then Partner gets the Success response "2001700" and http code "200" API Advice DIMS

  @adv_param_failed
  Scenario Outline: [DIMS] Partner hit API Advice DIMS with invalid param <param> <descTest> Biller Danamon
    Given Invalid Request param "<param>" ("<descTest>", "<currency>", "<amount>") API Advice DIMS
    When Partner hits API Advice DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Advice DIMS
    Examples:
      | param	                            | descTest      | currency  |	amount    |	rc	    | httpCode  |
      | originalPartnerReferenceNoDanamon   | Wrong         | IDR       |	10000.00  |	4041745 | 404       |
