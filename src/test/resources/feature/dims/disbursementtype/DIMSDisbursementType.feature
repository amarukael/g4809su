Feature: Dims Switch into Inter or Intra

  @auth_sukses
  Scenario: Partner hit API Authorization DIMS -- successfully
    Given Valid Request API Authorization DIMS
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

  @inq_sukses_disbursement_type
  Scenario Outline: Partner hit API Account Inquiry DIMS <descTest> -- successfully
    Given Valid Request ("<param>", "<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param     | descTest  | productId  | bankCode | accountName  | accountNo     | categoryPurchCode  | rc        | httpCode  |
      | bankCode  | Inter     | 2          | 014      | BCA          | 775627030447  | 04                 | 2001500   | 200       |
      | bankCode  | Intra     | 2          | 501      | BLU          | 000000000359  | 04                 | 2001500   | 200       |

  @payment_sukses_disbursement_type
  Scenario Outline: Partner hit API Payment DIMS with invalid param <param> <descTest> -- <condition>
    Given Valid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountNo>", "<accountName>", "<customerType>", "<customerResidence>", "<categoryPurchCode>", "<purpose>", "<currency>", "<amount>", "<rcvIdNum>", "<rcvEmail>") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Success response "<rc>" and http code "<httpCode>" API Payment DIMS
    Examples:
      | condition    | param       | descTest      | productId | bankCode  | accountNo     | accountName   | customerType   | customerResidence | categoryPurchCode | purpose      | currency | amount    | rcvIdNum | rcvEmail | rc        | httpCode   |
      | failed       | bankCode    | Inter         | 1         | 014       | 775627030447  | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
      | successfully | bankCode    | Intra         | 1         | 501       | 000000000359  | BLU           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001600   | 200        |
