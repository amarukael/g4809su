Feature: M&M static or dynamic button and custom body message

  @fill_valid_marketing_message
  Scenario Outline: [SOB M&M] Input Valid <messagingProduct> With Catagory <catagory> type <desc> -- <condition>
    Given I am in M&M Send Message Page
    * I choose "<messagingProduct>" on Messaging Product, "<catagory>" on Catagory, "<templateName>" on Choose Template Name
    * I fill Mandatory Field On "<type>" Template with Message Body "<messageBody>"
    When I choose Fill Mode "<fillMode>" and Fill Field To
    Then "<condition>" Input Fields Message
    Examples:
      | condition    | desc                         | type                     | fillMode     | messagingProduct  | catagory  | templateName                        | messageBody |
      | Successfully | Static Dynamic Button        | 3_button_static_dynamic  | manual       | Whatsapp          | Marketing | test_button_remid_03                | normal      |
      | Successfully | Static Dynamic Button        | 4_button_static_dynamic  | manual       | Whatsapp          | Marketing | test_button_remid_02                | normal      |
      | Successfully | Formatting Body Message Text | text                     | manual       | Whatsapp          | Marketing | testing_marketing_template_text_01  | format      |

#    Note:
#    untuk Formatting Body Message Text combinasi code(`) tidak bisa dicombine dengan yang lain
#    dari WA bakal gagal

  @fill_invalid_marketing_message
  Scenario Outline: [SOB M&M] Input Invalid <messagingProduct> With Catagory <catagory> type <desc>
    Given I am in M&M Send Message Page
    * I choose "<messagingProduct>" on Messaging Product, "<catagory>" on Catagory, "<templateName>" on Choose Template Name
    * I fill Mandatory Field with Invalid Data On "<type>" Template "<case>"
    * I choose Fill Mode "<fillMode>" and Fill Field To with Invalid Data "<case>"
    When Hit button Send Messages
    Then "<condition>" Input Fields Message
    Examples:
      | condition    | desc                   | type                     | fillMode     | messagingProduct  | catagory  | templateName          | case         |
      | Failed       | Static Dynamic Button  | 3_button_static_dynamic  | manual       | Whatsapp          | Marketing | test_button_remid_03  | wrong_input  |

  @send_message
  Scenario Outline: [SOB M&M] Send Message <messagingProduct> With Catagory <catagory> type <desc>
    Given I am in M&M Send Message Page
    * I choose "<messagingProduct>" on Messaging Product, "<catagory>" on Catagory, "<templateName>" on Choose Template Name
    * I fill Mandatory Field On "<type>" Template with Message Body "<messageBody>"
    * I choose Fill Mode "<fillMode>" and Fill Field To
    When Hit button Send Messages and Submit Send Message ("<condition>")
    Then "<condition>" Send Message
    Examples:
      | condition    | desc                   | type                    | fillMode     | messagingProduct  | catagory  | templateName                  | messageBody |
      | Successfully | Static Dynamic Button  | 3_button_static_dynamic | manual       | Whatsapp          | Marketing | test_button_remid_03          | format      |
