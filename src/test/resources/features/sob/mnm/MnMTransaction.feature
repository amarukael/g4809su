Feature: M&M Transaction

  @navigate_m&m_transaction_page
  Scenario Outline: [SOB M&M] Navigate into M&M Send Message Page -- <condition>
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to M&M Transaction
    Examples:
      | condition    | username            |
      | Successfully | ryormd              |
      | Failed       | pettycashpartner    |

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