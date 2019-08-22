package pom.listener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pom.base.Base;

public class TestListener extends Base implements ITestListener {
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getName();
		System.out.println(
				"Test Case Success: " + methodname + " " + (result.getEndMillis() - result.getStartMillis() + "ms"));
		try {
			extentPass("Test Case Success: " + methodname + " "
					+ (result.getEndMillis() - result.getStartMillis() + "ms"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		String methodname = result.getName();
		System.out.println(
				"Test Case Failure: " + methodname + " " + (result.getEndMillis() - result.getStartMillis() + "ms"));
		System.out.println(result.getThrowable().toString());
		try {
			extentFail("Test Case Failure: " + methodname + " "
					+ (result.getEndMillis() - result.getStartMillis() + "ms"));
			extentFail(result.getThrowable().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodname = result.getName();
		System.out.println(
				"Test case Skipped: " + methodname + " " + (result.getEndMillis() - result.getStartMillis() + "ms"));
		try {
			extentSkip("Test case Skipped: " + methodname + " "
					+ (result.getEndMillis() - result.getStartMillis() + "ms"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		String methodname = result.getName();
		System.out.println("Test Case: " + methodname);
		extentInfo("Test Case: " + methodname);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
}
