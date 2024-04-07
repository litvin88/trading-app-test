package api;

import enums.Index;
import enums.OrderType;
import io.restassured.response.ValidatableResponse;
import pojo.Order;
import pojo.Security;
import pojo.Trade;
import pojo.User;
import utils.DataGenerator;

import java.util.List;
import java.util.UUID;

public class Api {

    private final Request request;

    public Api(Request request) {
        this.request = request;
    }

    public ValidatableResponse requestUser(Object user) {
        return request.post(user, Endpoints.USERS);
    }

    public User createUser(Object user) {
        return requestUser(user)
                .statusCode(201)
                .extract().as(User.class);
    }

    public ValidatableResponse requestSecurity(Object security) {
        return request.post(security, Endpoints.SECURITIES);
    }

    public Security createSecurity(Index index) {
        Security security = new Security(UUID.randomUUID(), index.name());

        return requestSecurity(security)
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

    public User getUserById(User user) {
        return request.get(Endpoints.USER, user.getId().toString())
                .statusCode(200)
                .extract().as(User.class);
    }

    public List<User> getUsersList() {
        return request.get(Endpoints.USERS)
                .statusCode(200)
                .extract().jsonPath().getList(".", User.class);
    }

}
