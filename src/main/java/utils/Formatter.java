package utils;

public class Formatter {
    public static String formUrl(String endpoint, String... params){
        return endpoint.formatted((Object[]) params);
    }
}
