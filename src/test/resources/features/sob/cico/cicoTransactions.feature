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
      # | Failed       | rhesa    |

  @CICO_Transaction_filter_by_date
  Scenario: [SOB][CICO][FE] Transaction List - Show list Transaction
    Given I am in Menu "CICO" and Sub-Menu "Transaction List"
    * I click filter button on "CICO Transaction"
    * I fill From Date ("01,06") and To Date ("30,06") on "CICO Transaction"
    When I click apply button filter
    Then Datatable show data "CICO Transaction"

  @CICO_Transaction_filter_by_data
  Scenario Outline: [SOB][CICO][FE] Transaction List - Filter by Field
    Given I am in Menu "CICO" and Sub-Menu "Transaction List"
    * I click filter button on "CICO Transaction"
    * I fill From Date ("01,06") and To Date ("30,06") on "CICO Transaction"
    Then I Click Field "<field>" and Fill With "<value>" on CICO Transaction
    When I click apply button filter
    Then Datatable show data "CICO Transaction"

    Examples:
      | field                   | value                |
      | BCI Transaction ID      | IBTT1231719319496569 |
      | BCI Transaction ID      | IBTT                 |
      | Partner Transaction ID  | IDM20240625162503627 |
      | Partner Transaction ID  | IDM                  |
      | Partner Name            | I                    |
      | Merchant Transaction ID | TBY02406201302420103 |
      | Merchant Transaction ID | TBY                  |
      | Merchant Name           | INDOMARET            |
      | Merchant Name           | INDO                 |
      | Customer ID             |          08119703200 |
      | Customer ID             |              9703200 |
      | Customer Name           | MEJ HOA              |
      | Customer Name           | MEJ                  |
      | Token                   |           8377227888 |
      | Token                   |                 8377 |
      | Transaction Type        | CASHOUT              |
      | Transaction Type        | CASH                 |
      | Nominal                 |               200000 |
      | Nominal                 | Rp. 100.000          |
      | Nominal                 |                  200 |
      | Nominal                 |                  000 |
      | Status                  | Success              |
      | BCI Transaction ID      | random,30            |
      | BCI Transaction ID      | random,31            |
      | Partner Transaction ID  | random,30            |
      | Partner Transaction ID  | random,31            |
      | Partner Name            | random,50            |
      | Partner Name            | random,51            |
      | Merchant Transaction ID | random,30            |
      | Merchant Transaction ID | random,31            |
      | Merchant Name           | random,25            |
      | Merchant Name           | random,26            |
      | Customer ID             | random,25            |
      | Customer ID             | random,26            |
      | Customer Name           | random,50            |
      | Customer Name           | random,51            |
      | Token                   | random,30            |
      | Token                   | random,31            |
      | Transaction Type        | random,25            |
      | Transaction Type        | random,26            |
      | Nominal                 | random,18            |
      | Nominal                 | random,19            |
      | Status                  | Failed               |

  @CICO_Transaction_filter_by_data_All_field
  Scenario Outline: [SOB][CICO][FE] Transaction List - Filter data by fill all data field
    Given I am in Menu "CICO" and Sub-Menu "Transaction List"
    * I click filter button on "CICO Transaction"
    * I fill From Date ("01,06") and To Date ("30,06") on "CICO Transaction"
    Then I Click Field "BCI Transaction ID" and Fill With "<bciTransId>" on CICO Transaction
    Then I Click Field "Partner Transaction ID" and Fill With "<partnerTransId>" on CICO Transaction
    Then I Click Field "Partner Name" and Fill With "<partnerName>" on CICO Transaction
    Then I Click Field "Merchant Transaction ID" and Fill With "<merchTransId>" on CICO Transaction
    Then I Click Field "Merchant Name" and Fill With "<merchName>" on CICO Transaction
    Then I Click Field "Customer ID" and Fill With "<custId>" on CICO Transaction
    Then I Click Field "Customer Name" and Fill With "<custNum>" on CICO Transaction
    Then I Click Field "Token" and Fill With "<token>" on CICO Transaction
    Then I Click Field "Transaction Type" and Fill With "<transType>" on CICO Transaction
    Then I Click Field "Nominal" and Fill With "<nominal>" on CICO Transaction
    Then I Click Field "Status" and Fill With "<status>" on CICO Transaction
    When I click apply button filter
    Then Datatable show data "CICO Transaction"

    Examples:
      | bciTransId           | partnerTransId       | partnerName | merchTransId         | merchName | custId       | custNum                       | token      | transType | nominal   | status  |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      |                      | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 |                      | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 |             | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         |                      | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 |           | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET |              | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 |                               | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI |            | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 |           |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |           | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 |         |
      | random,30            | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | random,30            | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | random,50   | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | random,30            | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | random,35 | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | random,25    | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | random,50                     | 9555892771 | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | random,30  | CASHOUT   |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | random,25 |    100000 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   | random,18 | Success |
      | IBTT7381717550653865 | IDM20240605082646755 | IDM         | TTPB2406050826440101 | INDOMARET | 085894035505 | FAJAR PUSPITA RADIANSYA PUTRI | 9555892771 | CASHOUT   |    100000 | Failed  |
