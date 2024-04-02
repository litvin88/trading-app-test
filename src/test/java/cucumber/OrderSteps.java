package cucumber;

import api.Endpoints;
import context.TestContext;
import helpers.BaseHelper;
import io.cucumber.java.en.Then;
import pojo.Order;
import pojo.Security;
import pojo.User;
import utils.Format;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static utils.DataGenerator.*;
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
                .price(randomBigDecimal())
                .quantity(new Random().nextLong(100))
                .type(orderType(type))
                .build();

        request.post(order, Endpoints.ORDERS)
                .statusCode(201);
    }

    @Then("user {string} puts an order for security {string} with a wrong {string} and {string} for it")
    public void userPutsAnOrderForSecurityWithWrongField(String userName, String securityName, String field, String value) {
        User user = findUser(userName);
        Security security = findSecurity(securityName);

        Order order = randomValidOrder(user, security);

        Map<String, Object> orderMap = Format.objectToMap(order);
        orderMap.put(field, value);

        request.post(orderMap, Endpoints.ORDERS)
                .statusCode(400);
    }

    @Then("user puts an order for security with a {string} and zero {string}")
    @Then("user puts an order for security with a {string} and negative {string}")
    @Then("user puts an order for security with a wrong {string} and {string} for it")
    public void userPutsAnOrderForSecurityWithWrongPriceOrQuantity(String field, String value) {
        Order order = randomValidOrder(
                (User) testContext.users.toArray()[0],
                (Security) testContext.securities.toArray()[0]
        );

        Map<String, Object> orderMap = Format.objectToMap(order);
        orderMap.put(field, value);

        request.post(orderMap, Endpoints.ORDERS)
                .statusCode(400);
    }


    @Then("user puts an order for security with a {string} and value {string}, more or less then allowed range")
    public void userPutsAnOrderForSecurityWithWrong(String priceOrQuantity, String value) {
        Order order = randomValidOrder(
                (User) testContext.users.toArray()[0],
                (Security) testContext.securities.toArray()[0]
        );

        Map<String, Object> orderMap = Format.objectToMap(order);
        switch (priceOrQuantity) {
            case "price" -> orderMap.put(priceOrQuantity, new BigDecimal(value));
            case "quantity" -> orderMap.put(priceOrQuantity, Integer.parseInt(value));
        }

        //TODO should be 400 response
        request.post(orderMap, Endpoints.ORDERS)
                .statusCode(201);
    }

    @Then("user {string} puts a {string} order for security {string} for a {string} with a wrong type {string}")
    public void userPutAnWrongOrder(String userName, String orderType, String securityName, String field, String value) {
        User user = findUser(userName);
        Security security = findSecurity(securityName);
        verifyUserAndSecurity(user, security);

        Order order = generateOrder(user, orderType(orderType), security, randomBigDecimal(), randomLong());

        Map<String, Object> orderMap = Format.objectToMap(order);
        orderMap.put(field, Boolean.valueOf(value));

        request.post(orderMap, Endpoints.ORDERS)
                .statusCode(400);
    }

    private Order randomValidOrder(User user, Security security) {
        return Order.Builder.newInstance()
                .id(UUID.randomUUID())
                .userId(user.getId())
                .securityId(security.id())
                .price(randomBigDecimal())
                .quantity(new Random().nextLong(100))
                .type(randomOrderType())
                .build();
    }
}
