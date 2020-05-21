package com.app.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.app.locators.LandingPageLoc;
import com.app.utility.BaseConfiguration;


public class LandingPage extends BaseConfiguration implements LandingPageLoc{
	public WebDriver driver;
	public void moveToSignInLabel() {
		waitforPresent(SIGN_IN_LABLE);
		Actions action = new Actions(driver);
		action.moveToElement(findElement(SIGN_IN_LABLE)).build().perform();
	}
	
	public void clickOnSignUp() {
		waitforPresent(START_HERE_LABEL);
		clickOnElement(START_HERE_LABEL);
	}
	
	public void clickOnBuyNowBTN() {
		waitforPresent(LANDING_PAGE_BUY_BTN);
		clickOnElement(LANDING_PAGE_BUY_BTN);
	}
	
	public void clickOnCheckoutBTN() {
		waitforPresent(LANDING_PAGE_CHECKOUT_BTN);
		clickOnElement(LANDING_PAGE_CHECKOUT_BTN);
	}
	public void clickOnContinueBTN() {
		/*
		 * if (isDisplayed(LANDING_PAGE_ORDER_SUMMARY_CONTINUE_BTN, 15)) {
		 * clickOnElement(LANDING_PAGE_ORDER_SUMMARY_CONTINUE_BTN); }
		 */

		// WebElement frame = getDriver().findElement(By.cssSelector("#application"));
		getDriver().switchTo().frame(0);
		waitforPresent(LANDING_PAGE_ORDER_SUMMARY_CONTINUE_BTN);
		// waitForVisible(LANDING_PAGE_ORDER_SUMMARY_CONTINUE_BTN, 20);
		waitFor(20);
		clickOnElement(LANDING_PAGE_ORDER_SUMMARY_CONTINUE_BTN);


		// JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		// executor.executeScript('document.querySelector('a.button-main-content').click()');

		// String link =
		// getDriver().findElement(By.linkText("#/select-payment")).getAttribute("href");
		// getDriver().get(link);

		/*
		 * if (isElementPresent(By.xpath("//*[text()='Continue']//parent::div]"))) {
		 * clickOnElement("//*[text()='Continue']//parent::div]");
		 * System.out.println("clicked on continue button"); }
		 */

	}
	
	public void verifyOrdersummaryPageTitle(String titeText) {
		String title = getLocatorTextValue(LANDING_PAGE_ORDER_SUMMARY_POPUP);
		System.out.println(title);
	}

	public void selectPaymentViaCreditCardOption() {
		waitforPresent(LANDING_PAGE_CREDIT_CARD_PAYMENT_OPT);
		clickOnElement(LANDING_PAGE_CREDIT_CARD_PAYMENT_OPT);
	}

	public void enterCreditCardNumber(String cardNumber) {
		if (isDisplayed(LANDING_PAGE_CREDIT_CARD_NUMBER, 20)) {
			sendValue(LANDING_PAGE_CREDIT_CARD_NUMBER, cardNumber);
		} else
			System.out.println("element" + getDescription(LANDING_PAGE_CREDIT_CARD_NUMBER));
	}

	public void enterCreditCardExpDate(String expiryDate) {
		if (isDisplayed(LANDING_PAGE_CREDIT_CARD_EXPIRY_DATE, 20)) {
			sendValue(LANDING_PAGE_CREDIT_CARD_EXPIRY_DATE, expiryDate);
		} else
			System.out.println("element" + getDescription(LANDING_PAGE_CREDIT_CARD_EXPIRY_DATE));
	}

	public void enterCreditCardCvvNumber(String cvvNumber) {
		if (isDisplayed(LANDING_PAGE_CREDIT_CARD_CVV_NUMBER, 20)) {
			sendValue(LANDING_PAGE_CREDIT_CARD_CVV_NUMBER, cvvNumber);
		} else
			System.out.println("element" + getDescription(LANDING_PAGE_CREDIT_CARD_CVV_NUMBER));
	}

	public void selectDeselectPromoCheckbox(boolean checkedUnchecked) {
		// waitforPresent(LANDING_PAGE_MIDTRANS_PROMO_CHECKBOX);
		if (getDriver().findElement(By.xpath("//*[text()='Midtrans Promo']/../..")).isSelected() == false) {
			getDriver().findElement(By.xpath("//*[text()='Midtrans Promo']/../..")).click();
		}
	}

	public void clickOnPaynowBTN() {
		waitForVisible(LANDING_PAGE_CREDIT_PAY_NOW_BUTTON, 30);
		clickOnElement(LANDING_PAGE_CREDIT_PAY_NOW_BUTTON);
	}

	public void enterOTP(String otp) {
		getDriver().switchTo().frame(0);
		if (isDisplayed(LANDING_PAGE_OTP_TEXTBOX, 30)) {
			sendValue(LANDING_PAGE_OTP_TEXTBOX, otp);
		} else
			System.out.println("unable to locate element " + getDescription(LANDING_PAGE_OTP_TEXTBOX));
	}

	public void clickOnOKButton() {
		waitForVisible(LANDING_PAGE_OTP_OK_BUTTON);
		clickOnElement(LANDING_PAGE_OTP_OK_BUTTON);
	}

	public boolean verifySuccessfulTransaction(String value) {
		getDriver().switchTo().defaultContent();
		if (verifytext(LANDING_PAGE_SUCCESSFUL_TRANSACTION, value)) {
			return true;
		} else
			return false;
	}

	public boolean verifyFailedTransaction(String value) {
		waitForVisible(LANDING_PAGE_FAILED_TRANSACTION, 30);
		if (verifytext(LANDING_PAGE_FAILED_TRANSACTION, value)) {
			return true;
		} else
			return false;
	}

}
