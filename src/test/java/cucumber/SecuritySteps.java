package cucumber;

import context.ScenarioContext;
import helpers.BaseHelper;
import io.cucumber.java.en.Given;
import pojo.Security;
import utils.Format;

import java.util.Map;
import java.util.UUID;

import static utils.DataGenerator.randomSecurity;

public class SecuritySteps extends BaseHelper {

    public SecuritySteps(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    @Given("create security with index {string} and save response")
    public void createSecurityWithMandatory(String index) {
        Security security = new Security(UUID.randomUUID(), index);

        scenarioContext.response = api.requestSecurity(security);
    }

    @Given("create security with id {string}, index {string} and save response")
    public void createSecurityWithAll(String id, String index) {
        Security security = randomSecurity();
        Map<String, Object> map = Format.objectToMap(security);
        map.put("id", id);
        map.put("name", index);

        scenarioContext.response = api.requestSecurity(map);
    }

}
