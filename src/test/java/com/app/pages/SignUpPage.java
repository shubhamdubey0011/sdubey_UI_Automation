package com.app.pages;

import org.testng.Assert;

import com.app.locators.LandingPageLoc;
import com.app.locators.SignUpPageLoc;
import com.app.utility.BaseConfiguration;

public class SignUpPage extends BaseConfiguration implements SignUpPageLoc,LandingPageLoc{
   
	public void clickOnSignInButton()
   {
	   waitforPresent(SIGN_IN_LABLE);
	   clickOnElement(SIGN_IN_LABLE);
   }
	
	public void verifyLabels()
	{
		waitforPresent(LABEL_CREATE_ACCOUNT_BUTTON);
	 Assert.assertTrue(verifytext(LABEL_CREATE_ACCOUNT_BUTTON, "CREATE AN ACCOUNT"));
	}
}
