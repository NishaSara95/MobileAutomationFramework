package com.mobileautomationframework.testUtils;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extent;
	
	public  static ExtentReports configExtentReport() {

		// Extent Spark Reporter and Extent Report Classes areused 
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Mobile Automation Report");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Nisha");
		System.out.println("Extent report configuration is set");
		return extent;
	}
}
