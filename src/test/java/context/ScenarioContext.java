package context;

import io.restassured.response.ValidatableResponse;
import pojo.Order;
import pojo.Security;
import pojo.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ScenarioContext {

    public Set<Security> securities;
    public Set<User> users;
    public List<Order> buyOrders;
    public List<Order> sellOrders;
    public ValidatableResponse response;

    public ScenarioContext() {
        securities = new HashSet<>();
        users = new HashSet<>();
        buyOrders = new LinkedList<>();
        sellOrders = new LinkedList<>();
    }
}
