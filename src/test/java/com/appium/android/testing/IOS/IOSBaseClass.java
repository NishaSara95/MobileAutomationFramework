package com.appium.android.testing.IOS;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseClass {

	public AppiumDriverLocalService serviceBuilder;

	public IOSDriver driver;

	/**
	 * This method shows the basics of appium adding the XCUITestOptions and adding
	 * the desired capabilities
	 * 
	 * @throws MalformedURLException Using Url class to add the default port url of
	 *                               appium Using XCUI Test for adding the device
	 *                               onfiguration such as Device name, appfile and
	 *                               other details etc
	 */

	@BeforeClass
	public void configAppiumServer() throws MalformedURLException {
		// Starting server by automation using Class AppiumServiceBuilder by using its
		// method withApiumJs
		serviceBuilder = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C://Users//Windows 11//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		serviceBuilder.start();
		// Configuring the mobile device name and apk file using XCUITestOptions so
		// the server communicates to that mobile and perform operation
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 15 Pro");
		options.setApp("C://UsersWindows 11//Documents//Appium Mobile Automation//UIKitCatalog.app");
		options.setPlatformVersion("15.5");
		// Appium install webdriver agent -> agent helps automate IOS apps
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		URL url = new URL("http://127.0.0.1:4723/");
		driver = new IOSDriver(url, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
	}

	// Method for Long Press event using Java script executor

	public void longPressAction(WebElement ele) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("Duration", 5);
		driver.executeScript("mobile:touchAndHold", params);
	}

	// Method for scrollToElement event using Java script executor

	public void scrollToElement(WebElement ele) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("direction", "down");
		driver.executeScript("mobile:scroll", params);
	}
	
	// Method to swipe left or right to the next photo to its center 
	public void swipeAction(String dir) {

	Map<String , String> params = new HashMap<String, String>();
	params.put("direction",dir);

	driver.executeScript("mobile:swipe", params);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
		serviceBuilder.stop();
	}

}
