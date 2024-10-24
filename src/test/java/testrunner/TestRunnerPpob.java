package testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import constant.ConstantPpob;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

@RunWith(Cucumber.class)
@CucumberOptions(
//        features	=	{"src/test/resources/feature/dgms/ppob/PPOB1InquiryV2.feature"},
        features	=	{"src/test/resources/feature/dgms/ppob/PPOB2PaymentV2.feature"},
//        features	=	{"src/test/resources/feature/dgms/ppob/PPOB3AdviceV2.feature"},
        glue		= 	{"steplist/stepppob"},
        tags        =   "@payment_v2_async_sukses",
        plugin 		=	{"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//		    strict = true,
        dryRun = false,
        monochrome = true
)

public class TestRunnerPpob {
    private static String author = "Yudi Musthofa";
    private static String project = "PPOB Automation Reports";
    private static String envTesting = ConstantPpob.environmentSvr;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("ppob");

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
