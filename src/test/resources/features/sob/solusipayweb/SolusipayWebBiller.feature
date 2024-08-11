@SOB
Feature: SolusipayWeb Biller


  @navigate_to_solusipay_web_Biller
  Scenario Outline: Navigate into Solusipay Web Biller -- "<condition>"
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to Solusipay Web Biller
    Examples:
      |condition   |username        |
      |Successfully|pettycashpartner           |
      |Failed      |admin|

  @success_filter_data_biller
  Scenario Outline: Success fillter data biller
    Given I on a SolusipayWeb Biller
    * I Hit Filter Button on Biller page
    * I fill "<billerId>" Biller Id on Filter field
    When I click apply button filter
    Then I can see data table biller list
    Examples:
    |billerId|
    |12      |

  @Success_remove_filter_data_biller
  Scenario Outline: Success Remove fillter data biller
    Given I on a SolusipayWeb Biller
    * I Hit Filter Button on Biller page
    * I fill "<billerId>" Biller Id on Filter field
    * I click apply button filter
    * I can see data table biller list
    * I click filter button again on biller
    When I click Remove Filter button
    Then I can see data table biller list
    Examples:
      |billerId|
      |12      |

  @Success_Add_data_biller
  Scenario Outline: Add Data Biller -- <condition>
    Given I on a SolusipayWeb Biller
    * I click add biller button
    * I fill "<billerId>" biller id on form add data biller
    * I fill "<billerName>" biller name on form add data biller
    * I choose "<type>" type biller on form add data biller
    * I choose "<status>" status on form add data biller
    When I click add data button on form add data biller
    Then I verify add data biller was "<condition>"
    Examples:
    |condition    |billerId |billerName   |type       |status     |
    |Successfully |799      |S5 Tank      |Tagihan    |Active     |
    |Failed       |aBcDeFg  |TestAjaDulu  |Tagihan    |Active     |

  @add_data_without_filling_in_any_fields
  Scenario: add data without filling in any fields
    Given I on a SolusipayWeb Biller
    * I click add biller button
    When I click add data button on form add data biller
    Then I verify error message field on form add data biller

  @change_status_biller
  Scenario Outline: "<condition>" Change Status Biller
    Given I on a SolusipayWeb Biller
    * I Hit Filter Button on Biller page
    * I fill "<billerId>" Biller Id on Filter field
    * I click apply button filter
    * I click button change status biller
    When I click button "<confirmStatus>" confirm status
    Then I want to verify to see data table biller "<condition>"
    Examples:
      |condition    |billerId |confirmStatus  |
      |Successfully |444      |YES            |
      |Successfully |444      |NO             |

  @edit_data_biller
  Scenario Outline: "<condition>" Edit data biller
    Given I on a SolusipayWeb Biller
    * I Hit Filter Button on Biller page
    * I fill "<billerId>" Biller Id on Filter field
    * I click apply button filter
    * I click icon edit on biller
    When I edit field "<billerName>" biller name on form edit biller
    * I click button edit on form edit biller
    Then I want to verify to see data table biller "<condition>"
    Examples:
    |condition    |billerId|billerName    |
    |Successfully |444     |NanaKangKutuk |
    |Failed       |444     |              |

