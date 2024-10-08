Feature: Dims Payment
    Testing API Dims Payment

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

    @inq_sukses_pay_failed
    Scenario: Partner hit API Account Inquiry DIMS For Payment Case Failed-- successfully
        Given Valid Request ("2", "014", "BCA", "000000000359", "04") API Account Inquiry DIMS
        When Partner hits API Account Inquiry DIMS
        Then Partner gets the Success response "2001500" and http code "200" API Account Inquiry DIMS

    @pay_header_failed
    Scenario Outline: Partner hit API Payment DIMS with invalid header <param> -- failed
        Given Valid Request ("1", "014", "000000000359", "BCA", "1", "1", "04", "Test", "IDR", "10000.00", "", "") API Payment DIMS With Invalid header "<param>"
        When Partner hits API Payment DIMS
        Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Payment DIMS
        Examples:
            | param                 | rc        | httpCode  |
            | authorization         | 4017302   | 401       |
            | authorizationExpired  | 4017305   | 401       |
            | timeStamps            | 4007336   | 400       |
            | signature             | 4017302   | 401       |
#            | externalId            | 4007302   | 400      |
#            | xIpAddress            | 4007302   | 400      |
#            | xLongitude            | 4007302   | 400      |
#            | xLatitude             | 4007302   | 400      |

    @pay_param_failed
    Scenario Outline: Partner hit API Payment DIMS with invalid param <param> <descTest> -- failed
        Given Invalid Request param "<param>" ("<descTest>", "<productId>", "<bankCode>", "<accountNo>", "<accountName>", "<customerType>", "<customerResidence>", "<categoryPurchCode>", "<purpose>", "<currency>", "<amount>", "<rcvIdNum>", "<rcvEmail>") API Payment DIMS
        When Partner hits API Payment DIMS
        Then Partner gets the Failed response "<rc>" and http code "<httpCode>" API Payment DIMS
        Examples:
            | param                         | descTest      | productId | bankCode  | accountNo             | accountName   | customerType   | customerResidence | categoryPurchCode | purpose      | currency | amount    | rcvIdNum | rcvEmail | rc        | httpCode   |
            | productId                     | Empty         |           | 014       | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | productId                     | 0             | 0         | 014       | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | originalPartnerReferenceNo    | Empty         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | originalPartnerReferenceNo    | Wrong         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 5047368   | 504        |
            | originalPartnerReferenceNo    | Double        | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
            | bankCode                      | Empty         | 1         |           | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | bankCode                      | AlfaNumeric   | 1         | a02       | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
            | bankCode                      | < 3           | 1         | 02        | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
            | bankCode                      | > 3           | 1         | 0140      | 000000000359           | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
            | accountNo                     | Empty         | 1         | 014       |                       | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
#            | accountNo                     | < 10          | 1         | 014       | 60004                 | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
            | accountNo                     | > 20          | 1         | 014       | 0000000003594444444444 | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 5047368   | 504        |
            | accountNo                     | AlfaNumeric   | 1         | 014       | 000000000359Z          | BCA           | 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 2001650   | 200        |
            | accountName                   | Empty         | 1         | 014       | 000000000359           |				| 1              | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | customerType                  | Empty         | 1         | 014       | 000000000359           | BCA           |                | 1                 | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | customerResidence             | Empty         | 1         | 014       | 000000000359           | BCA           | 1              |                   | 04                | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | categoryPurchaseCode          | Empty         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 					 | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
#            | purpose                       | Empty         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04				 | purpose      | IDR      | 10000.00  |          |          | 4001603   | 400        |
            | currency                      | Empty         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      |          | 10000.00  |          |          | 4001603   | 400        |
#            | currency                      | Lower         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | idr      | 10000.00  |          |          | 5047368   | 504        |
            | currency                      | Wrong         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | AS!      | 10000.00  |          |          | 2001650   | 200        |
            | currency                      | < 3           | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | ID       | 10000.00  |          |          | 2001650   | 200        |
            | currency                      | > 3           | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | IDR1     | 10000.00  |          |          | 2001650   | 200        |
            | amount                        | Empty         | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | IDR      |           |          |          | 4001603   | 400        |
            | amount                        | Wrong Format  | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | IDR      | 10000     |          |          | 2001650   | 200        |
            | amount                        | AlfaNumeric   | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | IDR      | 10asd.00  |          |          | 5047368   | 504        |
            | amount                        | < 10000       | 1         | 014       | 000000000359           | BCA           | 1              | 1                 | 04   			 | purpose      | IDR      | 5000.00   |          |          | 2001650   | 200        |