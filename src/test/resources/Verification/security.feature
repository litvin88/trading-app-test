Feature: Security verification

  Scenario: Create Security with mandatory fields only
    Given create security with index "COMP" and save response
    Then response status code is 201

  Scenario: Create Security with all fields
    Given create security with id "64650924-e9bc-4f7f-827b-cf77958f2baf", index "DAX" and save response
    Then response status code is 201

    #Server ignores id from client and generates own id
  Scenario Outline: Create Security with a wrong id
    Given create security with id <id>, index "DAX" and save response
    Then response status code is 400
    Examples:
      | id    |
      | ""    |
      | " "   |
      | "!@#" |

    #Server allows special chars in security name field
  Scenario Outline: Create Security with a wrong name
    Given create security with id "64650924-e9bc-4f7f-827b-cf77958f2baf", index <name> and save response
    Then response status code is 400
    Examples:
      | name  |
      | ""    |
      | " "   |
      | "!@#" |