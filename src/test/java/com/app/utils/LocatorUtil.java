package com.app.utils;

import org.openqa.selenium.By;

public class LocatorUtil {

	public static String secondPart = null;

	public static By getBy(String locator) {
		/**
		 * Get locators by using dual locator strategy
		 */
		locator = System.getProperty(locator);
		secondPart = null;
		if (locator.startsWith("//")) {
			return By.xpath(locator);
		} else if (locator.indexOf("=") > 0) {
			if (locator.contains("||")) {
				secondPart = locator.split("\\|\\|")[1];
				locator = locator.split("\\|\\|")[0];
			}
			String parts[] = locator.split("=", 2);
			if (parts[0].equalsIgnoreCase("name")) {
				return By.name(parts[1]);
			} else if (parts[0].equalsIgnoreCase("id")) {
				return By.id(parts[1]);
			} else if (parts[0].equalsIgnoreCase("xpath")) {
				return By.xpath(parts[1]);
			} else if (parts[0].equalsIgnoreCase("css")) {
				return By.cssSelector(parts[1]);
			} else if (parts[0].equalsIgnoreCase("link") || parts[0].equalsIgnoreCase("linkText")) {
				return By.linkText(parts[1]);
			} else if (parts[0].equalsIgnoreCase("partialLink") || parts[0].equalsIgnoreCase("partialLinkText")) {
				return By.partialLinkText(parts[1]);
			} else if (parts[0].equalsIgnoreCase("className")) {
				return By.className(parts[1]);
			} else if (parts[0].equalsIgnoreCase("tagName")) {
				return By.tagName(parts[1]);
			} else if (parts[0].equalsIgnoreCase("content-desc")) {
				return By.xpath(String.format("//*[@content-desc='%s']", parts[1]));
			} else {
				return By.xpath(String.format("//*[@name='%s' or @id='%s' or @value='%s']", locator, locator, locator));
			}
		} else {
			return By.xpath(String.format("//*[@name='%s' or @id='%s' or @value='%s']", locator, locator, locator));
		}
	}
	
	/*
	 * public static void main(String[] args) {
	 * System.out.println(LocatorUtil.getBy("css=.btn.buy")); }
	 */
}
