package com.mobileautomationframework.pageObjects.android;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.mobileautomationframework.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends AndroidActions {

	AndroidDriver driver;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"]")
	private List<WebElement> addToCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement addToCartBtn;

	public ProductPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/** Method to select products by index to add to cart
	 * 
	 * @param index
	 * @throws InterruptedException
	 */
	public void addToCartByIndex(int index) throws InterruptedException {

		addToCart.get(index).click();
		System.out.println("Product added to cart");
		Thread.sleep(1000);

	}

	/** Method to go To Cart Page
	 * @return 
	 * 
	 * @throws InterruptedException
	 */
	public void goToCartPage() throws InterruptedException {

		addToCartBtn.click();
		Thread.sleep(1000);
	
	}

}
