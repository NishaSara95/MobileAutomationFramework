package com.mobileautomationframework.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AndroidActions extends AppiumUtils {
	
	AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		this.driver=driver;
	}
	
	
	/**
	 * Common method for Long press gestures
	 * 
	 * @param element
	 */
	public void longPressAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	/**
	 * Common method for Scroll till element is visible
	 * 
	 * @param value
	 */

	public void ScrollIntoView(String value) {
		String selectorExpression = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + value + "\"));";
		driver.findElement(AppiumBy.androidUIAutomator(selectorExpression));
	}

	/**
	 * Common method to swipe gesture left or right
	 * 
	 * @param ele
	 * @param direction
	 */
	public void SwipeGesture(WebElement ele, String direction) {

		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", direction, "percent", 0.75));
	}

	/**
	 * Common method for dragging and dropping using element Id , X and Y
	 * coordinates
	 * 
	 * @param ele
	 * @param x
	 * @param y
	 */
	public void dragAndDrop(WebElement ele, int x, int y) {

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", x, "endY", y));
	}

	
	/** Common method to wait for Page load
	 * 
	 * @param ele
	 * @param text
	 * @param value
	 */
	public void waitForPageLoad(WebElement ele, String text, String value) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(ele, text, value));

	}
}
