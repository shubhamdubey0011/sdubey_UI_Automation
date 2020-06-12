package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.app.utils.Driver;
import com.app.utils.LocatorUtil;


public class BasePage extends Driver {
	final static int WAIT_TIME = 60;
	final static int ALERT_WAIT_TIME = 10;

	public BasePage() {
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement findElement(String locator) {
		return getDriver().findElement(LocatorUtil.getBy(locator));
	}

	public List<WebElement> findElements(String locator) {
		return getDriver().findElements(LocatorUtil.getBy(locator));
	}

	private String getDescription(String locator) {
		return System.getProperty(locator, locator);
	}
}
