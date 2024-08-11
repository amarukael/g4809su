Feature: SolusipayWeb Partner Setting

  @navigate_to_solusipay_web_partner_setting
  Scenario Outline: Navigate into Solusipay Web Partner Setting -- "<condition>"
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to SolusipayWeb Partner Setting
    Examples:
      | condition    | username            |
      | Successfully | admin               |
      | Failed       | pettycashpartner    |

  @success_add_data_partner
  Scenario Outline: Success Add Data Partner
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Add Partner Button on Partner Setting page
    * I fill Partner Id "<partnerId>"
    * I fill Partner Name "<partnerName>"
    * I fill Admin Fee "<adminFee>"
    * I fill Session Time "<sessionTime>"
    * I choose "<status>" status on add partner
    When I click Add Data
    Then I "<condition>" Add Data Partner
    Examples:
      | condition    | partnerId | partnerName | adminFee | sessionTime | status |
      | Successfully | TM5       | TAMTAM5     | 1000     | 60          | Active |

  @failed_add_data_partner
  Scenario Outline: Failed Add Data Partner -- "<condition>"
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Add Partner Button on Partner Setting page
    * I fill Partner Id "<partnerId>"
    When I click Add Data
    Then I "<condition>" Add Data Partner
    Examples:
      |condition|partnerId|
      |Failed   |TM       |

  @succes_filter_data_partner_setting
  Scenario: Success Filter Data Partner Setting
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Filter Button on Partner Setting page
    * I choose "ASM" partner id on filter field partner setting
    When I click apply button filter
    Then Data table show data Partner Setting Suspect

  @navigate_to_detail_partner_list
  Scenario: Navigate to detail partner list
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Filter Button on Partner Setting page
    * I choose "TM" partner id on filter field partner setting
    * I click apply button filter
    When I click button detail partner list
    Then I on a Detail Partner Setting Page

  @success_change_data_partner
  Scenario Outline: Success Change data partner
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Filter Button on Partner Setting page
    * I choose "TM" partner id on filter field partner setting
    * I click apply button filter
    * I click button detail partner list
    * I click button change data on partner detail page
    * I want to edit data session time on partner detail "<sessionTime>"
    When I click button save data on partner detail page
    Then I success change data on partner detail page
    Examples:
    |sessionTime  |
    |59           |

  @navigate_to_biller_setting_on_partner_detail
  Scenario: Navigate to Biller Setting on Partner Detail
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Filter Button on Partner Setting page
    * I choose "TM" partner id on filter field partner setting
    * I click apply button filter
    When I click button detail partner list
    Then I scroll to biller setting on partner detail page

  @add_biller_partner_detail_page
  Scenario: Success Add Biller Setting on Partner Detail
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Filter Button on Partner Setting page
    * I choose "TM" partner id on filter field partner setting
    * I click apply button filter
    * I click button detail partner list
    * I scroll to biller setting on partner detail page
    * I click button add biller on partner detail page
    * I choose biller name "12" on add biller
    When I click Add Data
    Then I can see data table after add new biller

  @filter_data_on_biller_setting
  Scenario Outline: Successs filter data biller by "<billerId>"
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Filter Button on Partner Setting page
    * I choose "TM" partner id on filter field partner setting
    * I click apply button filter
    * I click button detail partner list
    * I scroll to biller setting on partner detail page
    * I hit Filter Button on Partner Setting page
    * I fill "<billerId>" biller id on filter biller setting
    When I click apply button filter
    Then I can see data table after filter data
    Examples:
    |billerId|
    |313     |

  @change_biller_status_on_biller_setting
  Scenario Outline: Success change biller status
    Given I on a SolusipayWeb Partner Setting Page
    * I hit Filter Button on Partner Setting page
    * I choose "TM" partner id on filter field partner setting
    * I click apply button filter
    * I click button detail partner list
    * I scroll to biller setting on partner detail page
    * I hit Filter Button on Partner Setting page
    * I fill "<billerId>" biller id on filter biller setting
    * I click apply button filter
    * I click action button status on data table biller setting
    When I click button yes
    Then I can see data table after change status
    Examples:
      |billerId|
      |313     |

    @delete_biller_on_data_table_biller_setting
    Scenario Outline: Delete biller on data table biller setting
      Given I on a SolusipayWeb Partner Setting Page
      * I hit Filter Button on Partner Setting page
      * I choose "TM" partner id on filter field partner setting
      * I click apply button filter
      * I click button detail partner list
      * I scroll to biller setting on partner detail page
      * I hit Filter Button on Partner Setting page
      * I fill "<billerId>" biller id on filter biller setting
      * I click apply button filter
      * I click icon delete for delete biller
      When I click button yes
      Then I can see data table delete biller
      Examples:
        |billerId|
        |12     |