Feature: M&M Send Message

  @navigate_m&m_send_message_page
  Scenario Outline: [SOB M&M] Navigate into M&M Send Message Page
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to M&M Send Message
    Examples:
      | condition    | username            |
      | Successfully | ryormd              |
      | Failed       | pettycashpartner    |

  @get_list_template_by_partner
  Scenario Outline: [SOB M&M] Get List Template Message By Partner <partner>
    Given I am in M&M Send Message Page with "<username>"
    When I choose "<messageProduct>" on Messaging Product, "<catagory>" on Catagory
    Then I get list Template
    Examples:
      | condition    | username    | partner | messageProduct  | catagory  |
      | Successfully | ryormd      | Remid   | Whatsapp        | Marketing |
#      | Successfully | ryosls      | SLS     | Whatsapp        | Marketing |
      | Successfully | aksesmu     | AksesMu | Whatsapp        | Marketing |

#  Note:
#    ryosls for dev
#    aksesmu for stg

  @open_template
  Scenario Outline: [SOB M&M] Open Template Message <messageProduct> With Catagory <catagory> type <desc>
    Given I am in M&M Send Message Page
    And I choose "<messageProduct>" on Messaging Product, "<catagory>" on Catagory
    When I choose "<templateName>" on Template Name
    Then "<condition>" open format Template Messaging
    Examples:
      | condition     | desc                      | messageProduct  | catagory  | templateName                            |
#      | Successfully  | Image                     | Whatsapp        | Marketing | testing_marketing_template_1            |
#      | Successfully  | Text                      | Whatsapp        | Marketing | testing_marketing_template_text_01      |
#      | Successfully  | Video                     | Whatsapp        | Marketing | testing_marketing_template_video_01     |
      | Successfully  | Document                  | Whatsapp        | Marketing | testing_marketing_template_document_01  |
#      | Successfully  | Text Empty                | Whatsapp        | Marketing | consent_marketing_remid                 |
#      | Successfully  | Static Dynamic Button     | Whatsapp        | Marketing | test_button_remid_03                    |
#      | Successfully  | Static Dynamic Button     | Whatsapp        | Marketing | test_button_remid_02                    |
#      | Failed        | Whatsapp        | Marketing | testing_marketing_template_empty  | # impossible kata FE dan BE

  @fill_valid_marketing_message
  Scenario Outline: [SOB M&M] Input Valid <messagingProduct> With Catagory <catagory> type <desc>
    Given I am in M&M Send Message Page
    * I choose "<messagingProduct>" on Messaging Product, "<catagory>" on Catagory, "<templateName>" on Choose Template Name
    * I fill Mandatory Field On "<type>" Template with Message Body "<messageBody>"
    When I choose Fill Mode "<fillMode>" and Fill Field To
    Then "<condition>" Input Fields Message
    Examples:
      | condition    | desc                  | type                    | fillMode    | messagingProduct | catagory  | templateName                           | messageBody |
      | Successfully | Image                 | image                   | manual      | Whatsapp         | Marketing | testing_marketing_template_1           | normal      |
      | Successfully | Image                 | image                   | bulk_upload | Whatsapp         | Marketing | testing_marketing_template_1           | normal      |
      | Successfully | Text                  | text                    | manual      | Whatsapp         | Marketing | testing_marketing_template_text_01     | normal      |
#      | Successfully | Text                   | text                    | bulk_upload  | Whatsapp          | Marketing | testing_marketing_template_text_01     | normal      |
      | Successfully | Video                 | video                   | manual      | Whatsapp         | Marketing | testing_marketing_template_video_01    | normal      |
      | Successfully | document              | document                | manual      | Whatsapp         | Marketing | testing_marketing_template_document_01 | normal      |
#      | Successfully | Text Empty             | text_empty              | manual       | Whatsapp          | Marketing | testing_marketing_template_empty       | normal      |
      | Successfully | Text Empty            | text_empty              | manual      | Whatsapp         | Marketing | consent_marketing_remid                | normal      |
