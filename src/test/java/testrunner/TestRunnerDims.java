package testrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.service.ExtentService;
import constant.ConstantDims;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import utility.ExtentReport;

import java.io.File;
import java.util.Arrays;

@RunWith(Cucumber.class)
@CucumberOptions(
//		features	=	"src/test/resources/feature/dims",
//		features	=	{"src/test/resources/feature/dims/DIMS2Inquiry.feature"
//				, "src/test/resources/feature/dims/DIMS3Payment.feature"
//				, "src/test/resources/feature/dims/DIMS4Advice.feature"},
//		features	=	{"src/test/resources/feature/dims/DIMS2Inquiry.feature"},
//		features	=	{"src/test/resources/feature/dims/DIMS3Payment.feature"},
//		features	=	{"src/test/resources/feature/dims/DIMS4Advice.feature"},
		features	=	{"src/test/resources/feature/dims/nobu/Nobu2Payment.feature"},
//		features	=	{"src/test/resources/feature/dims/danamon/DanamonTesting.feature"},
//		features	=	{"src/test/resources/feature/dims/permata/PermataTesting.feature"},
//		features	=	{"src/test/resources/feature/dims/permata/Permata2Payment.feature"},
//		features	=	{"src/test/resources/feature/dims/permata/Permata3Advice.feature"},
//		features	=	{"src/test/resources/feature/dims/multicredential/DIMSMultiCredential.feature"},
//		features	= 	{"src/test/resources/feature/dims/disbursementtype/DIMSDisbursementType.feature"},
        glue		= 	{"steplist/stepdims"},
		tags 		= 	"@auth_sukses or @inq_sukses or @pay_sukses",
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

public class TestRunnerDims {
	private static String author = "Yudi Musthofa";
	private static String project = "DIMS Automation Reports";
	private static String envTesting = ConstantDims.environmentSvr;
	private static ExtentReports extentReports = ExtentService.getInstance();

	@BeforeClass
	public static void prepareEnvironment() {
		ExtentReport.editExtentProperties("dims");

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
