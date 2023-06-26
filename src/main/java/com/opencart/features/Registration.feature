Feature: Register Flow Feature Test Suite

  Scenario: Access the account page after successful registration
    Given HomePage is accessed
    And Register page is accessed from home page menu
    When the registration form is completed with valid random data
    And the privacy checkbox is enabled
    And continueButton is clicked
    Then the new page URL contains "success" keyword

    Scenario: User remains on Register Page when privacy policy checkbox is not clicked during the register flow
      Given HomePage is accessed
      And Register page is accessed from home page menu
      When the registration form is completed with valid random data
      And continueButton is clicked
      Then the new page URL contains "register" keyword