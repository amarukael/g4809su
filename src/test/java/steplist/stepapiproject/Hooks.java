package steplist.stepapiproject;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    public static Scenario scenario;

    @Before
    public void afterScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    // @After
    // public void afterScenario() {
    // try {
    // Thread.sleep(5000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }

    // String trackingref = Step1Inquiry.trackingref.get("last");
    // System.out.println(trackingref);
    // scenario.log("LOG : " + LogHelper.inqAlfa(trackingref));
    // }
}
