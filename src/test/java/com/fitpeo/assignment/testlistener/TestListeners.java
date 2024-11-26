package com.fitpeo.assignment.testlistener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fitpeo.assignment.base.TestBase;
import com.fitpeo.assignment.extentreport.ExtentReporterNG;

public class TestListeners extends TestBase implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest tests;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		tests = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(tests); // unique thread id (ErrorValidationTest)->Test .this way it will creata a map
								// of tests with unique test id.This way we can acheive Thread safety
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		tests.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Attach a screenshot
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
