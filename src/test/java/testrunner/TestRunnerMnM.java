package testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constant.ConstantMnM;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(
        features	=	{"src/test/resources/feature/mnm/Webhook.feature"},
        glue		= 	{"steplist/stepmnm"},
		tags 		= 	"@webhook_get or @webhook_meta",
        plugin 		=	{"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//		strict = true,
        dryRun = false,
        monochrome = true
)

public class TestRunnerMnM {
    private static String author = "Yudi Musthofa";
    private static String project = "M&M Automation Reports";
    private static String envTesting = ConstantMnM.environmentSvr;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("mnm");

        extentReports.setSystemInfo("Engineer", author);
        extentReports.setSystemInfo("Project", project);
        extentReports.setSystemInfo("Enviroment", envTesting);
    }

//    @AfterClass
//    public static void generateReport() {
//        String tittleReport = project + ", Created by: " + author;
//        Configuration config = new Configuration(new File("target"), tittleReport);
//        ReportBuilder reportBuilder = new ReportBuilder(
//                Arrays.asList("target/report/cucumber.json"),
//                config
//        );
//        reportBuilder.generateReports();
//    }

    @AfterSuite
    public void tearDownSuite() {
        // reporter.endReport();
        extentReports.flush();
    }
}
