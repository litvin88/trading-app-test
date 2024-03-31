package cucumber;

import api.Endpoints;
import context.TestContext;
import helpers.BaseHelper;
import io.cucumber.java.en.Then;
import pojo.Order;
import pojo.Security;
import pojo.User;

import java.util.Random;
import java.util.UUID;

import static utils.DataGenerator.randomDouble;
import static utils.Format.orderType;

public class OrderSteps extends BaseHelper {

    public OrderSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("user {string} puts a {string} order for security {string} with mandatory fields only")
    public void userPutsATypeOrderForSecurityWithMandatoryFieldsOnly(String userName, String type, String securityName) {
        User user = findUser(userName);
        Security security = findSecurity(securityName);

        Order order = Order.Builder.newInstance()
                .id(UUID.randomUUID())
                .userId(user.getId())
                .securityId(security.id())
                .price(randomDouble())
                .quantity(new Random().nextLong(100))
                .type(orderType(type))
                .build();

        request.post(order, Endpoints.ORDERS)
                .statusCode(201);
    }
}
