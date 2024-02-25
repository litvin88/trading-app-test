Feature: Smoke

  Scenario: Basic trading Buy Sell
    Given one security "WSB" and two users "Diamond" and "Paper" exist
    When user "Diamond" puts a "buy" order for security "WSB" with a price of 101 and quantity of 50
    And user "Paper" puts a "sell" order for security "WSB" with a price of 100 and a quantity of 100
    Then a trade occurs with the price of 100 and quantity of 50

  Scenario: Basic trading Sell Buy
    Given one security "SEC" and two users "User1" and "User2" exist
    When user "User2" puts a "sell" order for security "SEC" with a price of 100 and a quantity of 100
    And user "User1" puts a "buy" order for security "SEC" with a price of 101 and quantity of 50
    Then a trade occurs with the price of 100 and quantity of 50

  Scenario: Buy price bigger than Sell price and no trades occur
    Given one security "NTR" and two users "User1" and "User2" exist
    When user "User2" puts a "sell" order for security "NTR" with a price of 100 and a quantity of 100
    And user "User1" puts a "buy" order for security "NTR" with a price of 99 and quantity of 50
    Then no trades occur

  Scenario: One user with Sell and Buy orders and no trades occur
    Given one security "NTR" and one users "User1" exist
    When user "User1" puts a "sell" order for security "NTR" with a price of 101 and a quantity of 100
    And user "User1" puts a "buy" order for security "NTR" with a price of 100 and quantity of 50
    Then a trade occurs with the price of 100 and quantity of 50

  Scenario: Price zero for sell order and no order created
    Given one security "NTR" and one users "User1" exist
    Then user "User1" puts a "sell" order with a wrong param for security "NTR" with a price of 0 and quantity of 10

  Scenario: Price zero for buy order and no order created
    Given one security "SEC" and one users "User1" exist
    Then user "User1" puts a "buy" order with a wrong param for security "SEC" with a price of 0 and quantity of 10

  Scenario: Quantity zero for buy order and no order created
    Given one security "NTR" and one users "User1" exist
    Then user "User1" puts a "buy" order with a wrong param for security "NTR" with a price of 1000 and quantity of 0

  Scenario: Quantity zero for sell order and no order created
    Given one security "SEC" and one users "User1" exist
    Then user "User1" puts a "sell" order with a wrong param for security "SEC" with a price of 1000 and quantity of 0
