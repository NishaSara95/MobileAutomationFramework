package com.mobileautomationframework.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mobileautomationframework.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")
	private WebElement emailCheckbox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Terms Of Conditions']")
	private WebElement termsConditionTitle;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CLOSE']")
	private WebElement closeBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmtLabl;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;

	/**
	 * Method to selectCheckBox
	 * 
	 */
	public void selectCheckBox() {
		emailCheckbox.click();
	}

	/**
	 * Method toLongPress and get terms and Condition title
	 * 
	 * @return
	 */

	public String longPressTermsAndConditionAndGetTitle() {
		longPressAction(termsBtn);
		return termsConditionTitle.getText();

	}

	// Method to close the terms and condition box
	public void closeTermsBoxAndProceed() {
		closeBtn.click();

	}

	// Method to get Total price by summing each products
	public Double getTotalPurchasePrice() {
		String totalAmt = totalAmtLabl.getText();
		String[] totalAmt1 = totalAmt.split(" ");
		return Double.parseDouble(totalAmt1[1]);

	}

	// Method to get sum of individual products
	public Double sumOfIdividualProductPrice() {
		int prodsSize = productPrice.size();
		Double totalProdAmt = 0.0;
		for (int i = 0; i < prodsSize; i++) {
			String prodValue = productPrice.get(i).getText();
			System.out.println(prodValue);
			String[] prodValueSplit = prodValue.split("\\$");
			Double productValInt = Double.parseDouble(prodValueSplit[1]);
			totalProdAmt += productValInt;
		}
		return totalProdAmt;

	}

}
