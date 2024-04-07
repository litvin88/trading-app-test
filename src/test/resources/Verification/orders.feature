Feature: Negative Checks

  Background:
    Given one security "DAX" and one user "WhiteListed" exist

  Scenario Outline: Create correct Order with mandatory field only
    Then user "WhiteListed" puts a <type> order for security "DAX" with mandatory fields only
    Examples:
      | type   |
      | "sell" |
      | "buy"  |

  Scenario Outline: Create correct Order with all fields
    Then user "WhiteListed" puts a <type> order for security "DAX" with mandatory fields only
    Examples:
      | type   |
      | "sell" |
      | "buy"  |

  Scenario Outline: Create wrong Order with a wrong type
    Then user "WhiteListed" puts an order for security "DAX" with a wrong <field> and <value> for it
    Examples:
      | field  | value   |
      | "type" | " "     |
      | "type" | ""      |
      | "type" | "@#$%^" |
      | "type" | "123"   |
      | "type" | "loan"  |

    #Server allows empty and space in id field
  Scenario Outline: Create wrong Order with a wrong id
    Then user "WhiteListed" puts an order for security "DAX" with a wrong <field> and <value> for it
    Examples:
      | field | value                                  |
      | "id"  | " "                                    |
      | "id"  | ""                                     |
      | "id"  | "12f75303-c3af-4603-b075-c460081aa"    |
      | "id"  | "12f75303-c3af-4603-b075-c460081aa!@#" |
      | "id"  | "%user%"                               |
      | "id"  | "{user}"                               |

    #Server allows empty and space in userId field
  Scenario Outline: Create wrong Order with a wrong userId
    Then user "WhiteListed" puts an order for security "DAX" with a wrong <field> and <value> for it
    Examples:
      | field    | value                                  |
      | "userId" | " "                                    |
      | "userId" | ""                                     |
      | "userId" | "12f75303-c3af-4603-b075-c460081aa"    |
      | "userId" | "12f75303-c3af-4603-b075-c460081aa!@#" |
      | "userId" | "%user%"                               |
      | "userId" | "{userId}"                             |

     #Server allows empty and space in securityId field
  Scenario Outline: Create wrong Order with a wrong securityId
    Then user "WhiteListed" puts an order for security "DAX" with a wrong <field> and <value> for it
    Examples:
      | field        | value                                  |
      | "securityId" | " "                                    |
      | "securityId" | ""                                     |
      | "securityId" | "12f75303-c3af-4603-b075-c460081aa"    |
      | "securityId" | "12f75303-c3af-4603-b075-c460081aa!@#" |
      | "securityId" | "%securityId%"                         |
      | "securityId" | "{securityId}"                         |

  Scenario Outline: Create wrong Order with a wrong price
    Then user "WhiteListed" puts an order for security "DAX" with a wrong <field> and <value> for it
    Examples:
      | field   | value     |
      | "price" | " "       |
      | "price" | ""        |
      | "price" | "!@#$%"   |
      | "price" | "%price%" |
      | "price" | "{price}" |

  Scenario Outline: Create wrong Order with a wrong quantity
    Then user "WhiteListed" puts an order for security "DAX" with a wrong <field> and <value> for it
    Examples:
      | field      | value        |
      | "quantity" | " "          |
      | "quantity" | ""           |
      | "quantity" | "-1"         |
      | "quantity" | "!@#$%"      |
      | "quantity" | "%quantity%" |
      | "quantity" | "{quantity}" |

  Scenario Outline: Create wrong Order with a negative price or quantity
    Then user puts an order for security with a <field> and negative <value>
    Examples:
      | field      | value  |
      | "quantity" | "-1"   |
      | "quantity" | "-0.1" |
      | "price"    | "-1"   |
      | "price"    | "-0.1" |

  Scenario Outline: Create wrong Order with a zero price or quantity
    Then user puts an order for security with a <field> and negative <value>
    Examples:
      | field      | value |
      | "quantity" | "0"   |
      | "quantity" | "0.0" |
      | "price"    | "0"   |
      | "price"    | "0.0" |

  Scenario Outline: Create wrong Order with a price or quantity more or less then allowed range
    Then user puts an order for security with a <field> and value <value>, more or less then allowed range
    Examples:
      | field      | value             |
      | "price"    | "0.01"            |
      | "price"    | "0.99"            |
      | "price"    | "9999999999.9999" |
      | "price"    | "10000000000"     |
      | "quantity" | "1"               |
      | "quantity" | "999999999"       |
      | "quantity" | "1000000000"      |

  Scenario Outline: Wrong price type for buy order and no order created
    Then user "WhiteListed" puts a "buy" order for security "DAX" for a <field> with boolean <value>
    Examples:
      | field      | value   |
      | "price"    | "true"  |
      | "price"    | "false" |
      | "quantity" | "true"  |
      | "quantity" | "false" |

  Scenario Outline: Wrong quantity type for sell order and no order created
    Then user "WhiteListed" puts a "sell" order for security "DAX" for a <field> with boolean <value>
    Examples:
      | field      | value   |
      | "price"    | "true"  |
      | "price"    | "false" |
      | "quantity" | "true"  |
      | "quantity" | "false" |


