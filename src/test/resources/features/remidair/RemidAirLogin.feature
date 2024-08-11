Feature: Remid Air Login

  Scenario:
    Given I open RemidAir Website
    When I log in as admin
    And I click submit button
    Then I on a homepage
