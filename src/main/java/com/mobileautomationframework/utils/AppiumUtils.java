package com.mobileautomationframework.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils {

	public AppiumDriverLocalService serviceBuilder;

	/**
	 * This method shows the basics of appium adding the UI automator 2 and adding
	 * the desired capabilities
	 * 
	 * @return
	 * 
	 * @throws MalformedURLException Using Url class to add the default port url of
	 *                               appium Using UIAutomator2Options for adding the
	 *                               device onfiguration such as Device name,
	 *                               apkfile and other details etc
	 */

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) throws MalformedURLException {
		// Starting server by automation using Class AppiumServiceBuilder by using its
		// method withApiumJs
		serviceBuilder = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C://Users//Windows 11//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress(ipAddress).usingPort(port).withTimeout(Duration.ofSeconds(500)).build();

		serviceBuilder.start();

		System.out.println("Server started successfully");

		return serviceBuilder;

	}

	public void stopAppium() {

		serviceBuilder.stop();

	}

	/**
	 * Common method to wait for Page load
	 * 
	 * @param ele
	 * @param text
	 * @param value
	 */
	public void waitForPageLoad(WebElement ele, String text, String value, AppiumDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(ele, text, value));

	}

	// Method to getTestDataFRomJsonFile
	@SuppressWarnings("deprecation")
	public List<HashMap<String, String>> getJsonTestData(String path) throws IOException {

		// The json is getting conveted to Json string using File Utils class from
		// Common IO dependancy
		String fileString = FileUtils.readFileToString(new File(path));
		// To map Json string to Hash Map we need Object Mapper
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(fileString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	// Method to get screenshot in case of test failures
	public  String getScreenShot(AppiumDriver driver,String testCaseName) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
}
