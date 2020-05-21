package com.app.tests;

import org.testng.annotations.Test;

import com.app.pages.SignUpPage;
import com.app.utility.BaseConfiguration;

public class SignuUpPagetest extends BaseConfiguration{
	SignUpPage	signup=new SignUpPage();
	
	
	@Test(enabled = true,priority = 1)
	public void testUserSignuUp()
	{
	signup.clickOnSignInButton();
	signup.verifyLabels();
	}
}
