Feature: Dims Inquiry
    Testing API Dims Inquiry

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

    @inq_header_failed
    Scenario Outline: Partner hit API Account Inquiry DIMS with invalid header <param> -- failed
        Given Valid Request ("2", "014", "BCA", "000000000359", "04") API Account Inquiry DIMS With Invalid header "<param>"
        When Partner hits API Account Inquiry DIMS
        Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
        Examples:
            | param                 | rc        | httpCode |
            | authorization         | 4017302   | 401      |
            | authorizationExpired  | 4017305   | 401      |
            | timeStamps            | 4007336   | 400      |
            | signature             | 4017302   | 401      |
#            | externalId            | 4007302   | 400     |

    @inq_param_failed
    Scenario Outline: Partner hit API Account Inquiry DIMS with invalid param <param> <descTest> -- failed
        Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountName>", "<accountNo>", "<categoryPurchCode>") API Account Inquiry DIMS
        When Partner hits API Account Inquiry DIMS
        Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Account Inquiry DIMS
        Examples:
            | param                      | descTest    | productId | bankCode | accountName | accountNo             | categoryPurchCode | rc      | httpCode |
            | productId                  | Empty       |           | 014      | BCA         | 000000000359           | 04                | 4001503 | 400      |
            | productId                  | 0           | 0         | 014      | BCA         | 000000000359           | 04                | 4001503 | 400      |
            | bankCode                   | Empty       | 2         |          | BCA         | 000000000359           | 04                | 4001503 | 400      |
            | bankCode                   | AlfaNumeric | 2         | a14      | BCA         | 000000000359           | 04                | 2001550 | 200      |
            | bankCode                   | < 3         | 2         | 02       | BCA         | 000000000359           | 04                | 2001550 | 200      |
            | bankCode                   | > 3         | 2         | 0140     | BCA         | 000000000359           | 04                | 2001550 | 200      |
            | accountName                | Empty       | 2         | 014      |             | 000000000359           | 04                | 4001503 | 400      |
            | accountNo                  | Empty       | 2         | 014      | BCA         |                       | 04                | 4001503 | 400      |
#            | accountNo                     | < 10          | 2         | 014       | BCA           | 60004                 | 04                | 4001503   | 400       |
            | accountNo                  | > 20        | 2         | 014      | BCA         | 0000000003594444444444 | 04                | 2001550 | 200      |
            | accountNo                  | AlfaNumeric | 2         | 014      | BCA         | 000000000359Z          | 04                | 2001550 | 200      |
            | originalPartnerReferenceNo | Empty       | 2         | 014      | BCA         | 000000000359           | 04                | 4001503 | 400      |
            | categoryPurchaseCode       | Empty       | 2         | 014      | BCA         | 000000000359           |                   | 4001503 | 400      |