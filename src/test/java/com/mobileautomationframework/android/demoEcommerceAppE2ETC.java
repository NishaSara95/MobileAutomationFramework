package com.mobileautomationframework.android;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.google.common.collect.ImmutableMap;
import com.mobileautomationframework.pageObjects.android.CartPage;
import com.mobileautomationframework.pageObjects.android.LoginPage;
import com.mobileautomationframework.pageObjects.android.ProductPage;
import com.mobileautomationframework.testUtils.androidBaseTest;



public class demoEcommerceAppE2ETC extends androidBaseTest {

	LoginPage login;
	CartPage cartPage;

	
//	@BeforeMethod()
//	public void preSetup() throws InterruptedException {
//		login = new LoginPage(driver);
//		login.preSetup();
//	}
//	
	
	@Test(priority = 0, groups= {"smoke", "sanity"})
	public void enterblankUserLogin() throws InterruptedException {

		login = new LoginPage(driver);
		login.submitLetsShop();
		String toastMessage = login.veriyToastMessage();
		Assert.assertEquals(toastMessage, "Please enter your name");
		
		
	}
		
	/**
	 * TC: 2 This is the E2E TC from logging in with user name and selecting products adding to cart asserting the purcahse amount
	 * 
	 * @throws InterruptedException
	 */

	@Test(priority = 1, dataProvider = "userLoginData", groups= {"sanity"})
	public void enterValidLogin(HashMap<String, String>input) throws InterruptedException {

		login = new LoginPage(driver);
		//String toastMessage = login.veriyToastMessage();
		//Assert.assertEquals(toastMessage, "Please enter your name");
		login.setUserName(input.get("name"));
		login.selectGender(input.get("gender"));
		login.selectCountryName(input.get("country"));
		ProductPage productPage = login.submitLetsShop();
		productPage.addToCartByIndex(0);
		productPage.addToCartByIndex(1);
		productPage.goToCartPage();
		 cartPage = new CartPage(driver);
		 cartPage.selectCheckBox();
		Double sumOfIdividualProductPrice = cartPage.sumOfIdividualProductPrice();
		Double totalPurchasePrice = cartPage.getTotalPurchasePrice();
		Assert.assertEquals(sumOfIdividualProductPrice, totalPurchasePrice);
		String termsAndConditionTitle = cartPage.longPressTermsAndConditionAndGetTitle();
		Assert.assertEquals(termsAndConditionTitle, "Terms Of Conditions");
		cartPage.closeTermsBoxAndProceed();
		

	
	}
	
	@DataProvider
	public Object[] [] userLoginData() throws IOException{
		
		List<HashMap<String, String>>data = getJsonTestData(System.getProperty("user.dir")+"//src//test//java//com//mobileautomationframework//testdata//ecommerceTestData.json");
		//return new Object[][]{ {data.get(0),data.get(1)}};
		//"Nisha","female","Australia"},{"Sara","female","Argentina"}};
		return new Object[][]{ {data.get(0)}};

	};


	

}
