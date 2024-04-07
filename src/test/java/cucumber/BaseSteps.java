package cucumber;

import api.Endpoints;
import context.TestContext;
import helpers.BaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pojo.Trade;

import java.math.BigDecimal;

public class BaseSteps extends BaseHelper {

    public BaseSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("one security {string} and one user {string} exist")
    public void oneSecurityAndOneUser(String securityName, String userName) {
        setUpSecurity(securityName);
        setUpUser(userName);
    }

    @Then("a trade occurs with the price of {double} and quantity of {long}")
    public void aTradeOccursWithThePriceOfAndQuantityOf(Double price, Long quantity) {
        Trade trade = api.getBuySellTrade(testContext.buyOrder, testContext.sellOrder);
        verifyTrade(trade, BigDecimal.valueOf(price), quantity);
    }

    @Then("no trades occur")
    public void noTradesOccur() {
        request.get(Endpoints.TRADE_BUY_SELL, testContext.buyOrder.getId().toString(), testContext.sellOrder.getId().toString())
                .statusCode(404);
    }

    @And("response status code is {int}")
    public void validateResponse(Integer statusCode) {
        testContext.response
                .statusCode(statusCode);
    }

}
