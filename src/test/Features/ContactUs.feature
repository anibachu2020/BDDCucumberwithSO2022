Feature: Verify that user can contact customer services

  @ContactUs
  Scenario: User contact Customer Service using Contact Us tab
    Given User is on home page
    And User can see Contact Us tab
    When User clicks Contact Us tab
    Then User should navigate to Customer Service Page
    And User can see Send a Message details
    When User selects subject heading as Customer Service
    And User enters Email as "anitharao@gmail.com" and Order reference as "AB12345"
    And User clicks on choose file
    And User enters message as "Item damaged" in message box
    And clicks on submit
    Then User should see message sent confirmation