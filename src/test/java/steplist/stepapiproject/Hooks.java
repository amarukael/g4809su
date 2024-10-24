package steplist.stepapiproject;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Node;

public class Hooks {
    public static Scenario scenario;

    @Before
    public void afterScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
