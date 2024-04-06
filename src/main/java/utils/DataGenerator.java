package utils;

import enums.OrderType;
import pojo.Order;
import pojo.Security;
import pojo.User;

import java.util.Random;
import java.util.UUID;

public class DataGenerator {

    private static final Random random = new Random();

    public static Double randomDouble() {
        return new Random().nextDouble(0, Double.MAX_VALUE);
    }

    public static OrderType randomOrderType() {
        OrderType[] types = OrderType.values();
        return types[random.nextInt(types.length)];
    }

    public static Long randomLong() {
        return random.nextLong();
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
