package cucumber;

import context.ScenarioContext;
import helpers.BaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pojo.Security;
import pojo.User;

import static utils.Format.orderType;

public class SmokeSteps extends BaseHelper {

    public SmokeSteps(ScenarioContext scenarioContext) {
        super(scenarioContext);
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


}
