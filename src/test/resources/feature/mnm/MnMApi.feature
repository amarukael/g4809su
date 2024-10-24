Feature: M&M API Service

  @m&m_send_messages
  Scenario Outline: [M&M] Partner hit API Send Message
    Given Valid Request ("<reqBody>") Type "<type>" API Send Messages M&M
    When Partner Hit API Send Messages M&M
    Then Partner gets response "<rc>" API Send Messages M&M
    Examples:
      | type  | reqBody   | rc    |
      | slack | {\"messagingProduct\":\"Slack\",\"to\":\"C05TTDT9XTP\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"text\",\"additionalDataInfo\":{\"body\":\"Test Sample Text\"}}                                                                                 | 20000 |
      | email | {\"messagingProduct\":\"email\",\"to\":\"yudi.musthofa@ids.id\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"text\",\"additionalDataInfo\":{\"subject\":\"TESTING NOTIFY\",\"body\":\"Body Email\"}}                                               | 20000 |
      | wa    | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}} | 20000 |

  @m&m_invalid_send_messages
  Scenario Outline: [M&M] Partner hit API Send Message with invalid <param> <descTest>
    Given Invalid Request ("<reqBody>", "<param>", "<descTest>") Type "<type>" API Send Messages M&M
    When Partner Hit API Send Messages M&M
    Then Partner gets response "<rc>" API Send Messages M&M
    Examples:
      | type  | param                       | descTest | reqBody   | rc    |
      | wa    | originalPartnerReferenceNo  | empty    | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                     | 40004 |
      | wa    | recipient                   | wrong    | {\"messagingProduct\":\"wa\",\"to\":\"XX81285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                     | 40005 |
      | wa    | recipient                   | invalid  | {\"messagingProduct\":\"wa\",\"to\":\"6200000000000\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                     | 40106 |
      | wa    | urlMeta                     | wrong    | {\"messagingProduct\":\"wa\",\"to\":\"6200000000000\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                     | 50006 |
#      | wa    | xClientId                   | wrong    | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                    | 40134 |
      | wa    | signature                   | wrong    | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                     | 40134 |
      | wa    | typeMessaging               | wrong    | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"XXX\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                          | 40135 |
      | wa    | templateName                | wrong    | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid_xx\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                  | 40430 |
      | wa    | messagingProduct            | wrong    | {\"messagingProduct\":\"wa1\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                    | 40433 |
      | wa    | xExternalId                 | double   | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"?\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}                     | 40901 |
      | wa    | originalPartnerReferenceNo  | double   | {\"messagingProduct\":\"wa\",\"to\":\"6281285312957\",\"originalPartnerReferenceNo\":\"WA202405131020102027\",\"typeMessaging\":\"template\",\"additionalDataInfo\":{\"templateName\":\"consent_marketing_remid\",\"languageCode\":\"id\",\"componentBody\":{\"parameters\":[]}}}  | 40903 |