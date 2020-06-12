package com.app.pages;

import org.testng.Assert;

import com.app.locators.LandingPageLoc;
import com.app.locators.ShoppingCartPageLoc;
import com.app.messages.ShoppingCartMessages;
import com.app.utils.BaseConfiguration;

public class ShoppingCartPage extends BaseConfiguration
		implements ShoppingCartPageLoc, LandingPageLoc, ShoppingCartMessages {
   
	public void clickOnSignInButton()
   {
	   waitforPresent(SIGN_IN_LABLE);
	   clickOnElement(SIGN_IN_LABLE);
   }
	
	public void verifyShoppingCartHeader()
	{
		waitforPresent(SHOPPINGCART_PAGE_HEADING);
	 Assert.assertTrue(verifytext(SHOPPINGCART_PAGE_HEADING, SHOPPINGCART_PAGE_HEADER));
	}
}
