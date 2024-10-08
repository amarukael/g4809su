Feature: Inquiry Dims Via Biller Permata

  @auth_sukses
  Scenario: [DIMS] Partner hit API Authorization DIMS Biller Permata
    Given Valid Request API Authorization DIMS
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

  @inq_sukses
  Scenario: [DIMS] Partner hit API Account Inquiry DIMS Biller Permata
    Given Valid Request ("2", "426", "Mega", "021790020015466", "04") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "2001500" and http code "200" API Account Inquiry DIMS

  @inq_param_failed
  Scenario Outline: [DIMS] Partner hit API Account Inquiry DIMS with invalid param <param> <descTest> Biller Permata
    Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param          | descTest | productId | bankCode | accountName | accountNo       | categoryPurchCode | rc      | httpCode |
      | authPermata    | Invalid  | 2         | 426      | Mega        | 021790020015466 | 04                | 2001550 | 200      |
#      | accountNo      | Invalid  | 2         | 011      | Mega        | 021790020015466 | 04                | 4001545 | 400      |
      | invalidSignInq | Invalid  | 2         | 426      | Mega        | 021790020015466 | 04                | 5047368 | 504      |

#* Note
#  accountNo Invalid tidak perlu di tes