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
//        features	=	{"src/test/resources/feature/dgms/ototrans/OTOTrans1InquiryV2.feature"
//                , "src/test/resources/feature/dgms/ototrans/OTOTrans2PaymentV2.feature"
//                , "src/test/resources/feature/dgms/ototrans/OTOTrans3AdviceV2.feature"},
//        features	=	{"src/test/resources/feature/dgms/ototrans/OTOTrans1InquiryV2.feature"},
//        features	=	{"src/test/resources/feature/dgms/ototrans/OTOTrans2PaymentV2.feature"},
//        features	=	{"src/test/resources/feature/dgms/ototrans/OTOTrans3AdviceV2.feature"},
//        features	=	{"src/test/resources/feature/dgms/ototrans/OTOTrans4InquiryAcc.feature"},
        features	=	{"src/test/resources/feature/dgms/ototrans/flip/OTOTrans.feature"
                , "src/test/resources/feature/dgms/ototrans/flip/OTOTransV2.feature"},
        glue		= 	{"steplist/stepototrans"},
//        tags = "@inquiry_account_sukses",
        plugin 		=	{"pretty",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//		    strict = true,
        dryRun = false,
        monochrome = true
)

public class TestRunnerOTOTrans {
    private static String author = "Yudi Musthofa";
    private static String project = "OTOTrans Automation Reports";
    private static String envTesting = ConstantPpob.environmentSvr;
    private static ExtentReports extentReports = ExtentService.getInstance();

    @BeforeClass
    public static void prepareEnvironment() {
        ExtentReport.editExtentProperties("ototrans");

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
