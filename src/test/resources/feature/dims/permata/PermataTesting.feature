Feature: Dims Biller Permata

  @auth_sukses
  Scenario: [DIMS] Partner hit API Authorization DIMS Biller Permata
    Given Valid Request API Authorization DIMS
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

  @inq_sukses
  Scenario: [DIMS] Partner hit API Account Inquiry DIMS Biller Permata
    Given Valid Request ("2", "011", "Danamon", "903600635936", "04") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "2001500" and http code "200" API Account Inquiry DIMS

  @inq_param_failed
  Scenario Outline: [DIMS] Partner hit API Account Inquiry DIMS with invalid param <param> <descTest> Biller Permata
    Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param          | descTest | productId | bankCode | accountName | accountNo    | categoryPurchCode | rc      | httpCode |
      | authPermata    | Invalid  | 2         | 011      | Danamon     | 903600635936 | 04                | 2001550 | 200      |
      | accountNo      | Invalid  | 2         | 011      | Danamon     | 903600635939 | 04                | 4001546 | 400      |
      | invalidSignInq | Invalid  | 2         | 011      | Danamon     | 903600635936 | 04                | 5047368 | 504      |

  @pay_sukses
  Scenario: [DIMS] Partner hit API Payment DIMS Biller Permata
    Given Valid Request ("1", "011", "903600635936", "", "1", "1", "04", "", "IDR", "10000.00", "", "") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Success response "2001600" and http code "200" API Payment DIMS

  @inq_sukses_pay_failed
  Scenario Outline: [DIMS] Partner hit API Account Inquiry DIMS For Payment Case Failed Biller Permata
    Given Valid Request ("<param>", "<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param  | descTest     | productId | bankCode | accountName | accountNo    | categoryPurchCode | rc      | httpCode |
      | global | valid        | 2         | 011      | Test        | 903600635936 | 04                | 2001500 | 200      |

  @pay_param_failed
  Scenario Outline: [DIMS] Partner hit API Payment DIMS with invalid param <param> <descTest> Biller Permata
    Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountNo>", "<accountName>", "<customerType>", "<customerResidence>", "<categoryPurchCode>", "<purpose>", "<currency>", "<amount>", "<rcvIdNum>", "<rcvEmail>") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Payment DIMS
    Examples:
      | param                      | descTest     | productId | bankCode | accountNo    | accountName | customerType | customerResidence | categoryPurchCode | purpose | currency | amount   | rcvIdNum | rcvEmail | rc      | httpCode |
      | authPermata                | Invalid      | 1         | 011      | 903600635936 |             | 1            | 1                 | 04                | purpose | IDR      | 10000.00 |          |          | 2001650 | 200      |
      | originalPartnerReferenceNo | Wrong        | 1         | 011      | 903600635936 |             | 1            | 1                 | 04                | purpose | IDR      | 10000.00 |          |          | 5047368 | 504      |
      | originalPartnerReferenceNo | Double       | 1         | 011      | 903600635936 |             | 1            | 1                 | 04                | purpose | IDR      | 10000.00 |          |          | 4091634 | 409      |
      | amount                     | Insufficient | 1         | 011      | 903600635936 |             | 1            | 1                 | 04                | purpose | IDR      | 10000.00 |          |          | 2001650 | 200      |
      | invalidSignPay             | Invalid      | 1         | 011      | 903600635936 |             | 1            | 1                 | 04                | purpose | IDR      | 10000.00 |          |          | 2001650 | 200      |