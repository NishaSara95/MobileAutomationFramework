package com.appium.android.testing.IOS;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSBasicExamples extends IOSBaseClass {

	/*
	 * Basic Test method to know more about IOS locators such as iOSClassChain, iOSNsPredicateString
	 */
	
	@Test
	public void iosBasics() {
		
		
		// Locators for IOS devices: xpath, classname, id, accessibility id, iosClassChain, IOS PredicateString 
		driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		// Xpath is very slow in IOS so iosClassChain comes up
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label=='Text Entry'`]")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell")).sendKeys("Nisha");
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name=='A message should be a short, complete sentence'`]")).isDisplayed();
		String message = driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'A message'")).getText();
		System.out.println("Message is" +message);
		driver.findElement(AppiumBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")).click();
		driver.findElement(AppiumBy.iOSNsPredicateString("label=='Confirm'")).click();

	
	
	
	}
}
