Feature: Login Feature Test Suite

  Background:
    Given The "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" is accessed

  @Regression
  Scenario Outline: An error message is displayed when login is performed with invalid <attribute>
    And the following fields from "LoginPage" are populated with data:
      | emailAddress  | <emailAddress>  |
      | passwordLogIn | <passwordLogIn> |
    When "logInButton" from "LoginPage" is clicked
    Then the following error messages are displayed:
      | Warning: No match for E-Mail Address and/or Password. |
    Examples:
      | attribute | emailAddress  | passwordLogIn |
      | email     | fi@asda.com   | asdass        |
      | password  | admin@aaa.com | asasdasd      |

  @Regression
  Scenario Outline: A valid user is able to login into the system.
    And the following fields from "LoginPage" are populated with data:
      | emailAddress  | <emailAddress>  |
      | passwordLogIn | <passwordLogIn> |
    When "logInButton" from "LoginPage" is clicked
    Then the current URL contains the following keyword: "customer_token"
    Examples:
      | emailAddress            | passwordLogIn |
      | yuki.okeefe@hotmail.com | admin         |