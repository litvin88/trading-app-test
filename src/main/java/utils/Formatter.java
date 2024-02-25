package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.OrderType;

import java.util.Locale;
import java.util.Map;

public class Formatter {
    public static String formUrl(String endpoint, String... params){
        return endpoint.formatted((Object[]) params);
    }

    public static Map<String, Object> formatObjectToMap(Object object) {
        return new ObjectMapper().convertValue(object, new TypeReference<>() {});
    }

    public static OrderType formOrderType(String orderType){
        return OrderType.valueOf(orderType.toUpperCase(Locale.ROOT));
    }
}
