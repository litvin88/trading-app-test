package helpers;

import api.Api;
import api.Request;
import pojo.Security;
import pojo.User;

import java.util.HashSet;
import java.util.Set;

public class BaseHelper {

    protected final Request request;
    protected final Api api;
    protected final Set<Security> securities;
    protected final Set<User> users;

    public BaseHelper() {
        request = new Request();
        api = new Api(request);
        securities = new HashSet<>();
        users = new HashSet<>();
    }

}
