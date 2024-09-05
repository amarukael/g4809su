Feature: M&M Transaction

  @navigate_m&m_transaction_page
  Scenario Outline: [SOB M&M] Navigate into M&M Send Message Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to M&M Transaction

    Examples:
      | condition    | username         |
      | Successfully | ryormd           |
      | Failed       | pettycashpartner |

  @m&m_filter_by_field
  Scenario Outline: [SOB M&M] Show data Filter by Field
    Given I am in M&M Transaction Page
    * I hit Filter Button on M&M Transaction
    * I fill From Date ("24,06") and To Date ("30,06") on "SOB M&M Transaction"
    * I click field "<label>" and fill with "<value>" on M&M Transaction
    When I click apply button filter
    Then Datatable show data "M&M Transaction"

    Examples:
      | label             | value                              |
      | Transactions ID   | REMID6281285312957WA20240626151902 |
      | Reff Number       | REMIDWA20240624182355              |
      | Messaging Product | whatsapp                           |
      | status            | Success                            |

  @m&m_detail_transaction
  Scenario: [SOB M&M] Detail Transaction
    Given I am in M&M Transaction Page
    * I hit Filter Button on M&M Transaction
    * I fill From Date ("24,06") and To Date ("30,06") on "SOB M&M Transaction"
    When I click apply button filter
    Then Datatable show data "M&M Transaction"
    Given I click detail on row "1" on M&M Transaction

  @m&m_filter_by_status
  Scenario Outline: [SOB M&M] Show data Filter by Status Messages
    Given I am in M&M Transaction Page
    * I hit Filter Button on M&M Transaction
    * I fill From Date on Filter Field on M&M Transaction
    When I choose "<status>" From List Box Status on Filter Field on M&M Transaction
    Then I hit Apply Button on M&M Transaction

    Examples:
      | status  |
      | Success |
      | Read    |
      | Failed  |
      | Waiting |

  @m&m_export_transaction
  Scenario: [SOB M&M] Export file from M&M Transaction
    Given I am in M&M Transaction Page
    * I hit Filter Button on M&M Transaction
    When I fill From Date on Filter Field on M&M Transaction
    Then I hit Apply Button on M&M Transaction
    Then I hit Export Button on M&M Transaction
