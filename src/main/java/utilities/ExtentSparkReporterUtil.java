package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentSparkReporterUtil {
	public static ExtentReports reports;
	public static ExtentSparkReporter spark;
	public static ExtentTest logg;
	public static WebDriver driver;
	
	public static void  configDriver() {
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().fullscreen();
	}

	public static ExtentReports extentReportConfig() {

		reports = new ExtentReports();
		reports.setSystemInfo("Tester", "Raghavendra Kaki");
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\target\\report.html");
		reports.attachReporter(spark);
		spark.config().setDocumentTitle("My Document");
		spark.config().setReportName("My Test case");
		spark.config().setTheme(Theme.DARK);
		return reports;

	}

	public static ExtentTest extentTestConfig(String methodName) {

		logg = reports.createTest(methodName);

		return logg;

	}

	public static void extentFlushConfig() {

		reports.flush();

	}

	public static void extentLog(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			logg.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "- Test Case Passed", ExtentColor.GREEN));
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			logg.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "- Test Case Passed", ExtentColor.RED));
			
					
		}

	}
	public static void extentLogWithScreenShot(ITestResult result, String src) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			logg.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "- Test Case Passed", ExtentColor.GREEN));
			logg.addScreenCaptureFromPath(src);
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			logg.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "- Test Case Passed", ExtentColor.RED));
			logg.addScreenCaptureFromPath(src);
					
		}

	}

	public static String screenCaptureConfig(WebDriver driver) throws IOException {

		String sc = System.getProperty("user.dir") + "\\target\\test.jpg";
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(sc));

		return sc;

	}

}
