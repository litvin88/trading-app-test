Feature: Negative Checks

  Background:
    Given one security "WSB" and one user "Diamond" exist

  Scenario Outline: Create correct Order with mandatory field only
    Then user "Diamond" puts a <type> order for security "WSB" with mandatory fields only
    Examples:
      | type   |
      | "sell" |
      | "buy"  |

  Scenario Outline: Create correct Buy Order with all field only
    #TODO create steps
    Examples:
      | type   |
      | "sell" |
      | "buy"  |

  Scenario: Create correct Sell Order with all field only
    #TODO create steps

  Scenario: Create wrong Order with a wrong id
    #TODO create steps

  Scenario: Create wrong Order with a wrong userId
    #TODO create steps

  Scenario: Create wrong Order with a wrong securityId
    #TODO create steps

  Scenario: Create wrong Order with a wrong type
    #TODO create steps

  Scenario: Create wrong Order with a wrong price
    #TODO create steps

  Scenario: Create wrong Order with a wrong quantity
    #TODO create steps

  Scenario: Wrong price type for buy order and no order created
    Then user "Diamond" puts a "buy" order for security "WSB" with wrong type for "price"

  Scenario: Wrong quantity type for sell order and no order created
    Then user "Diamond" puts a "sell" order for security "WSB" with wrong type for "quantity"



