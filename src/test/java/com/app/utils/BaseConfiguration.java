package com.app.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseConfiguration extends Driver{

	//private static final String baseURL = "https://www.amazon.in";
	//private static final String baseURL = "http://newtours.demoaut.com/";
	private static final String baseURL = "https://demo.midtrans.com";
	final static int WAIT_TIME = 60;
	final static int ALERT_WAIT_TIME = 10;
	
	public BaseConfiguration() {
		PageFactory.initElements(driver, this);
	}
	public static String getBaseURL() {
		return baseURL;
	}

	@BeforeClass
	public void openApplicationUnderTest() {
		System.out.println("BeforeClass annotation");
		driver.get(getBaseURL());
		driver.manage().window().maximize();
		System.out.println("Currentl url is :- " + driver.getCurrentUrl());
	}

	public void waitforPresent(String element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(LocatorUtil.getBy(element)));
		} catch (Exception e) {
			if (!LocatorUtil.secondPart.equals(null)) {
				System.setProperty("secondLocator", LocatorUtil.secondPart);
				System.out.println(" or part of the element is :-" + element);
				wait.until(ExpectedConditions.elementToBeClickable(LocatorUtil.getBy("secondLocator")));
			}
		}
	}

	/*
	 * public WebElement findElement(String locator) { try { return
	 * driver.findElement(LocatorUtil.getBy(locator)); } catch (Exception e) { if
	 * (!LocatorUtil.secondPart.equals(null)) { System.setProperty("secondLocator",
	 * LocatorUtil.secondPart); return
	 * driver.findElement(LocatorUtil.getBy("secondLocator")); } } return null; }
	 */

	public WebElement findElement(String locator) {
		return getDriver().findElement(LocatorUtil.getBy(locator));
	}

	public List<WebElement> findElements(String locator) {
		return driver.findElements(LocatorUtil.getBy(locator));
	}

	protected String getDescription(String locator) {
		return System.getProperty(locator, locator);
	}

	public void clickOnElement(String locator) {
		findElement(locator).click();
	}

	public void sendValue(String locator, String value) {
		findElement(locator).sendKeys(value);
	}
	
	public boolean verifytext(String locator, String value) {
		if (findElement(locator).getText().equals(value)) {
			System.out.println(" label is ***********" + findElement(locator).getText());
			return true;
		}
		return false;
	}

	@BeforeSuite
	public void readConfig(ITestContext context) {
		System.out.println("before Suite annotation executed");
		PropertyFileReader.fileReader();
		//System.out.println(context.getCurrentXmlTest().getAllParameters() + "aaaaaaaa");
	}

	/****************************/
	/** EVENTS FOR WEB ELEMENT **/
	/****************************/

	public boolean isPresent(String locator) {
		/**
		 * Verify Element is present in screen
		 */
		Reporter.log("Check present of " + locator);
		List<WebElement> elements = findElements(locator);
		if (elements.size() > 0) {
			Reporter.log("Present " + locator);
			return true;
		}
		Reporter.log("Not present " + locator);
		return false;
	}

	public boolean isDisplayed(String locator, int... timeout) {
		/**
		 * Verify Element is visible in screen
		 */
		try {
			waitForVisible(locator, timeout);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void waitForVisible(String locator, int... timeout) {
		Reporter.log("Wait time out for <b>\"" + getDescription(locator) + "\"</b> to be visible");
		// System.out.println("Wait time out for <b>\"" + getDescription(locator) +
		// "\"</b> to be visible");
		new WebDriverWait(getDriver(), getTimeOut(timeout))
				.until(ExpectedConditions.elementToBeClickable(LocatorUtil.getBy(locator)));
	}

	private int getTimeOut(int... timeout) {
		return timeout.length > 0 ? timeout[0] : WAIT_TIME;
	}

	public void clickThroughJavaScript(Object locator) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if (locator instanceof String) {
			executor.executeScript("arguments[0].click();", findElement((String) locator));
		} else {
			executor.executeScript("arguments[0].click();", (WebElement) locator);
		}
	}

	/*
	 * public boolean isElementPresent(By locator) {
	 * 
	 * if (getDriver().findElements(locator).size() != 0) { // Element present
	 * return true; } else { // Element not present return false; } }
	 */
	public static boolean isElementPresent(By locator) {
		boolean isPresent = false;
		try {
			isPresent = driver.findElement(locator) != null;
		} catch (NoSuchElementException nse) {
		}
		return isPresent;
	}

	public String getLocatorTextValue(String locator) {
		String locatorTextValue = "";
		locatorTextValue = findElement(locator).getText();
		return locatorTextValue;
	}

	public void waitFor(int timeout) {
		getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}



}
