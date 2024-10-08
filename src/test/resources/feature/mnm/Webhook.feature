Feature: M&M Webhook

  @webhook_get
  Scenario: [M&M] Hit WebHook Method GET
    Given Req valid data API WebHook M&M with Method GET
    When Hit API WebHook M&M with Method "GET"
    Then Response "200" API WebHook with Method "GET"

  @webhook_meta
  Scenario Outline: [M&M] Hit WebHook
    Given Req valid data("<type>", "<data>") Api WebHook M&M
    When Hit API WebHook M&M with Method "POST"
    Then Response "<httpCode>" API WebHook with Method "POST"
    Examples:
      | type               | data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | httpCode |
      | sent               | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000001\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"6281285312957\",\"phone_number_id\":\"105922732603297\"},\"statuses\":[{\"id\":\"wamid.HBgNNjI4MTI4NTMxMjk1NxUCABEYEjE4OTk5MENBMERCNDI4RDlGQQA=\",\"status\":\"sent\",\"timestamp\":\"1719289551\",\"recipient_id\":\"6281285312957\",\"conversation\":{\"id\":\"c4df136ed43b293f8c5e3e2406261001\",\"expiration_timestamp\":\"1719386044\",\"origin\":{\"type\":\"marketing\"}},\"pricing\":{\"billable\":true,\"pricing_model\":\"CBP\",\"category\":\"marketing\"}}]},\"field\":\"messages\"}]}]}                                                                          | 200      |
      | delivered          | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000001\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"6281285312957\",\"phone_number_id\":\"105922732603297\"},\"statuses\":[{\"id\":\"wamid.HBgNNjI4MTI4NTMxMjk1NxUCABEYEjE4OTk5MENBMERCNDI4RDlGQQA=\",\"status\":\"delivered\",\"timestamp\":\"1719376050\",\"recipient_id\":\"6281285312957\",\"conversation\":{\"id\":\"c4df136ed43b293f8c5e3e2406261001\",\"origin\":{\"type\":\"marketing\"}},\"pricing\":{\"billable\":true,\"pricing_model\":\"CBP\",\"category\":\"marketing\"}}]},\"field\":\"messages\"}]}]}                                                                                                             | 200      |
      | read               | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000001\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"6281285312957\",\"phone_number_id\":\"105922732603297\"},\"statuses\":[{\"id\":\"wamid.HBgNNjI4MTI4NTMxMjk1NxUCABEYEjE4OTk5MENBMERCNDI4RDlGQQA=\",\"status\":\"read\",\"timestamp\":\"1719376051\",\"recipient_id\":\"6281285312957\"}]},\"field\":\"messages\"}]}]}                                                                                                                                                                                                                                                                                                          | 200      |
      | reply_not_interest | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000001\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"6288991762332\",\"phone_number_id\":\"105922732603297\"},\"contacts\":[{\"profile\":{\"name\":\"Yudi M\"},\"wa_id\":\"6281285312957\"}],\"messages\":[{\"context\":{\"from\":\"6288991762332\",\"id\":\"wamid.HBgNNjI4MTI4NTMxMjk1NxUCABEYEjE4OTk5MENBMERCNDI4RDlGQQA=\"},\"from\":\"6281285312957\",\"id\":\"wamid.HBgNNjI4NTc4ODU2OTQ0MBUCABIYIDAwNkEwM0IxMjE0N0EwOURFNTI3NDc0RURFMEY0NUMwAA==\",\"timestamp\":\"1719376060\",\"type\":\"button\",\"button\":{\"payload\":\"No, I am not Interested\",\"text\":\"No, I am not Interested\"}}]},\"field\":\"messages\"}]}]} | 200      |
      | reply_interest     | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000001\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"6288991762332\",\"phone_number_id\":\"105922732603297\"},\"contacts\":[{\"profile\":{\"name\":\"Yudi M\"},\"wa_id\":\"6281285312957\"}],\"messages\":[{\"context\":{\"from\":\"6288991762332\",\"id\":\"wamid.HBgNNjI4MTI4NTMxMjk1NxUCABEYEjE4OTk5MENBMERCNDI4RDlGQQA=\"},\"from\":\"6281285312957\",\"id\":\"wamid.HBgNNjI4NTc4ODU2OTQ0MBUCABIYIDJEMEM4MkQwODFCQjBDNzJEQUE2MkYzQjlDNTRGMUM3AA==\",\"timestamp\":\"1719376065\",\"type\":\"button\",\"button\":{\"payload\":\"Yes, I am Interested\",\"text\":\"Yes, I am Interested\"}}]},\"field\":\"messages\"}]}]}       | 200      |
      | sent               | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000002\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"628998945103\",\"phone_number_id\":\"105922732603299\"},\"statuses\":[{\"id\":\"wamid.HBgMNjI4OTk4OTQ1MTAzFQIAERgSMEJFMEFERUU0N0ZCOTYwMjY2AA==\",\"status\":\"sent\",\"timestamp\":\"1719375951\",\"recipient_id\":\"628998945103\",\"conversation\":{\"id\":\"c4df136ed43b293f8c5e3e2406261003\",\"expiration_timestamp\":\"1719386044\",\"origin\":{\"type\":\"marketing\"}},\"pricing\":{\"billable\":true,\"pricing_model\":\"CBP\",\"category\":\"marketing\"}}]},\"field\":\"messages\"}]}]}                                                                           | 200      |
      | sent               | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000003\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"6281285312957\",\"phone_number_id\":\"105922732603298\"},\"statuses\":[{\"id\":\"wamid.HBgNNjI4MTI4NTMxMjk1NxUCABEYEjdCRTY0MEQyRDUyQzk5MDI5RgA=\",\"status\":\"sent\",\"timestamp\":\"1719376044\",\"recipient_id\":\"6281285312957\",\"conversation\":{\"id\":\"c4df136ed43b293f8c5e3e2406261002\",\"expiration_timestamp\":\"1719386044\",\"origin\":{\"type\":\"marketing\"}},\"pricing\":{\"billable\":true,\"pricing_model\":\"CBP\",\"category\":\"marketing\"}}]},\"field\":\"messages\"}]}]}                                                                         | 200      |
      | sent               | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240626100000002\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"628998945103\",\"phone_number_id\":\"105922732603300\"},\"statuses\":[{\"id\":\"wamid.HBgMNjI4OTk4OTQ1MTAzFQIAERgSRkQ3RUYxQUY3MTREQTlFNjFCAA==\",\"status\":\"sent\",\"timestamp\":\"1719376044\",\"recipient_id\":\"628998945103\",\"conversation\":{\"id\":\"c4df136ed43b293f8c5e3e2406261003\",\"expiration_timestamp\":\"1719386044\",\"origin\":{\"type\":\"marketing\"}},\"pricing\":{\"billable\":true,\"pricing_model\":\"CBP\",\"category\":\"marketing\"}}]},\"field\":\"messages\"}]}]}                                                                          | 200      |
      | failed             | {\"entry\":[{\"id\":\"240626100000002\",\"changes\":[{\"field\":\"messages\",\"value\":{\"metadata\":{\"phone_number_id\":\"105922732603297\",\"display_phone_number\":\"6285788569440\"},\"statuses\":[{\"id\":\"wamid.HBgNNjI4OTUwODU5NTA1ORUCABEYEjQ0RUQyNTU3QjhFNjIyREM4NQA=\",\"errors\":[{\"code\":131026,\"title\":\"MessageUndeliverable.\"}],\"status\":\"failed\",\"timestamp\":\"1719376050\",\"recipient_id\":\"6285788569440\"}],\"messaging_product\":\"whatsapp\"}}]}],\"object\":\"whatsapp_business_account\"}                                                                                                                                                                                                                                    | 200      |

  @webhook_invalid_meta
  Scenario Outline: [M&M] Hit WebHook With Invalid Data
    Given Req invalid data("<type>", "<data>") Api WebHook M&M
    When Hit API WebHook M&M with Method "POST"
    Then Response "<httCode>" API WebHook with Method "POST"
    Examples:
      | type      | data                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | httCode |
      | delivered | {\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"240520200000001\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"6285788569440\",\"phone_number_id\":\"105922732603297\"},\"statuses\":[{\"id\":\"wamid.HBgNNjI4NTc3MzYwMDE5OBUCABEYEkRCOTk3NUU3MTk0N0U5NjAwXXX=\",\"status\":\"sent\",\"timestamp\":\"1716174373\",\"recipient_id\":\"6285788569440\",\"conversation\":{\"id\":\"c4df136ed43b293f8c5e3e2405202001\",\"expiration_timestamp\":\"1716274373\",\"origin\":{\"type\":\"marketing\"}},\"pricing\":{\"billable\":true,\"pricing_model\":\"CBP\",\"category\":\"marketing\"}}]},\"field\":\"messages\"}]}]} | 200     |

