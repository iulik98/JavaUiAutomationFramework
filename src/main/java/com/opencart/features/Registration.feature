Feature: Register Flow Feature Test Suite

  Background:
    Given HomePage is accessed
    And Register page is accessed from home page menu

  @Smoke @Regression
  Scenario: Access the account page after successful registration
    When the registration form is completed with valid random data
    And "checkBoxPolicy" from "RegisterPage" is clicked
    And "continueButton" from "RegisterPage" is clicked
    Then the current URL contains the following keyword: "success"

  @Regression
  Scenario: User remains on Register Page when privacy policy checkbox is not clicked during the register flow
    When the registration form is completed with valid random data
    And "continueButton" from "RegisterPage" is clicked
    Then the current URL contains the following keyword: "register"

  @Regression
  Scenario: User remains on Register Page when continue button is not clicked during the register flow
    When the registration form is completed with valid random data
    And "checkBoxPolicy" from "RegisterPage" is clicked
    Then the current URL contains the following keyword: "register"

  @Regression
  Scenario Outline: Error messages are displayed when trying to register with invalid <attribute> data
    And the registration form is completed with the following data:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | <email>     |
      | password  | <password>  |
    When "continueButton" from "RegisterPage" is clicked
    Then the following error messages are displayed:
      | <attribute> must be between 1 and 32 characters! |
      | Warning: You must agree to the Privacy Policy!   |
    Examples:
      | attribute  | firstName                               | lastName                                | email  | password |
      | First Name | aaaaaaaaaaaasdddddddddddddddddassssssss | random                                  | random | randoM   |
      | Last Name  | random                                  | aaaaaaaaaaaasdddddddddddddddddassssssss | Random | raNdom   |

  @Regression
  Scenario Outline: Error messages are displayed when trying to register with invalid <attribute> data
    And the following fields from "RegisterPage" are populated with data:
      | firstNameField | <firstName> |
      | lastNameField  | <lastName>  |
      | emailField     | <email>     |
      | passField      | <password>  |
    When "continueButton" from "RegisterPage" is clicked
    Then the following error messages are displayed:
      | <attribute> must be between 1 and 32 characters! |
      | Warning: You must agree to the Privacy Policy!   |
    Examples:
      | attribute  | firstName                               | lastName                                | email  | password |
      | First Name | aaaaaaaaaaaasdddddddddddddddddassssssss | random                                  | random | randoM   |
      | Last Name  | random                                  | aaaaaaaaaaaasdddddddddddddddddassssssss | Random | raNdom   |