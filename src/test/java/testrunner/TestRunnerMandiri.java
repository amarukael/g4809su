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
//		features	=	"src/test/resources/feature/mandiri",
        features	=	{"src/test/resources/feature/mandiri/mandiri2Inquiry.feature"},
//        features	=	{"src/test/resources/feature/mandiri/mandiri3Payment.feature"},
        glue		= 	{"steplist/stepmandiri"},
//		tags 		= 	"@auth_sukses or @inq_sukses",
//      tags 		= 	"not (@auth_failed or @pay_sukses or @pay_header_failed or @pay_param_failed)",
//		tags 		= 	"not (@auth_failed or @inq_header_failed or @inq_param_failed)",
        plugin 		=	{"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//		strict = true,
        dryRun = false,
        monochrome = true
)

public class TestRunnerMandiri {
    private static String author = "Kelby";
    private static String project = "Mandiri Automation Reports";
    private static String envTesting = ConstantDims.environmentSvr;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("mandiri");

        extentReports.setSystemInfo("Engineer", author);
        extentReports.setSystemInfo("Project", project);
        extentReports.setSystemInfo("Enviroment", envTesting);
    }

//	@AfterClass
//	public static void generateReport() {
//		String tittleReport = project + ", Created by: " + author;
//		Configuration config = new Configuration(new File("target"), tittleReport);
//		ReportBuilder reportBuilder = new ReportBuilder(
//				Arrays.asList("target/report/cucumber.json"),
//				config
//		);
//		reportBuilder.generateReports();
//	}

    @AfterSuite
    public void tearDownSuite() {
        // reporter.endReport();
        extentReports.flush();
    }
}
