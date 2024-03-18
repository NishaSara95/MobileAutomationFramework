package com.mobileautomationframework.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;
import com.mobileautomationframework.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends AndroidActions {

	AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.androidsample.generalstore:id/nameField']")
	private WebElement nameText;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement maleRadioBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Australia\"]")
	private WebElement australiaCountry;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopBtn;

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private WebElement toastMessage;

	/** Method to verify Toast message for clicking LetsShop button without entering the user name
	 * 
	 */
	
	public String veriyToastMessage() {
		letsShopBtn.click();	
		return driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		
		
	}

	/**
	 * Method to enter User name in the name field
	 * 
	 * @param name
	 * @throws InterruptedException
	 */
	public void setUserName(String name) throws InterruptedException {
		Thread.sleep(10000);
		nameText.sendKeys(name);
		driver.hideKeyboard();

	}

	/**
	 * Method to select selectGender
	 * 
	 */
	public void selectGender(String gender) {
		if (gender.equalsIgnoreCase("female")) {
			femaleRadioBtn.click();
		} else {
			maleRadioBtn.click();
		}
	}

	/**
	 * Method to select select country name from dropdown
	 * 
	 */
	public void selectCountryName(String country) {
		countryDropdown.click();
		ScrollIntoView(country);
		WebElement selectCountry = driver.findElement(
				By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text='" + country + "']"));
		selectCountry.click();
	}

	/**
	 * Method to click LetsShop button
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public ProductPage submitLetsShop() throws InterruptedException {
		Thread.sleep(5000);
		letsShopBtn.click();
		Thread.sleep(1000);
		return new ProductPage(driver);
	}

	public void preSetup() throws InterruptedException {
		Thread.sleep(1000);
	((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
}
}
