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
        features	=	"src/test/resources/feature/va/CreateVa.feature",
        glue		= 	{"steplist.stepva"},
        tags = "@create_va_example_base",
        plugin 		=	{"pretty",
                "json:target/JSONReports/report.json",
                "junit:target/JUnitReports/report.xml",
                "html:target/HtmlReports.html"},
        dryRun = false,
        monochrome = true
)
public class TestRunnerVA {
    private static String author = "";
    private static String project = "Payment Point VA";
    private static String envTesting = ConstantDims.environmentSvr;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("Payment Point VA");

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
