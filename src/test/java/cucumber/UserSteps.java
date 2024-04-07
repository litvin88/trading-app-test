package cucumber;

import context.TestContext;
import helpers.BaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pojo.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.DataGenerator.initUserName;
import static utils.DataGenerator.randomUser;

public class UserSteps extends BaseHelper {

    private User userFromResponse;
    private User userData;

    public UserSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("create user with name {string}, password {string} and save response")
    public void createUser(String userName, String password) {
        userData = User.Builder.newInstance()
                .username(initUserName(userName))
                .password(password)
                .build();

        testContext.response = api.requestUser(userData);
    }

    @And("save user from response")
    public void saveUserFromResp() {
        userFromResponse = testContext.response
                .extract().as(User.class);
    }

    @Given("create user with all fields and save response")
    public void createUserWithAllFields() {
        userData = randomUser("random");
        testContext.response = api.requestUser(userData);
    }

    @And("user data and user from DB should be equals")
    public void validateUsers() {
        userData.setPassword(userFromResponse.getPassword());

        if (userData.getId() == null)
            userData.setId(userFromResponse.getId());

        assertThat(userData)
                .withFailMessage("User data and user from response not equals")
                .isEqualTo(userFromResponse);
    }

    @Given("create user with {string} name and save this user")
    public void createUser(String userName) {
        userFromResponse = setUpUser(userName);
    }

    @And("make request for duplicate user and save response")
    public void sendUserRequest() {
        testContext.response = api.requestUser(userFromResponse);
    }


    @And("validate that users list filtered by ID size equals to {int}")
    public void sendUserRequest(int count) {
        List<User> filtered = api.getUsersList().stream()
                .filter(user -> user.getId().equals(userFromResponse.getId()))
                .toList();
        assertThat(filtered.size())
                .withFailMessage("More than 1 user was created in DB but should not")
                .isEqualTo(count);
    }

    @And("create user with a wrong name {string}")
    public void createUserWithWrongName(String param) {
        User user = randomUser(param);
        testContext.response = api.requestUser(user);
    }

    @And("create user with a wrong password {string}")
    public void createUserWithWrongPassword(String param) {
        User user = randomUser("random");
        user.setPassword(param);

        testContext.response = api.requestUser(user);
    }
}
