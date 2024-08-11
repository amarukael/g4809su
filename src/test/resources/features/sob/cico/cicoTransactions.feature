@SOB_CICO_Transaction
Feature: CICO Transaction

  @navigate_CICO_transaction
  Scenario Outline: Navigate into CICO Transaction Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to CICO Transaction
    Examples:
      | condition    | username |
      | Successfully | adminqa2 |
      | Failed       | rhesa    |

  @CICO_Transaction_filter_by_date
  Scenario: Filter data by Date -- Successfully
    Given I am in Menu "CICO" and Sub-Menu "Transaction List"
    * I click filter button on "CICO Transaction"
    * I fill From Date ("01,01") and To Date ("07,01") on "CICO Transaction"
    When I click apply button filter
    Then Datatable show data "CICO Transaction"

  @CICO_Transaction_filter_by_data
  Scenario Outline: Filter data by Data "<field>"
    Given I am in Menu "CICO" and Sub-Menu "Transaction List"
    * I click filter button on "CICO Transaction"
    * I fill From Date ("01,01") and To Date ("07,01") on "CICO Transaction"
    * I Click Field "<field>" and Fill With "<value>" on CICO Transaction
    When I click apply button filter
    Then Datatable show data "CICO Transaction"
    Examples:
      | field                   | value                |
      | BCI Transaction ID      | IBTT0561704438438174 |
      | Partner Transaction ID  | IDM20240105160047828 |
      | Partner Name            | IDM                  |
      | Merchant Transaction ID | TTPB2401051526440102 |
      | Merchant Name           | INDOMARET            |
      | Customer ID             | 085208345677         |
      | Customer Name           | RIYANO CARDIN        |
      | Token                   | 1008811128           |
      | Transaction Type        | CASHOUT              |
      | Nominal                 | 100000               |
      | Status                  | Success              |

  @CICO_Transaction_filter_by_data_All_field
  Scenario: Filter data by Data All Field
    Given I am in Menu "CICO" and Sub-Menu "Transaction List"
    * I click filter button on "CICO Transaction"
    * I fill From Date ("01,01") and To Date ("07,01") on "CICO Transaction"
    * I fill all fields on filter CICO Transaction
    When I click apply button filter
    Then Datatable show data "CICO Transaction"