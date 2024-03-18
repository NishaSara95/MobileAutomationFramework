package com.mobileautomationframework.testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.collect.ImmutableMap;
import com.mobileautomationframework.utils.AppiumUtils;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class androidBaseTest extends AppiumUtils {
	public AndroidDriver driver;
	Properties prop;

	public AppiumDriverLocalService serviceBuilder;


	/**
	 * This method shows the basics of appium adding the UI automator 2 and adding
	 * the desired capabilities
	 * 
	 * @throws IOException
	 */

	@BeforeClass(alwaysRun = true)
	public void configAppiumServer() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//com//mobileautomationframework//resources//data.properties");
		// To pass the parameter in maven command we cN give as below
		String ip =System.getProperty("ipAddress")!= null? System.getProperty("ipAddress"):prop.getProperty("ipAddress");
		prop.load(file);
		//String ip = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		int portInt = Integer.parseInt(port);
		
		 String urlString = prop.getProperty("url");

		String androidDevice = prop.getProperty("deviceName");

//		serviceBuilder = new AppiumServiceBuilder()
//				.withAppiumJS(new File(
//						"C://Users//Windows 11//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
//				.withIPAddress(ip).usingPort(portInt).withTimeout(Duration.ofSeconds(100)).
//				
//				build();
//		serviceBuilder.start();
		serviceBuilder =startAppiumServer(ip, portInt);
		
		// Configuring the mobile device name and apk file using UIAutomator2Options so
		// the server communicates to that mobile and perform operation
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(androidDevice); // Emulator
		// options.setDeviceName("Android Device"); // Real device
		options.setChromedriverExecutable("C://Users//Windows 11//Documents//chromedriver_win32//chromedriver.exe");

		// This apk path link is for api demo app
		// options.setApp(
		// "C://Users//Windows 11//eclipse-workspace//Workspace
		// 2//Appium_Learning//src//test//resources//ApiDemos-debug.apk");
		// This apk path link is for ecommerce demo app
		
		options.setApp(System.getProperty("user.dir") +("//src//test//resources//General-Store.apk"));
		options.setCapability("adbExecTimeout", 60000); // Set the timeout in milliseconds
		
		URL url = new URL("http://127.0.0.1:4723/");
		driver = new AndroidDriver(url, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		System.out.println("Server configured successfully");
	}

	

	@AfterClass(alwaysRun = true)
	public void tearDown() {

		driver.quit();
		stopAppium();
	}

}
