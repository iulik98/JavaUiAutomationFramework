Feature: Login Feature Test Suite

  Background:
    Given The "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" is accessed

  @Regression
  Scenario Outline: An error message is displayed when login is performed with invalid <attribute>
    And the following data is entered into the login form:
      | <email>    |
      | <password> |
    When loginButton is clicked
    Then the following error messages are displayed:
      | Warning: No match for E-Mail Address and/or Password. |
    Examples:
      | attribute | email         | password |
      | email     | fi@asda.com   | asdass   |
      | password  | admin@aaa.com | asasdasd |

  @Regression
  Scenario Outline: A valid user is able to login into the system.
    And the following data is entered into the login form:
      | <email>    |
      | <password> |
    When loginButton is clicked
    Then the current URL contains the following keyword: "customer_token"
    Examples:
      | email                   | password |
      | yuki.okeefe@hotmail.com | admin    |