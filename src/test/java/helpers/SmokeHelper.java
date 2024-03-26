package helpers;

import enums.Index;
import enums.OrderType;
import pojo.Order;
import pojo.Security;
import pojo.Trade;
import pojo.User;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokeHelper extends BaseHelper{

    protected Order buyOrder;
    protected Order sellOrder;

    public void verifyTrade(Trade trade, Double price, Long quantity) {
        assertThat(price).withFailMessage("Price not expected")
                .isEqualTo(trade.price());
        assertThat(quantity).withFailMessage("Quantity not expected")
                .isEqualTo(trade.quantity());
    }

    public void verifyUserAndSecurity(User user, Security security) {
        assertThat(users.contains(user)).withFailMessage("Unknown user: %s", user.getUsername())
                .isTrue();
        assertThat(securities.contains(security)).withFailMessage("Unknown security: %s", security.name())
                .isTrue();
    }

    public void setUpSecurity(String indexName) {
        assertThat(Index.values())
                .extracting(Index::name)
                .contains(indexName);
        Security security = api.createSecurity(Index.valueOf(indexName));
        securities.add(security);
    }

    public void setUpUser(String userName) {
        User userOne = api.createUser(userName);
        users.add(userOne);
    }

    public void setUpOrder(User user, OrderType orderType, Security security, Double price, Long quantity) {
        Order orderCreated = api.createOrder(user, orderType, security, price, quantity);
        if (OrderType.SELL == orderCreated.getType())
            sellOrder = orderCreated;
        else
            buyOrder = orderCreated;
    }

    public User findUser(String name) {
        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(name))
                foundUser = user;
        }
        assertThat(foundUser)
                .withFailMessage("User with a name: %s was not found", name)
                .isNotNull();
        return foundUser;
    }

    public Security findSecurity(String name) {
        Security securityToFind = null;
        for (Security security : securities) {
            if (security.name().equals(name))
                securityToFind = security;
        }
        assertThat(securityToFind)
                .withFailMessage("Security with a name: %s was not found", name)
                .isNotNull();
        return securityToFind;
    }


}
