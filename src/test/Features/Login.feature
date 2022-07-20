Feature: User Login for Automation Practice
  Background:
    Given I am on home page
    When I click SignIn button
    Then I should navigate to authentication page
    And  I should see Login Panel


  @smoke
  Scenario: Verify that user can login with valid credentials
    When I enter username as "anitharao@gmail.com" and Password as "12345"
    And click Login button
    Then I should navigate to My Account page

@regression
  Scenario Outline: : Verify that user cannot login with invalid credentials and see validation message
    When I enter username as <Email> and Password as <Password>
    And click Login button
    Then I should see error message as <Validation_Message>

    Examples:
      |Email|Password|Validation_Message|
      |anibach@gmail.com|12345|Authentication failed.|
      |anibachu@gmail.com|1234|Invalid password|
      ||12345|An email address required.|
      |anibach@gmail.com||Password is required.|
      |||An email address required.            |


