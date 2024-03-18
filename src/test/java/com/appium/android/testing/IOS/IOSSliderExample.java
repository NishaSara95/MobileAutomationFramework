package com.appium.android.testing.IOS;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSSliderExample extends IOSBaseClass {

	
	@Test
	public void slideTestDemo() throws InterruptedException {
		//A dummy app (TestApp 3.app) can be used  to slide to the corner and toggle on and off using appium
		WebElement slider = driver.findElement(AppiumBy.xpath("//XCUIElementTypeSlider[name='AppElem']"));
		slider.sendKeys("0%");
		System.out.println(slider.getAttribute("value"));
		Thread.sleep(2000);
		slider.sendKeys("1%");
		System.out.println(slider.getAttribute("value"));

	}
	
	
	
}
