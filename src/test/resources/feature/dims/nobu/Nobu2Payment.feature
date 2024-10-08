Feature: Payment Dims Via Biller Nobu

  @auth_sukses
  Scenario: [DIMS] Partner hit API Authorization DIMS Biller Nobu
    Given Valid Request API Authorization DIMS
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

  @inq_sukses
  Scenario: [DIMS] Partner hit API Account Inquiry DIMS Biller Nobu
    Given Valid Request ("2", "002", "BRI", "510654300", "04") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "2001500" and http code "200" API Account Inquiry DIMS

  @pay_sukses
  Scenario: [DIMS] Partner hit API Payment DIMS Biller Nobu
    Given Valid Request ("1", "002", "510654300", "", "1", "1", "04", "", "IDR", "10000.00", "", "") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Success response "2001600" and http code "200" API Payment DIMS

  @inq_sukses_pay_failed
  Scenario Outline: [DIMS] Partner hit API Account Inquiry DIMS For Payment Case Failed Biller Nobu
    Given Valid Request ("<param>", "<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param                         | descTest      | productId | bankCode  | accountName   | accountNo             | categoryPurchCode | rc        | httpCode  |
      | global                        | valid         | 2         | 002       | Test          | 510654300             | 04                | 2001500   | 200       |
      | bankCode                      | Invalid       | 2         | 002       | Test          | 510654301             | 04                | 2001500   | 200       |
      | accountNo                     | Invalid       | 2         | 002       | Test          | 510654305             | 04                | 2001500   | 200       |

  @pay_param_failed
  Scenario Outline: [DIMS] Partner hit API Payment DIMS with invalid param <param> <descTest> Biller Nobu
    Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountNo>", "<accountName>", "<customerType>", "<customerResidence>", "<categoryPurchCode>", "<purpose>", "<currency>", "<amount>", "<rcvIdNum>", "<rcvEmail>") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Payment DIMS
    Examples:
      | param                         | descTest      | productId | bankCode  | accountNo  | accountName   | customerType   | customerResidence | categoryPurchCode | purpose      | currency | amount    | rcvIdNum | rcvEmail | rc        | httpCode   |
      | authNobu                      | Invalid       | 1         | 002       | 510654300  |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
      | originalPartnerReferenceNo    | Wrong         | 1         | 002       | 510654300  |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 5047368   | 504        |
      | bankCode                      | Invalid       | 1         | 002       | 510654301  |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
      | accountNo                     | Invalid       | 1         | 002       | 510654305  |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001645   | 400        |
      | amount                        | Invalid       | 1         | 002       | 510654300  |               | 1              | 1                 | 04                | purpose      | IDR      | 1000.00   |          |          | 2001650   | 200        |
#      | unAuthHeaderPay               | Invalid       | 1         | 002       | 510654300  |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 5047368   | 504        |
      | invalidSignPay                | Invalid       | 1         | 002       | 510654300  |               | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 5047368   | 504        |

#    * Note
#  untuk unAuthHeaderPay tidak bisa dites, karena tidak bisa memodif access token yang didapat