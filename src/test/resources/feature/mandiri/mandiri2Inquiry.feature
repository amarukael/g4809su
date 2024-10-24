Feature: Mandiri Inquiry
  Testing API Mandiri Inquiry

  @auth_success
  Scenario: Mandiri hit API Authorization Token to IDS -- successfully
    Given Valid Request API Authorization TOKEN
    When Mandiri hits API Authorization TOKEN
    Then Mandiri gets the Success response "2007300" and http code "200" API Authorization TOKEN


  @inq_success
  Scenario Outline: Mandiri hit API Inquiry VA
    Given Valid Request ("<reqBody>") API Inquiry
    When Mandiri hits API Inquiry VA
    Then Mandiri gets the Success response "2002400" and http code "200" API Inquiry VA
    Examples:
    |reqBody          |
    |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|

  @inq_header_failed
  Scenario Outline: Mandiri hit API Inquiry VA with invalid header <param> -- failed
    Given Given Valid Request ("<reqBody>") API Inquiry With Invalid header "<param>" - "<condition>"
    When Mandiri hits API Inquiry VA
    Then Mandiri gets the Success response "<rc>" and http code "<httpCode>" API Inquiry VA
    Examples:
      | param                 |condition            | rc        | httpCode |reqBody          |
      | authorization         |null                 | 4002402   | 400      |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      | authorization         |invalid value        | 4012401   | 401      |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      | timeStamps            |null                 | 4002402  | 400       |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      | timeStamps            |invalid format       | 4002401  | 400       |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      | signature             |null                 | 4002402   | 400      |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      | signature             |invalid value        | 4012400   | 401      |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      | externalId            |null                 | 4002402   | 400      |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"   9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|

  @inq_failed
  Scenario Outline: Mandiri hit API Inquiry VA with invalid request
    Given Invalid Request ("<reqBody>", "<attribute>", "<descTest>", "<param>") API Inquiry
    When Mandiri hits API Inquiry VA
    Then Mandiri gets the Success response "<rc>" and http code "<httpCode>" API Inquiry VA
    Examples:
      |attribute                 |descTest| param|rc       |httpCode       |reqBody          |
      |InquiryRequestId          |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      |CustomerNo                |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      |PartnerServiceId          |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      |VirtualAccountNo          |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      |TrxDateInit               |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      |ChannelCode               |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      |Language                  |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|
      |InquiryRequestId          |empty   |      |4002401  |400            |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|


