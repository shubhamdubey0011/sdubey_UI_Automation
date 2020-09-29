package com.app.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;

public class Driver {

	public static WebDriver driver;
	// private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	// public static String platform = "chrome";
	public static String platform = new PropertyReader().readProperty("browser");

	@BeforeSuite
	public WebDriver getDriver() {
		if (driver == null) {
			createNewDriverInstance(platform);
		}
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Driver.driver = driver;
	}

	public void createNewDriverInstance(String platform) {
		/**
		 * Create Driver Instance of given desired capabilities from properties file
		 */
		// Read properties file which contains driver capabilities and
		Map<String, String> properties = PropertyReader.getDriverCapabilities(platform);
		String driverName = properties.get("driver.name");
		System.setProperty("driver.name", driverName);
		PropertyReader.loadLocators(driverName);

		if (driverName.equalsIgnoreCase("chrome")) {
			// Creating Chrome driver for Web Automation
			if (properties.containsKey("webdriver.chrome.driver")) {
				System.setProperty("webdriver.chrome.driver", properties.get("webdriver.chrome.driver"));
			}

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--disable-web-security=\"C:/ChromeDevSession\"");
			// options.addArguments("--allow-running-insecure-content");
			options.merge(capabilities);
			driver = new ChromeDriver(options);
			// Launch Base URL.
			driver.get(properties.get("env.baseurl"));
			driver.manage().window().maximize();
		} else if (driverName.equalsIgnoreCase("android")) {
			// Creating Android driver for Android Automation
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

			ObjectMapper mapper = new ObjectMapper();
			String capabilities = properties.get("appium.additional.capabilities");

			Map<String, Object> map = new HashMap<String, Object>();
			try {
				map = mapper.readValue(capabilities, new TypeReference<Map<String, Object>>() {
				});
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String value = entry.getValue().toString();
					if (entry.getValue().toString().startsWith("$")) {
						String systemValue = System.getProperty(entry.getKey());
						if (systemValue != null) {
							value = systemValue;
						}
					}
					desiredCapabilities.setCapability(entry.getKey(), value);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				driver = new AndroidDriver<WebElement>(new URL(properties.get("appium.server")), desiredCapabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Create Driver Instance for android of given desired capabilities from
		 * properties file
		 */

	}

	public static String getBrowserInfo() {
		/**
		 * Get browser information
		 */
		Capabilities cap = ((RemoteWebDriver) (WebDriver) Driver.driver).getCapabilities();
		String b = cap.getBrowserName();
		String os = cap.getPlatform().toString();
		String v = cap.getVersion();
		System.out.println(String.format("Capabilities: %s v:%s %s", b, v, os));
		return String.format("Capabilities: %s v:%s %s", b, v, os);

	}

	@AfterClass
	public void destroyDriver() {
		driver.quit();
		driver = null;
	}

	public void closeDriver() {
		if (driver != null) {
			System.out.println("afterClass executed");
			System.out.println("Closing the browser");
			driver.close();
		}
	}

}
