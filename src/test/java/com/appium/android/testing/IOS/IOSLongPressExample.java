package com.appium.android.testing.IOS;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSLongPressExample extends IOSBaseClass{

	// Test method to trigger Long Press events
	@Test
	public void longPressTest() {
		
		driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
		WebElement longPressEle = driver.findElement(AppiumBy.xpath("(XCUIElementTypeButton[@name='Increment'])[3]"));
		longPressAction(longPressEle);
		
		
	}
}
