package helpers;

import api.Api;
import api.Request;
import context.TestContext;
import enums.Index;
import enums.OrderType;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.Order;
import pojo.Security;
import pojo.Trade;
import pojo.User;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.DataGenerator.initUserName;

public class BaseHelper {
    protected final Request request;
    protected final Api api;
    protected TestContext testContext;

    public BaseHelper(TestContext testContext) {
        this.request = new Request();
        this.api = new Api(request);
        this.testContext = testContext;
    }

    public User setUpUser(String userName) {

        User userOne = checkAndCreateUsers(userName);
        testContext.users.add(userOne);
        return userOne;
    }

    private User checkAndCreateUsers(String userName) {
        User user = filterUsers(userName);
        if (user == null) {
            user = User.Builder.newInstance()
                    .username(initUserName(userName))
                    .password(RandomStringUtils.randomAlphanumeric(64))
                    .build();
            return api.createUser(user);
        }
        return user;
    }

    public User filterUsers(String userName) {
        return testContext.users.stream().filter(u -> u.getUsername().equals(userName))
                .findFirst().orElse(null);
    }

    public User findUser(String name) {
        User foundUser = filterUsers(name);
        assertThat(foundUser)
                .withFailMessage("User with a name: %s, was not found", name)
                .isNotNull();
        return foundUser;
    }

    public void setUpOrder(User user, OrderType orderType, Security security, Double price, Long quantity) {
        Order orderCreated = api.createOrder(user, orderType, security, price, quantity);
        if (OrderType.SELL == orderCreated.getType())
            testContext.sellOrder = orderCreated;
        else
            testContext.buyOrder = orderCreated;
    }

    public void verifyTrade(Trade trade, BigDecimal price, Long quantity) {
        assertThat(price).withFailMessage("Price not expected")
                .isEqualTo(trade.price());
        assertThat(quantity).withFailMessage("Quantity not expected")
                .isEqualTo(trade.quantity());
    }

    public void setUpSecurity(String indexName) {
        assertThat(Index.values())
                .extracting(Index::name)
                .contains(indexName);
        Security security = api.createSecurity(Index.valueOf(indexName));
        testContext.securities.add(security);
    }

    public Security findSecurity(String name) {
        Security securityToFind = null;
        for (Security security : testContext.securities) {
            if (security.name().equals(name))
                securityToFind = security;
        }
        assertThat(securityToFind)
                .withFailMessage("Security with a name: %s was not found", name)
                .isNotNull();
        return securityToFind;
    }

    public void verifyUserAndSecurity(User user, Security security) {
        assertThat(testContext.users.contains(user)).withFailMessage("Unknown user: %s", user.getUsername())
                .isTrue();
        assertThat(testContext.securities.contains(security)).withFailMessage("Unknown security: %s", security.name())
                .isTrue();
    }

}
