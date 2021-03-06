package com.app.tests;

import org.testng.annotations.Test;

import com.app.pages.LandingPage;
import com.app.utils.BaseConfiguration;

public class LazsaDemo extends BaseConfiguration {

	@Test(description = "verify title", enabled = true, priority = 1)
	public void testSuccessfulTransaction() {
		LandingPage landingPage = new LandingPage();
		landingPage.clickOnBuyNowBTN();
		landingPage.clickOnCheckoutBTN();
		landingPage.clickOnContinueBTN();
		landingPage.selectPaymentViaCreditCardOption();
		landingPage.enterCreditCardNumber("4811 1111 1111 1114");
		landingPage.enterCreditCardExpDate("0920");
		landingPage.enterCreditCardCvvNumber("123");
		// landingPage.selectDeselectPromoCheckbox(false);
		landingPage.clickOnPaynowBTN();
		landingPage.enterOTP("112233");
		landingPage.clickOnOKButton();
		landingPage.verifySuccessfulTransaction("Thank you for your purchase.");

	}
}
