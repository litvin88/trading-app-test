package api;

import utils.Config;

public class Endpoints {
    public static final String BASE_URL = Config.getBaseUrl();
    public static final String API = BASE_URL + "/api";
    public static final String USERS = API + "/users";
    public static final String USER = USERS + "/%s";
    public static final String ORDERS = API + "/orders";
    public static final String ORDER = ORDERS + "/%s";
    public static final String SECURITIES = API + "/securities";
    public static final String SECURITY = SECURITIES + "%s";
    public static final String TRADES = API + "/trades";
    public static final String TRADE_BUY_SELL = TRADES + "/orderBuyId/%s/orderSellId/%s";
}
