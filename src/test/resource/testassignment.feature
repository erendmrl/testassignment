Feature: Login test on thedemosite.co.uk and API tests via Rest-Assured and XmlPath. A user is created beforehand

  Scenario: Login

    When I navigated to thedemosite login page
    Then the response HTTP status code should be 200
    And the page title should be "Login example page to test the PHP MySQL online system"
    And the page title text should be "4. Login"
    And the page info text should be correct
    And the page status text should be correct
    Then I enter a proper username
    And I enter a proper password
    When I click login
    Then the info message must be success
    But when I enter a wrong username and password
    When I click login
    Then the info message must fail, I write the time to the file


  Scenario Outline: Login try with invalid username and password
    And I check for username-password minimum boundary conditions, when I try to enter an invalid username "<username>" or password "<password>"
    Then alert window must appear, I write the time to the file
    Examples:
      | username | password |
      | a        | testpass |
      | testuser | a        |


  Scenario: API tests with Rest-Assured and XmlPath, a user is created via UI beforehand just in case

    When I request to login with correct parameters via HTTP POST
    Then Response HTTP status code should be 200
    When I request to login with correct parameters via HTTP POST
    Then Response title should be "Login example page to test the PHP MySQL online system"
    When I request to login with correct parameters via HTTP POST
    Then Response xml should contain "**Successful Login**"
    When I request to login with correct parameters via HTTP POST
    When I request to add user with correct parameters
    Then the response title should be correct and response should contain the entered username
    When I request to login with incorrect parameters
    Then Response html should contain "**Failed Login**"







