package com.mobileautomationframework.testUtils;

import java.io.IOException;
import java.lang.reflect.Field;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mobileautomationframework.utils.AppiumUtils;
import com.mobileautomationframework.testUtils.ExtentReporterNG;

import io.appium.java_client.AppiumDriver;

public class Listeners  extends AppiumUtils implements ITestListener{

	

	ExtentTest test;
	ExtentReports ext= ExtentReporterNG.configExtentReport();
	AppiumDriver driver;

	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		 test = ext.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,"Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		 test.fail(result.getThrowable());
		test.log(Status.FAIL,"Test Failed");
		String testName = result.getMethod().getMethodName();
		
		try {
			driver= (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			 if (driver != null) {
		            test.addScreenCaptureFromPath(getScreenShot(driver, testName), testName);
		        } else {
		            System.out.println("Driver is null. Cannot capture screenshot.");
		        }
			//test.addScreenCaptureFromPath(getScreenShot(driver,testName),testName);
		}
		catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
		
		
		
//		  try {
//			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		
//          if (driver != null) {
//              test.addScreenCaptureFromPath(getScreenShot(testName, driver), testName);
//          } else {
//              System.out.println("Driver is null. Cannot capture screenshot.");
          
			
		
//	catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ext.flush();
	}



}
