Feature: SolusipayWeb Transaction

  @navigate_to_solusipay_web_transaction
  Scenario Outline: Navigate into Solusipay Web Transaction -- "<condition>"
    Given I open SOB Website
    When I logged in with username "<username>"
    Then I "<condition>" navigate to SolusipayWeb Transaction
    Examples:
      | condition    | username            |
      | Successfully | admin               |
      | Failed       | pettycashpartner    |


  @success_filter_data_by_date
  Scenario: Success Filter data by date
    Given I on a SolusipayWeb Transaction Page
    * I hit Filter Button
    * I fill From Date on Filter Field
    * I fill To Date on Filter Field
    * I choose "ASM" partnerID on filter field
    * I choose "SUCCESS" status on filter field
    When I click apply button filter
    Then Data table show data Transaction Suspect

    @success_filter_data_by_fields
    Scenario Outline: Success filter data by "<fields>"
      Given I on a SolusipayWeb Transaction Page
      * I hit Filter Button
      * I fill From Date on Filter Field
      * I fill To Date on Filter Field
      * I choose "ASM" partnerID on filter field
      * I fill "<fields>" with "<value>"
      * I choose "SUCCESS" status on filter field
      When I click apply button filter
      Then Data table show data Transaction Suspect
      Examples:
      | fields           | value           |
      | Order ID         | 202403010000013 |
      | Customer ID      | 085738273748    |

    @remove_filter_data
    Scenario Outline: Remove filter data "<fields>"
      Given I on a SolusipayWeb Transaction Page
      * I hit Filter Button
      * I fill From Date on Filter Field
      * I fill To Date on Filter Field
      * I choose "ASM" partnerID on filter field
      * I fill "<fields>" with "<value>"
      * I choose "SUCCESS" status on filter field
      * I click apply button filter
      * I click filter button again
      When I click Remove Filter button
      Then Data table show data Transaction Suspect
      Examples:
        | fields           | value           |
        | Order ID         | 202403010000013 |
        | Customer ID      | 085738273748    |

      @verify_error_message_partnerid
      Scenario Outline: Verify Error Message partner id is empty
        Given I on a SolusipayWeb Transaction Page
        * I hit Filter Button
        * I fill From Date on Filter Field
        * I fill To Date on Filter Field
        * I fill "<fields>" with "<value>"
        * I choose "SUCCESS" status on filter field
        When I click apply button filter
        And "<condition>" error message partner id is empty
        Then Data table show data Transaction Suspect
        Examples:
          |condition  | fields           | value           |
          |Failed     | Order ID         | 202403010000013 |


