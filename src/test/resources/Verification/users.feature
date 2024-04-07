Feature: User verification

  Scenario: Create User with mandatory fields only
    Given create user with name "random", password "123" and save response
    And response status code is 201
    And save user from response
    Then user data and user from DB should be equals

    #Server ignores ID from client and creates auto generated
  Scenario: Create User with all fields
    Given create user with all fields and save response
    And response status code is 201
    Then user data and user from DB should be equals

  Scenario: Create two identical users
    Given create user with "random" name and save this user
    And make request for duplicate user and save response
    And response status code is 201
    Then validate that users list filtered by ID size equals to 1

    #Server allows special chars in username field
  Scenario Outline: Create User with a wrong username
    Given create user with a wrong name <name>
    Then response status code is 400
    Examples:
      | name   |
      | ""     |
      | " "    |
      | "!@#$" |

  Scenario Outline: Create User with a wrong password
    Given create user with a wrong password <password>
    Then response status code is 400
    Examples:
      | password |
      | ""       |
      | " "      |
