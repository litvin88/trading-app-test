package utils;

import enums.OrderType;
import pojo.Order;
import pojo.Security;
import pojo.User;

import java.util.UUID;

public class DataGenerator {

    public static Order generateOrder(User user, OrderType orderType, Security security, Double price, Long quantity) {
        return Order.builder()
                .id(UUID.randomUUID())
                .userId(user.getId())
                .securityId(security.getId())
                .price(price)
                .quantity(quantity)
                .type(orderType)
                .build();
    }
}
