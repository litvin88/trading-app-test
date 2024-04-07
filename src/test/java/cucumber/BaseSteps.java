package cucumber;

import api.Endpoints;
import context.ScenarioContext;
import helpers.BaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pojo.Order;
import pojo.Trade;
import utils.FakeOrder;

import java.util.Comparator;

public class BaseSteps extends BaseHelper {


    private Order sellOrder;
    private Order buyOrder;

    public BaseSteps(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    @Given("one security {string} and one user {string} exist")
    public void oneSecurityAndOneUser(String securityName, String userName) {
        setUpSecurity(securityName);
        setUpUser(userName);
    }

    @Then("a trade occurs with the price of {double} and quantity of {long}")
    public void aTradeOccursWithThePriceOfAndQuantityOf(Double price, Long quantity) {
        Trade trade = api.getBuySellTrade(
                buyOrder,
                sellOrder);
        verifyTrade(trade, price, quantity);
    }

    @Then("no trades occur")
    public void noTradesOccur() {
        request.get(Endpoints.TRADE_BUY_SELL,
                        buyOrder.getId().toString(),
                        sellOrder.getId().toString())
                .statusCode(404);
    }

    @And("response status code is {int}")
    public void validateResponse(Integer statusCode) {
        scenarioContext.response
                .statusCode(statusCode);
    }

    @And("prepare orders for trade")
    public void setUpOrdersForTrade() {
        buyOrder = scenarioContext.buyOrders.get(0);
        sellOrder = scenarioContext.sellOrders
                .stream().min(Comparator.comparing(Order::getPrice))
                .orElse(new FakeOrder());

    }
}
