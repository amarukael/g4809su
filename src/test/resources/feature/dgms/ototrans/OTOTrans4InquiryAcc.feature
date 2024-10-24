Feature: OTOTrans Inquiry Account
  Testing API Inquiry Account

  @inquiry_account_sukses
  Scenario Outline: Partner hit API Inquiry Account OTOTrans -- successfully
    Given Valid Request ("<partnerId>", "<productId>", "<customerId>") API Inquiry Account OTOTrans
    When Partner hits API Inquiry Account OTOTrans
    Then Partner gets response "<rc>" API Inquiry Account OTOTrans
    Examples:
      | partnerId | productId | customerId    | rc |
      | SLS       | 014       | 000000000359  | 00 |
#      | SLS       | 002       | 8760673566    | 00 |

  @inquiry_account_failed
  Scenario Outline: Partner hit API Inquiry Account OTOTrans with invalid param <param> <descTest> -- failed
    Given Invalid Request param "<param>" "<descTest>" ("<partnerId>", "<productId>", "<customerId>") API Inquiry Account OTOTrans
    When Partner hits API Inquiry Account OTOTrans
    Then Partner gets response "<rc>" API Inquiry Account OTOTrans
    Examples:
      | param         | descTest      | partnerId  | productId  | customerId  | rc  |
      | trackingref   | Empty         | SLS        | 002        | 8760673566  | 00  |
      | trxdate       | Empty         | SLS        | 002        | 8760673566  | 00  |
      | trxdate       | Wrong         | SLS        | 002        | 8760673566  | 00  |
      | partnerid     | Empty         |            | 002        | 8760673566  | 34  |
      | partnerid     | > 6           | SLSXXXXX   | 002        | 8760673566  | 34  |
      | productid     | Empty         | SLS        |            | 8760673566  | 33  |
      | productid     | Wrong         | SLS        | 02         | 8760673566  | 33  |
      | productid     | > 3           | SLS        | 0024       | 8760673566  | 33  |
      | customerid    | Empty         | SLS        | 002        |             | EE  |
      | customerid    | AlfaNumeric   | SLS        | 002        | 87606735xx  | 05  |
      | customerid    | > 9           | SLS        | 002        | 8760673566  | 00  |
      | signature     | Empty         | SLS        | 002        | 8760673566  | 34  |
      | signature     | Wrong         | SLS        | 002        | 8760673566  | 34  |

#  note
#  trackingref Empty harusnya rc != 00
#  trackingref di dok 256 sedangkan di db hanya 99 ini gimana?
#  trxdate Empty harusnya rc != 00
#  partnerid Empty harusnya rc 31
#  partnerid > 6 harusnya rc 31
#  customerid AlfaNumeric harusnya EE, di dok type numeric
#  customerid > 9 harusnya rc != 00, jika iya di dok tolong di periksa