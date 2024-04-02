package utils;

import enums.OrderType;
import pojo.Order;
import pojo.Security;
import pojo.User;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class DataGenerator {

    private static final Random random = new Random();

    public static BigDecimal randomBigDecimal() {
        return BigDecimal.valueOf(new Random().nextDouble(0, 1000000000));
    }

    public static OrderType randomOrderType() {
        OrderType[] types = OrderType.values();
        return types[random.nextInt(types.length)];
    }

    public static Long randomLong() {
        return random.nextLong();
    }

    public static Order generateOrder(User user, OrderType orderType, Security security, BigDecimal price, Long quantity) {
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
