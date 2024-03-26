package cucumber;

import api.Endpoints;
import helpers.SmokeHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.Order;
import pojo.Security;
import pojo.Trade;
import pojo.User;

import java.util.Map;

import static utils.DataGenerator.*;
import static utils.Format.objectToMap;
import static utils.Format.orderType;

public class SmokeSteps extends SmokeHelper {

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

        setUpOrder(user, orderType(orderType), security, price, quantity);
    }

    @Then("user {string} puts a {string} order for security {string} with a wrong param for a price of {double} nor a quantity of {long}")
    public void userPutAnOrderWithAWrongParams(String userName, String orderType, String securityName, Double price, Long quantity) {
        User user = findUser(userName);
        Security security = findSecurity(securityName);
        verifyUserAndSecurity(user, security);

        Order order = generateOrder(user, orderType(orderType), security,
                price, quantity);
        request.post(order, Endpoints.ORDERS)
                .statusCode(400);
    }

    @Then("user {string} puts a {string} order for security {string} with wrong type for {string}")
    public void userPutAnWrongOrder(String userName, String orderType, String securityName, String param) {
        User user = findUser(userName);
        Security security = findSecurity(securityName);
        verifyUserAndSecurity(user, security);

        Order order = generateOrder(user, orderType(orderType), security, randomDouble(), randomLong());

        Map<String, Object> orderMap = objectToMap(order);
        if (param.equals("price"))
            orderMap.put("price", true);
        else
            orderMap.put("quantity", true);

        request.post(orderMap, Endpoints.ORDERS)
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
                .statusCode(404);
    }
}
