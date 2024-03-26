package utils;

import enums.OrderType;
import pojo.Order;
import pojo.Security;
import pojo.User;

import java.util.Random;
import java.util.UUID;

public class DataGenerator {


    public static Double randomDouble() {
        return new Random().nextDouble();
    }

    public static Long randomLong() {
        return new Random().nextLong();
    }

    public static Order generateOrder(User user, OrderType orderType, Security security, Double price, Long quantity) {
        return Order.Builder.newInstance()
                .id(UUID.randomUUID())
                .userId(user.getId())
                .securityId(security.id())
                .price(price)
                .quantity(quantity)
                .type(orderType)
                .build();
    }
}
