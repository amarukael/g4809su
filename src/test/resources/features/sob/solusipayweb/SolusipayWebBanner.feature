@SOB
Feature: SolusipayWeb Banner

  Background:
    Given I open SOB Website
    When I log in as admin
    And I click submit button
    Then I on a homepage
    When I navigate to SolusipayWeb Banner

  Scenario: Success Open FAQ Page
    Given I on a SolusipayWeb Banner Page