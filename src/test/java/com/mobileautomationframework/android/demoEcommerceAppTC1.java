package com.mobileautomationframework.android;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mobileautomationframework.pageObjects.android.CartPage;
import com.mobileautomationframework.pageObjects.android.LoginPage;
import com.mobileautomationframework.testUtils.androidBaseTest;
import com.mobileautomationframework.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import jdk.internal.org.jline.utils.Log;

public class demoEcommerceAppTC1 extends androidBaseTest {




	/*
	 * TC: 1 To select Lets cart button and assert the toast message To select the
	 * country from dropdown by scrolling to the country and entering Name and
	 * clicking on Lets shop button
	 */

	LoginPage login;

	
	@Test(priority = 0, groups= {"sanity"})
	public void enterblankUserLogin() throws InterruptedException {

		login = new LoginPage(driver);
		login.submitLetsShop();
		String toastMessage = login.veriyToastMessage();
		Assert.assertEquals(toastMessage, "Please enter your name");
		
		
	}
	}
