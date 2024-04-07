package cucumber;

import api.Endpoints;
import context.ScenarioContext;
import helpers.BaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pojo.Trade;

import java.math.BigDecimal;

public class BaseSteps extends BaseHelper {

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
        Trade trade = api.getBuySellTrade(scenarioContext.buyOrder, scenarioContext.sellOrder);
        verifyTrade(trade, BigDecimal.valueOf(price), quantity);
    }

    @Then("no trades occur")
    public void noTradesOccur() {
        request.get(Endpoints.TRADE_BUY_SELL, scenarioContext.buyOrder.getId().toString(), scenarioContext.sellOrder.getId().toString())
                .statusCode(404);
    }

    @And("response status code is {int}")
    public void validateResponse(Integer statusCode) {
        scenarioContext.response
                .statusCode(statusCode);
    }

}
