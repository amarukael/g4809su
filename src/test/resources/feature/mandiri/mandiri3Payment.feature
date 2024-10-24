Feature: Mandiri Payment
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
      |{\"partnerServiceId\":\"   99007\",\"customerNo\":\"40000000000002\",\"virtualAccountNo\":\"9900740000000000002\",\"trxDateInit\":\"2024-04-30T14:40:21+07:00\",\"channelCode\":\"6021\",\"language\":\"ID\",\"hashedSourceAccountNo\":\"\",\"amount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"inquiryRequestId\":\"1180768\"}|



  @payment_success
  Scenario Outline: Mandiri hit API Payment VA
    Given Valid Request ("<reqBody>") API Payment
    When Mandiri hits API Payment VA
    Then Mandiri gets the Success response "2002500" and http code "200" API Payment VA
    Examples:
      |reqBody          |
    |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}|

  @payment_failed
  Scenario Outline: Mandiri hit API Payment VA with invalid request
    Given Invalid Request ("<reqBody>", "<attribute>", "<descTest>", "<param>") API Payment
    When Mandiri hits API Payment VA
    Then Mandiri gets the Success response "<rc>" and http code "<httpCode>" API Payment VA
    Examples:
      |attribute                 |descTest| param|rc       |httpCode       |reqBody          |
      |CustomerNo                |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |PaymentRequestId          |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |VirtualAccountNo          |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |PartnerServiceId          |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |VirtualAccountName        |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |TrxDateTime               |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |ChannelCode               |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |ReferenceNo               |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
      |HashedSourceAccountNo     |empty   |      | 4002502 |400            |{\"paymentRequestId\":\"663475098428582904\",\"customerNo\":\"40000000000002\",\"partnerServiceId\":\"   99007\",\"virtualAccountNo\":\"9900740000000000002\",\"virtualAccountName\":\"ABCD1291829182983198391931898391\",\"trxDateTime\":\"2022-03-15T10:00:00+07:00\",\"channelCode\":\"6021\",\"referenceNo\":\"2024031510000000017\",\"hashedSourceAccountNo\":\"90492f66133276d890873a68fc91e56f\",\"paidAmount\":{\"value\":\"5000.00\",\"currency\":\"IDR\"},\"flagAdvise\":\"N\",\"paidBills\":\"FFFFFF\"}                 |
