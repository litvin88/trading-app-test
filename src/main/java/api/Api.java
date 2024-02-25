package api;

import enums.OrderType;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.Order;
import pojo.Security;
import pojo.Trade;
import pojo.User;
import utils.DataGenerator;

import java.util.UUID;

public class Api {

    private final Request request;

    public Api(Request request) {
        this.request = request;
    }

    public User createUser(String name) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .username(name)
                .password(RandomStringUtils.randomAlphanumeric(64))
                .build();

        return request.post(user, Endpoints.USERS)
                .statusCode(201)
                .extract().as(User.class);
    }

    public Security createSecurity(String name) {
        Security security = Security.builder()
                .id(UUID.randomUUID())
                .name(name)
                .build();

        return request.post(security, Endpoints.SECURITIES)
                .statusCode(201)
                .extract().as(Security.class);
    }

    public Order createOrder(User user, OrderType orderType, Security security, Double price, Long quantity) {
        Order order = DataGenerator.generateOrder(user, orderType, security, price, quantity);
        return request.post(order, Endpoints.ORDERS)
                .statusCode(201)
                .extract().as(Order.class);
    }

    public Trade getBuySellTrade(Order buyOrder, Order sellOrder) {
        return request.get(Endpoints.TRADE_BUY_SELL, buyOrder.getId().toString(), sellOrder.getId().toString())
                .statusCode(200)
                .extract().as(Trade.class);
    }

}
