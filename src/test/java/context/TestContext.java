package context;

import pojo.Order;
import pojo.Security;
import pojo.User;

import java.util.HashSet;
import java.util.Set;

public class TestContext {

    public Set<Security> securities;
    public Set<User> users;
    public Order buyOrder;
    public Order sellOrder;

    public TestContext(){
        securities = new HashSet<>();
        users = new HashSet<>();
    }
}
