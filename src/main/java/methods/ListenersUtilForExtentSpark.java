package methods;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.ExtentSparkReporterUtil;
import utilities.MyOwnException;
import utilities.Report;

public class ListenersUtilForExtentSpark extends ExtentSparkReporterUtil implements ITestListener {
	String sc;
	
	public void onTestStart(ITestResult result) {
		extentReportConfig();
		extentTestConfig(result.getName());

	}

	
	public void onTestSuccess(ITestResult result) {
		extentLog(result);

	}

	
	public void onTestFailure(ITestResult result) {
			
			try {
				sc = screenCaptureConfig(driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentLogWithScreenShot(result, sc);

	}

	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	
	public void onFinish(ITestContext context) {
		extentFlushConfig();

	}

}
