@SOB_Dana_Transaction_Suspect
Feature: Dana Transaction Suspect

  @navigate_Dana_transsuspect
  Scenario Outline: Navigate into Dana Transaction Suspect -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to Dana Transaction Suspect
    Examples:
      | condition    | username |
      | Successfully | adminqa  |

  @dana_transsuspect_filter_by_date
  Scenario: Filter data by Date -- Successfully
    Given I am in Dana Transaction Suspect
    * I hit Filter Button on Dana Transaction Suspect
    * I fill From Date ("08,01") and To Date ("17,01") on Dana Transaction Suspect
    When I hit Apply Button on Dana Transaction Suspect
    Then Dana Datatable show data Transaction Suspect

  @dana_transsuspect_show_status_and_action
  Scenario: Show data in coloum Update Date and Update By -- Successfully
    Given I am in Dana Transaction Suspect
    * I hit Filter Button on Dana Transaction Suspect
    * I fill From Date ("08,01") and To Date ("17,01") on Dana Transaction Suspect
    When I hit Apply Button on Dana Transaction Suspect
    Then Dana Display value in Column Status and Action in data Transaction Suspect

  @dana_transsuspect_filter_data
  Scenario Outline: Filter data by Date and Field <fields> -- Successfully
    Given I am in Dana Transaction Suspect
    * I hit Filter Button on Dana Transaction Suspect
    * I fill From Date ("<fromDate>") and To Date ("<toDate>") on Dana Transaction Suspect
    * I choose "<fields>" and fill with "<value>" on Dana Transaction Suspect
    When I hit Apply Button on Dana Transaction Suspect
    Then Dana Datatable show data Transaction Suspect
    Examples:
      | fields         | fromDate | toDate | value       |
      | Acquirement Id | 08,01    | 17,01  | DUMMY123    |
      | Product Name   | 08,01    | 17,01  | ASRJWS      |
      | Customer ID    | 08,01    | 17,01  | 68001889322 |
      | Ref            | 08,01    | 17,01  | DUMMY123    |
      | Amount         | 08,01    | 17,01  | 200000      |

  @dana_changing_Suspect_Transaction_to_Success_and_Failed
  Scenario Outline: Changing Suspect Transaction to "<btn>"
    Given I am in Dana Transaction Suspect
    * I hit Filter Button on Dana Transaction Suspect
    * I fill From Date ("08,01") and To Date ("17,01") on Dana Transaction Suspect
    * I choose "Ref" and fill with "<tracking>" on Dana Transaction Suspect
    When I hit Apply Button on Dana Transaction Suspect
    Then I Hit Button Force "<btn>" and Fill "<field>" with Value "<value>"
    Then I Hit Edit Button and Show Alert
    Examples:
      | tracking | btn     | field         | value        |
      | DUMMY212 | APPROVE | Receipt Code  | DUMMY12345RC |
      | DUMMY123 | REJECT  | Reject Reason | 99           |

  @Show_Info_Invalid_or_Limit_Date
  Scenario Outline: Show Info Invalid or Limit Date
    Given I am in Dana Transaction Suspect
    * I hit Filter Button on Dana Transaction Suspect
    * I fill From Date ("<date>") on Dana Transaction Suspect
    Then Show Info "<type>" Date
    Examples:
      | type    | date  |
      | Limit   | 01,01 |
      | Invalid | 12,12 |

  @Short_Data_via_Header_Datatable
  Scenario Outline: Short Data via Header Datatable
    Given I am in Dana Transaction Suspect
    * I hit Filter Button on Dana Transaction Suspect
    * I fill From Date ("08,01") and To Date ("17,01") on Dana Transaction Suspect
    When I hit Apply Button on Dana Transaction Suspect
    Then I Hit Header "<value>" Datatable to Sort "<type>"
    Examples:
      | value          | type      |
      | No             | ASC & DSC |
      | Settle Date    | ASC & DSC |
      | Tracking Reff  | ASC & DSC |
      | Acquirement Id | ASC & DSC |
      | Customer ID    | ASC & DSC |
      | Product Name   | ASC & DSC |
      | Amount         | ASC & DSC |
      | RC             | ASC & DSC |
      | RC Desc        | ASC & DSC |