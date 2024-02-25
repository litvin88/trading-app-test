package cucumber;

import api.Api;
import api.Endpoints;
import api.Request;
import enums.OrderType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.Order;
import pojo.Security;
import pojo.Trade;
import pojo.User;
import utils.DataGenerator;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.*;

public class SmokeSteps {

    private final Request request;
    private final Api api;
    private final Set<Security> securities;
    private final Set<User> users;
    private Order buyOrder;
    private Order sellOrder;

    public SmokeSteps() {
        request = new Request();
        api = new Api(request);
        securities = new HashSet<>();
        users = new HashSet<>();
    }

    @Given("one security {string} and one users {string} exist")
    public void oneSecurityAndOneUser(String securityName, String userName) {
        setUpSecurity(securityName);
        setUpUser(userName);
    }

    @Given("one security {string} and two users {string} and {string} exist")
    public void oneSecurityAndTwoUsers(String securityName, String userName1, String userName2) {
        setUpSecurity(securityName);
        setUpUser(userName1);
        setUpUser(userName2);
    }

    @When("user {string} puts a {string} order for security {string} with a price of {double} and quantity of {long}")
    @And("user {string} puts a {string} order for security {string} with a price of {double} and a quantity of {long}")
    public void userPutAnOrder(String userName, String orderType, String securityName, Double price, Long quantity) {
        User user = findUser(userName);
        Security security = findSecurity(securityName);
        verifyUserAndSecurity(user, security);

        setUpOrder(user, OrderType.valueOf(orderType.toUpperCase(Locale.ROOT)), security, price, quantity);
    }

    @Then("user {string} puts a {string} order with a wrong param for security {string} with a price of {double} and quantity of {long}")
    public void userPutAnOrderWithAWrongPrice(String userName, String orderType, String securityName, Double price, Long quantity) {
        User user = findUser(userName);
        Security security = findSecurity(securityName);
        verifyUserAndSecurity(user, security);

        Order order = DataGenerator.generateOrder(user, OrderType.valueOf(orderType.toUpperCase(Locale.ROOT)), security,
                price, quantity);
        request.post(order, Endpoints.ORDERS)
                .statusCode(400);
    }

    @Then("a trade occurs with the price of {double} and quantity of {long}")
    public void aTradeOccursWithThePriceOfAndQuantityOf(Double price, Long quantity) {
        Trade trade = api.getBuySellTrade(buyOrder, sellOrder);
        verifyTrade(trade, price, quantity);
    }

    @Then("no trades occur")
    public void noTradesOccur() {
        request.get(Endpoints.TRADE_BUY_SELL, buyOrder.getId().toString(), sellOrder.getId().toString())
                .statusCode(400);
    }

    private void verifyTrade(Trade trade, Double price, Long quantity) {
        assertEquals("Price not expected", trade.getPrice(), price);
        assertEquals("Quantity not expected", trade.getQuantity(), quantity);
    }

    private void verifyUserAndSecurity(User user, Security security) {
        assertTrue(String.format("Unknown user \"%s\"", user.getUsername()), users.contains(user));
        assertTrue(String.format("Unknown security \"%s\"", security.getName()), securities.contains(security));
    }

    private void setUpSecurity(String securityName) {
        Security security = api.createSecurity(securityName);
        securities.add(security);
    }

    private void setUpUser(String userName) {
        User userOne = api.createUser(userName);
        users.add(userOne);
    }

    private void setUpOrder(User user, OrderType orderType, Security security, Double price, Long quantity) {
        Order orderCreated = api.createOrder(user, orderType, security, price, quantity);
        if (OrderType.SELL == orderCreated.getType())
            sellOrder = orderCreated;
        else
            buyOrder = orderCreated;
    }

    private User findUser(String name) {
        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(name))
                foundUser = user;
        }
        assertNotNull("User with a name: %s was not found".formatted(name), foundUser);
        return foundUser;
    }

    private Security findSecurity(String name) {
        Security securityToFind = null;
        for (Security security : securities) {
            if (security.getName().equals(name))
                securityToFind = security;
        }
        assertNotNull("Security with a name: %s, was not found".formatted(name), securityToFind);
        return securityToFind;
    }
}
