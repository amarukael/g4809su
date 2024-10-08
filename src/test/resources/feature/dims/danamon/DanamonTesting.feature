Feature: Inquiry Dims Via Biller Danamon

  @auth_sukses
  Scenario: [DIMS] Partner hit API Authorization DIMS Biller Danamon
    Given Valid Request API Authorization DIMS
    When Partner hits API Authorization DIMS
    Then Partner gets the Success response "2007300" and http code "200" API Authorization DIMS

  @inq_sukses
  Scenario: [DIMS] Partner hit API Account Inquiry DIMS Biller Danamon
    Given Valid Request ("2", "110", "Test", "0013255296001", "01") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "2001500" and http code "200" API Account Inquiry DIMS

#  110 | 0013255296001
#  013 | 9832319535

  @inq_param_failed
  Scenario Outline: [DIMS] Partner hit API Account Inquiry DIMS with invalid param <param> <descTest> Biller Danamon
    Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param               | descTest | productId | bankCode | accountName | accountNo  | categoryPurchCode | rc      | httpCode |
      | authDanamon         | Invalid  | 2         | 013      | Test        | 9832319535 | 01                | 2001550 | 200      |
      | invalidSignInq      | Invalid  | 2         | 013      | Test        | 9832319535 | 01                | 5047368 | 504      |
      | accountNo           | Invalid  | 2         | 013      | Test        | 9832319536 | 01                | 4001545 | 400      |
      | accountNo           | Inactive | 2         | 023      | Test        | 3011000183 | 01                | 4001545 | 400      |
      | beneficiaryBankCode | Empty    | 2         | 014      | Test        | 1122334455 | 01                | 4001502 | 400      |

  @pay_sukses
  Scenario: [DIMS] Partner hit API Payment DIMS Biller Danamon
    Given Valid Request ("1", "110", "0013255296001", "", "01", "01", "04", "Test", "IDR", "10000.00", "", "") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Success response "2001600" and http code "200" API Payment DIMS

  @inq_sukses_pay_failed
  Scenario Outline: [DIMS] Partner hit API Account Inquiry DIMS For Payment Case Failed Biller Danamon
    Given Valid Request ("<param>", "<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
    When Partner hits API Account Inquiry DIMS
    Then Partner gets the Success response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
    Examples:
      | param  | descTest     | productId | bankCode | accountName | accountNo    | categoryPurchCode | rc      | httpCode |
      | global | valid        | 2         | 013      | Test        | 9832319535   | 01                | 2001500 | 200      |
#      | amount | insufficient | 2         | 008      | Test        | 003600216638 | 01                | 2001500 | 200      |
#      | xEternalID | conflict_1 | 2         | 013      | Test        | 9832319535 | 01                | 2001500 | 200      |
#      | xEternalID | conflict_2 | 2         | 013      | Test        | 9832319535 | 01                | 2001500 | 200      |

  @pay_param_failed
  Scenario Outline: [DIMS] Partner hit API Payment DIMS with invalid param <param> <descTest> Biller Danamon
    Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountNo>", "<accountName>", "<customerType>", "<customerResidence>", "<categoryPurchCode>", "<purpose>", "<currency>", "<amount>", "<rcvIdNum>", "<rcvEmail>") API Payment DIMS
    When Partner hits API Payment DIMS
    Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Payment DIMS
    Examples:
      | param                      | descTest     | productId | bankCode | accountNo    | accountName | customerType | customerResidence | categoryPurchCode | purpose | currency | amount   | rcvIdNum | rcvEmail | rc      | httpCode |
#      | authDanamon                | Invalid  | 1         | 013      | 9832319535 |             | 01           | 26                | 01                | purpose | IDR      | 10000.00 |          |          | 2001650 | 200      |
#      | currency                   | > 3      | 1         | 013      | 9832319535 |             | 01           | 01                | 04                | purpose | IDR1     | 10000.00 |          |          | 5047368 | 504      |
      | originalPartnerReferenceNo | Wrong        | 1         | 013      | 9832319535   |             | 01           | 01                | 04                | purpose | IDR      | 10000.00 |          |          | 5047368 | 504      |
      | originalPartnerReferenceNo | Double       | 1         | 013      | 9832319535   |             | 01           | 01                | 04                | purpose | IDR      | 10000.00 |          |          | 4091634 | 409      |
#      | xEternalID                 | Conflict | 1         | 013      | 9832319535 |             | 01           | 01                | 04                | purpose | IDR      | 10000.00 |          |          |         |          |
#      | amount                     | Insufficient | 1         | 008      | 003600244515 |             | 01           | 01                | 04                | purpose | IDR      | 10000.00 |          |          | 2001650 | 200      |
#      | bankCode                   | Invalid  | 1         | 013      | 9832319535 |             | 01            | 26                | 01                | purpose | IDR      | 10000.00 |          |          | 2001650 | 200      |
#      | accountNo                  | Invalid  | 1         | 013      | 9832319535 |             | 01            | 26                | 01                | purpose | IDR      | 10000.00 |          |          | 4001645 | 400      |
#      | amount                     | Invalid  | 1         | 013      | 9832319535 |             | 01            | 26                | 01                | purpose | IDR      | 1000.00  |          |          | 2001650 | 200      |
#      | invalidSignPay             | Invalid  | 1         | 013      | 9832319535 |             | 01           | 26                | 01                | purpose | IDR      | 10000.00 |          |          | 2001650 | 200      |

  @adv_sukses
  Scenario: [DIMS] Partner hit API Advice DIMS Biller Danamon
    Given Valid Request ("IDR", "10000.00") API Advice DIMS
    When Partner hits API Advice DIMS
    Then Partner gets the Success response "2001700" and http code "200" API Advice DIMS