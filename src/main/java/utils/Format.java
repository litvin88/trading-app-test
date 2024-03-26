package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.OrderType;

import java.util.Locale;
import java.util.Map;

public class Format {
    public static String url(String endpoint, String... params){
        return endpoint.formatted((Object[]) params);
    }

    public static Map<String, Object> objectToMap(Object object) {
        return new ObjectMapper().convertValue(object, new TypeReference<>() {});
    }

    public static OrderType orderType(String orderType){
        return OrderType.valueOf(orderType.toUpperCase(Locale.ROOT));
    }
}
