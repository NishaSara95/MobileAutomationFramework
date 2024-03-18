package com.appium.android.testing.IOS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSSwipeExample extends IOSBaseClass {

	/**
	 * Test method to trigger swipe element events
	 * 
	 * Important Note: In IOS to automate the inbuilt apps i.e apps which are
	 * installed already we need to get bundle id from developers
	 * 
	 * @throws InterruptedException
	 */

	@Test
	public void swipeElementDemo() throws InterruptedException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("bundleId", "com.apple.mobileslideshow");
		driver.executeScript("mobile:launchApp", params);
		driver.findElement(AppiumBy.iOSNsPredicateString("label=='All Photos'")).click();
		List<WebElement> allPhotos = driver.findElements(By.xpath("//XCUIElementTypeCell[@name='Photo']"));

		driver.findElement(By.xpath("//XCUIElementTypeCell[@name='Photo']")).click();
		for (int i = 0; i < allPhotos.size(); i++) {

			String attributeValue = driver.findElement(By.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name");
			System.out.println("Time stamp is" + attributeValue);
			swipeAction("left");
		}
		driver.navigate().back();

		driver.findElement(AppiumBy.accessibilityId("Albums")).click(); // Clean up script to navigate back to its
																		// original home screen

	}
}
