@SOB
Feature: SolusipayWeb FAQ

  Background:
    Given I open SOB Website
    When I log in as admin
    And I click submit button
    Then I on a homepage
    When I navigate to SolusipayWeb FAQ

  @TC1
  Scenario: Success Open FAQ Page
    Given I on a SolusipayWeb FAQ Page
    Then I rename document "TC1"

  @TC2
  Scenario: Go To Next Page
    Given I click filter button
    When I click apply button filter
    And I click button next Page
    Then I on a SolusipayWeb FAQ Page two
    Then I rename document "TC2"


  Scenario: Success Filter
    Given I click filter button
    When I click apply button filter
    Then I can see FAQ SolusipayWeb data is show
    Then I rename document "TC3"

  @TC4
  Scenario: Remove Filter
    Given I click filter button
    And I choose "ADN" partnerID on filter field
    When I click apply button filter
    Then I can see FAQ SolusipayWeb data is show
    When I click filter button again
    And I click Remove Filter button
    Then I can see all data FAQ SolusipayWeb is show
    Then I rename document "TC4"


  Scenario: Success Add Data
    Given I click add data button
    When I choose partner FAQ dropdown
    And I write some content on FAQ
    When I click Save Data Button
    Then I can see Success Notification Snackbar
    Then I rename document "TC5"

  @TC6
  Scenario: Blank Partner
    Given I click add data button
    When I click Save Data Button
    Then I can see warning on Partner Field
    Then I rename document "TC6"

  Scenario: Duplicate Partner
    Given I click add data button
    When I choose partner FAQ dropdown
    And I write some content on FAQ
    When I click Save Data Button
    Then I can see Duplicate Partner Notification Snackbar
    Then I rename document "TC7"

  Scenario: Success Edit with Toggle Active/Inactive
    Given I click filter button
    When I click apply button filter
    Then I switch status on first data FAQ
    When I click Yes for Confirmation
    Then I can see Success Notification Snackbar
    Then I rename document "TC8"

  Scenario: Edit FAQ Content
    Given I click filter button
    When I click apply button filter
    Then I click pencil icon for Edit
    When I edit some content on FAQ
    When I click Save Data Button
    Then I can see Success Notification Snackbar
    Then I rename document "TC9"

  Scenario: Edit Active/Inactive
    Given I click filter button
    When I click apply button filter
    Then I click pencil icon for Edit
    When I change the status
    When I click Save Data Button
    Then I can see Success Notification Snackbar
    Then I rename document "TC10"

  @TC11
  Scenario: Edit Partner
    Given I click filter button
    When I click apply button filter
    Then I click pencil icon for Edit
    When I want change partner but the field is inactive
    Then I rename document "TC11"

  Scenario: Success Delete
    Given I click filter button
    When I click apply button filter
    Then I click trash icon for delete on nineth data FAQ
    When I click Yes for Confirmation
    Then I can see Success Notification Snackbar
    Then I rename document "TC12"