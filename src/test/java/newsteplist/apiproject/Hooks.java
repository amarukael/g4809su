package newsteplist.apiproject;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class Hooks {
    public static Scenario scenario;

    @Before
    public void afterScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
