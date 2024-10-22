package testrunner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;

import constant.ConstantAPIProject;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(
		// features =
		// {"src/test/resources/feature/apiproj/APIProjectNonAlfaBillerOTN.feature"},
		features = {
				"C:\\Users\\fahmi.amaruddin\\IdeaProjects\\AutoAPI\\src\\test\\resources\\feature\\dgms\\ppob\\PPOB_CSV.feature" },
		// glue = {"steplist.stepdef"},
		glue = { "newsteplist" },
		// tags = "@apiproj_inq_sukses or @apiproj_inq_param_failed or
		// @apiproj_pay_sukses",
		plugin = { "pretty",
				"html:target/report/cucumber.html",
				"json:target/report/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },
		// strict = true,
		dryRun = false, monochrome = true)

public class TestRunnerAPIProject {
	private static String author = "Yudi Musthofa";
	private static String project = "API Project - ALFA & NONALFA";
	// private static String project = "API Project - NONALFA";
	private static String envTesting = ConstantAPIProject.environmentSvr;
	private static ExtentReports extentReports = ExtentService.getInstance();

	@BeforeClass
	public static void prepareEnvironment() {
		ExtentReport.editExtentProperties("apiproject");

		extentReports.setSystemInfo("Engineer", author);
		extentReports.setSystemInfo("Project", project);
		extentReports.setSystemInfo("Enviroment", envTesting);
	}

	@AfterSuite
	public void tearDownSuite() {
		// reporter.endReport();
		extentReports.flush();
	}
}