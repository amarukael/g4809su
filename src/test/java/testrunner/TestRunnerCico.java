package testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constant.ConstantDims;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(
        features	=	{"src/test/resources/feature/cico/CicoIDMBCI.feature"},
        glue		= 	{"steplist.stepcico"},
//      	tags		= 	"@apiproj_inq_sukses or @apiproj_inq_param_failed or @apiproj_pay_sukses",
        plugin 		=	{"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//		strict = true,
        dryRun = false,
        monochrome = true
)
public class TestRunnerCico {
    private static String author = "Yudi Musthofa";
    private static String project = "DIMS Automation Reports";
    private static String envTesting = "DEV";
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("cico");

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