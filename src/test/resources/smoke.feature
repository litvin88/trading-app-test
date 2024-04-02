Feature: Smoke

  Scenario: Basic trading Buy Sell
    Given one security "WSB" and two users "Diamond" and "Paper" exist
    When user "Diamond" puts a "buy" order for security "WSB" with a price of 101 and quantity of 50
    And user "Paper" puts a "sell" order for security "WSB" with a price of 100 and a quantity of 100
    Then a trade occurs with the price of 100 and quantity of 50

  Scenario: Basic trading Sell Buy
    Given one security "SEC" and two users "User1" and "User2" exist
    When user "User2" puts a "sell" order for security "SEC" with a price of 99.99 and a quantity of 100
    And user "User1" puts a "buy" order for security "SEC" with a price of 100 and quantity of 50
    Then a trade occurs with the price of 99.99 and quantity of 50

  Scenario: Sell price bigger than Buy price and no trades occur
    Given one security "NTR" and two users "User1" and "User2" exist
    When user "User2" puts a "sell" order for security "NTR" with a price of 100 and a quantity of 100
    And user "User1" puts a "buy" order for security "NTR" with a price of 99.9 and quantity of 50
    Then no trades occur

  Scenario: Trading with one user with Sell and Buy orders
    Given one security "NTR" and one user "User1" exist
    When user "User1" puts a "sell" order for security "NTR" with a price of 1000 and a quantity of 100
    And user "User1" puts a "buy" order for security "NTR" with a price of 1001 and quantity of 50
    Then a trade occurs with the price of 1000 and quantity of 50

  Scenario: Trading with one user with Buy and Sell orders
    Given one security "NTR" and one user "User1" exist
    When user "User1" puts a "buy" order for security "NTR" with a price of 11111 and a quantity of 1000
    And user "User1" puts a "sell" order for security "NTR" with a price of 11111 and quantity of 50
#   Value for Trade's quantity field is taken from Buy order
    Then a trade occurs with the price of 11111 and quantity of 50

  Scenario: Max quantity for trading Sell Buy
    Given one security "SEC" and two users "User1" and "User2" exist
    When user "User1" puts a "sell" order for security "SEC" with a price of 100 and quantity of 9223372036854775807
    And user "User2" puts a "buy" order for security "SEC" with a price of 100 and quantity of 100
    Then a trade occurs with the price of 100 and quantity of 100

  Scenario: Max quantity for trading Buy Sell
    Given one security "WSB" and two users "User1" and "User2" exist
    When user "User1" puts a "buy" order for security "WSB" with a price of 100 and quantity of 9223372036854775807
    And user "User2" puts a "sell" order for security "WSB" with a price of 100 and quantity of 100
    Then a trade occurs with the price of 100 and quantity of 9223372036854775807

  Scenario: Price with decimal for trading Sell Buy
    Given one security "SEC" and two users "User1" and "User2" exist
    When user "User1" puts a "sell" order for security "SEC" with a price of 0.1 and quantity of 1000
    And user "User2" puts a "buy" order for security "SEC" with a price of 0.1 and quantity of 111
    Then a trade occurs with the price of 0.1 and quantity of 111

  Scenario: Price with decimal for trading Buy Sell
    Given one security "WSB" and two users "User1" and "User2" exist
    When user "User1" puts a "buy" order for security "WSB" with a price of 999999.9999 and quantity of 100
    And user "User2" puts a "sell" order for security "WSB" with a price of 999999.9999 and quantity of 1000
    Then a trade occurs with the price of 999999.9999 and quantity of 100
