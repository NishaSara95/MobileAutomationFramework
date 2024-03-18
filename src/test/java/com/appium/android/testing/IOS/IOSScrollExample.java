package com.appium.android.testing.IOS;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSScrollExample extends IOSBaseClass {

	/** Test method to trigger scroll to element events and selecting the option from Dropdown 
	 * In IOS we can select option from dropdown by using sendKeys method
	 * 
	 * @throws InterruptedException
	 */
	
	@Test
	public void scrollToElementDemo() throws InterruptedException {

		WebElement scrollElement = driver.findElement(AppiumBy.accessibilityId("WebView"));
		scrollToElement(scrollElement);
		scrollElement.click();
		Thread.sleep(2000);
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name=='UIKitCatalog'`]")).click();
		// driver.findElement(AppiumBy.iOSNsPredicateString("name=='UIKitCatalog'")).click();
		driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
		 driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'Red color'")).sendKeys("90");
		 driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'Green color'")).sendKeys("210");
		 driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'Blue color'")).sendKeys("170");
		 String BlueColorText = driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'Blue color'")).getText();
		 Assert.assertEquals(BlueColorText, "170");
	}
}
