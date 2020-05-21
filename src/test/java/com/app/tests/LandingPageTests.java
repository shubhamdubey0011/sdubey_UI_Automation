package com.app.tests;

import org.testng.annotations.Test;

import com.app.pages.LandingPage;
import com.app.utility.BaseConfiguration;



public class LandingPageTests extends BaseConfiguration {

	@Test(description = "verify User is  able to perform a successful transaction", enabled = true, priority = 1)
	public void testSuccessfulTransaction() {
		LandingPage landingPage = new LandingPage();
		landingPage.clickOnBuyNowBTN();
		landingPage.clickOnCheckoutBTN();
		landingPage.clickOnContinueBTN();
		landingPage.selectPaymentViaCreditCardOption();
		landingPage.enterCreditCardNumber("4811 1111 1111 1114");
		landingPage.enterCreditCardExpDate("0520");
		landingPage.enterCreditCardCvvNumber("123");
		landingPage.selectDeselectPromoCheckbox(false);
		landingPage.clickOnPaynowBTN();
		landingPage.enterOTP("112233");
		landingPage.clickOnOKButton();
		landingPage.verifySuccessfulTransaction("Thank you for your purchase.");

	}

	@Test(description = "verify Unsuccessful transaction", enabled = true, priority = 2)
	public void testFailedTransaction() {
		LandingPage landingPage = new LandingPage();
		landingPage.clickOnBuyNowBTN();
		landingPage.clickOnCheckoutBTN();
		landingPage.clickOnContinueBTN();
		landingPage.selectPaymentViaCreditCardOption();
		landingPage.enterCreditCardNumber("4911 1111 1111 1113");
		landingPage.enterCreditCardExpDate("0520");
		landingPage.enterCreditCardCvvNumber("123");
		landingPage.selectDeselectPromoCheckbox(true);
		landingPage.clickOnPaynowBTN();
		landingPage.enterOTP("112233");
		landingPage.clickOnOKButton();
		landingPage.verifyFailedTransaction("Transaction failed");

	}

}