#      | Successfully | desc                   | text_empty              | bulk_upload  | Whatsapp          | Marketing | consent_marketing_remid                | normal      |
      | Successfully | Static Dynamic Button | 3_button_static_dynamic | manual      | Whatsapp         | Marketing | test_button_remid_03                   | normal      |
      | Successfully | Static Dynamic Button | 4_button_static_dynamic | manual      | Whatsapp         | Marketing | test_button_remid_02                   | normal      |

  @fill_invalid_marketing_message
  Scenario Outline: [SOB M&M] Input Invalid <messagingProduct> With Catagory <catagory> type <desc>
    Given I am in M&M Send Message Page
    * I choose "<messagingProduct>" on Messaging Product, "<catagory>" on Catagory, "<templateName>" on Choose Template Name
    * I fill Mandatory Field with Invalid Data On "<type>" Template "<case>"
    * I choose Fill Mode "<fillMode>" and Fill Field To with Invalid Data "<case>"
    When Hit button Send Messages
    Then "<condition>" Input Fields Message
    Examples:
      | condition    | desc                   | type                     | fillMode     | messagingProduct  | catagory  | templateName                           | case         |
      | Failed       | Empty Field            | image                    | manual       | Whatsapp          | Marketing | testing_marketing_template_1           | empty_field  |
      | Failed       | Image                  | image                    | manual       | Whatsapp          | Marketing | testing_marketing_template_1           | wrong_input  |
      | Failed       | Image                  | image                    | bulk_upload  | Whatsapp          | Marketing | testing_marketing_template_1           | wrong_input  |
      | Failed       | Text                   | text                     | manual       | Whatsapp          | Marketing | testing_marketing_template_text_01     | wrong_input  |
      | Failed       | Video                  | video                    | manual       | Whatsapp          | Marketing | testing_marketing_template_video_01    | wrong_input  |
      | Failed       | Document               | document                 | manual       | Whatsapp          | Marketing | testing_marketing_template_document_01 | wrong_input  |
      | Failed       | Static Dynamic Button  | 3_button_static_dynamic  | manual       | Whatsapp          | Marketing | test_button_remid_03                   | wrong_input  |

  @send_message
  Scenario Outline: [SOB M&M] Send Message <messagingProduct> With Catagory <catagory> type <desc>
    Given I am in M&M Send Message Page
    * I choose "<messagingProduct>" on Messaging Product, "<catagory>" on Catagory, "<templateName>" on Choose Template Name
    * I fill Mandatory Field On "<type>" Template with Message Body "<messageBody>"
    * I choose Fill Mode "<fillMode>" and Fill Field To
    When Hit button Send Messages and Submit Send Message ("<condition>")
    Then "<condition>" Send Message
    Examples:
      | condition      | desc                  | type                    | fillMode    | messagingProduct | catagory  | templateName                           | messageBody |
      | Successfully   | Image                 | image                   | manual      | Whatsapp         | Marketing | testing_marketing_template_1           | normal      |
      | Successfully   | Video                 | video                   | manual      | Whatsapp         | Marketing | testing_marketing_template_video_01    | normal      |
      | Successfully   | Document              | document                | manual      | Whatsapp         | Marketing | testing_marketing_template_document_01 | normal      |
      | Successfully   | Static Dynamic Button | 3_button_static_dynamic | manual      | Whatsapp         | Marketing | test_button_remid_03                   | format      |
      | Successfully   | Text Empty            | text_empty              | manual      | Whatsapp         | Marketing | consent_marketing_remid                | normal      |
      | Successfully   | Text Empty            | text_empty              | bulk_upload | Whatsapp         | Marketing | consent_marketing_remid                | normal      |
#      | Failed       | Image                 | image                   | manual   | Whatsapp         | Marketing | testing_marketing_template_1           | normal      |
      | Failed Url     | Text Empty            | text_empty              | manual      | Whatsapp         | Marketing | consent_marketing_remid                | normal      |
      | Failed Version | Text Empty            | text_empty              | manual      | Whatsapp         | Marketing | consent_marketing_remid                | normal      |

#    Note
#    Test Case Failed pertama untuk error url Meta
#    Test Case Failed kedua untuk error version Meta