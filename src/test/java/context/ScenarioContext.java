package context;

import io.restassured.response.ValidatableResponse;
import pojo.Order;
import pojo.Security;
import pojo.User;

import java.util.HashSet;
import java.util.Set;

public class ScenarioContext {

    public Set<Security> securities;
    public Set<User> users;
    public Order buyOrder;
    public Order sellOrder;
    public ValidatableResponse response;

    public ScenarioContext(){
        securities = new HashSet<>();
        users = new HashSet<>();
    }
}
