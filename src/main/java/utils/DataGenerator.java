package utils;

import enums.Index;
import enums.OrderType;
import org.apache.commons.lang3.RandomStringUtils;
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

    public static String randomSting(int length) {
        return RandomStringUtils.randomAlphabetic(length);
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

    public static User randomUser(String name){
        return User.Builder.newInstance()
                .id(UUID.randomUUID())
                .username(initUserName(name))
                .password(randomSting(10))
                .build();
    }

    public static String initUserName(String userName) {
        if (userName.equals("random"))
            userName = "Test_" + randomSting(10);
        return userName;
    }

    public static Security randomSecurity(){
        return new Security(UUID.randomUUID(), randomIndex());
    }

    public static String randomIndex(){
        Index[] indexes = Index.values();
        return indexes[new Random().nextInt(indexes.length)].toString();
    }
}
