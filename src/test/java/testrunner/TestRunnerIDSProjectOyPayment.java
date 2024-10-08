package testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constant.ConstantIDSProject;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(
        features	=	"src/test/resources/feature/idsproject/oypayment/IDSProject.feature",
        glue		= 	{"steplist/stepidsproject/oypayment"},
//		tags 		= 	"@inq_sukses",
        plugin 		=	{"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//		strict = true,
        dryRun = false,
        monochrome = true
)

public class TestRunnerIDSProjectOyPayment {
    private static String author = "Yudi Musthofa";
    private static String project = "IDSProject OYPayment Automation Reports";
    private static String envTesting = ConstantIDSProject.environmentSvr;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("idsprojectoypayment");

        extentReports.setSystemInfo("Engineer", author);
        extentReports.setSystemInfo("Project", project);
        extentReports.setSystemInfo("Enviroment", envTesting);
    }

    @AfterSuite
    public void tearDownSuite() {
        extentReports.flush();
    }
}
