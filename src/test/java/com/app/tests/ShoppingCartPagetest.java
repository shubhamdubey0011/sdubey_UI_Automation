package com.app.tests;

import org.testng.annotations.Test;

import com.app.pages.LandingPage;
import com.app.pages.ShoppingCartPage;
import com.app.utils.BaseConfiguration;

public class ShoppingCartPagetest extends BaseConfiguration{
	ShoppingCartPage shpcart = new ShoppingCartPage();
	LandingPage landingPage = new LandingPage();
	
	@Test(description = "verify Customer details entered", enabled = true, priority = 1)
	public void testEnteredCustomerDetails() {

		landingPage.clickOnBuyNowBTN();
		shpcart.verifyShoppingCartHeader();

	}
	
}
