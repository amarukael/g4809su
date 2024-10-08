Feature: Payment Dims Via Biller Danamon

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

  @inq_sukses_pay_failed
  Scenario Outline: [DIMS] Partner hit API Account Inquiry DIMS For Payment Case Failed Biller Danamon
    Given Valid Request ("<param>", "<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param                         | descTest      | productId | bankCode  | accountName   | accountNo     | categoryPurchCode | rc        | httpCode  |
      | global                        | valid         | 2         | 002       | Test          | ?             | 04                | 2001500   | 200       |
      | bankCode                      | Invalid       | 2         | 002       | Test          | ?             | 04                | 2001500   | 200       |
      | accountNo                     | Invalid       | 2         | 002       | Test          | ?             | 04                | 2001500   | 200       |

  @pay_param_failed
  Scenario Outline: [DIMS] Partner hit API Payment DIMS with invalid param <param> <descTest> Biller Danamon
    Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountNo>", "<accountName>", "<customerType>", "<customerResidence>", "<categoryPurchCode>", "<purpose>", "<currency>", "<amount>", "<rcvIdNum>", "<rcvEmail>") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Payment DIMS
    Examples:
      | param                         | descTest      | productId | bankCode  | accountNo   | accountName   | customerType   | customerResidence | categoryPurchCode | purpose      | currency | amount    | rcvIdNum | rcvEmail | rc        | httpCode   |
      | authDanamon                   | Invalid       | 1         | 002       | ?           |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
      | originalPartnerReferenceNo    | Wrong         | 1         | 002       | ?           |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 5047368   | 504        |
      | bankCode                      | Invalid       | 1         | 002       | ?           |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
      | accountNo                     | Invalid       | 1         | 002       | ?           |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001645   | 400        |
      | amount                        | Invalid       | 1         | 002       | ?           |               | 1              | 1                 | 04                | purpose      | IDR      | 1000.00   |          |          | 2001650   | 200        |
      | invalidSignPay                | Invalid       | 1         | 002       | ?           |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 5047368   | 504        |
