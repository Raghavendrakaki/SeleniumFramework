package methods;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import accelarators.Base;
import utilities.MyOwnException;
import utilities.Report;

public class ListenersUtil extends Base implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			childTestCase1 = Report.makeTestCaseEntry(Report.extentReport, "TestCase Name");
		} catch (MyOwnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSuccess(ITestResult result) {
		try {
			Report.logTestCaseStatus(childTestCase1, "PASS", "Test Case passed");
		} catch (MyOwnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult result) {
		try {
			Report.logTestCaseStatus(childTestCase1, "FAIL", result.getName() + " is Failed");
		} catch (MyOwnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	}

}
