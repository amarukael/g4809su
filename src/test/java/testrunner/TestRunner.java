package testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constant.ConstantDims;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(

		features	=	"src/test/resources/feature/apiproj/APIPROJECT-GDM.feature",
        glue		= 	{"steplist.stepdef"},

		tags		= 	"@getSAT",
		plugin 		=	{"pretty",
				"html:target/report/cucumber.html",
				"json:target/report/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},

		dryRun = false,
		monochrome = true
)

public class TestRunner {
	private static String author = "Kelby";
	private static String project = "apiproject";
	private static String envTesting = ConstantDims.environmentSvr;
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