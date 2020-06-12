package com.app.tests;

import org.testng.annotations.Test;

import com.app.utils.BaseConfiguration;


public class Demo_1 extends BaseConfiguration {
	// static String driverPath =
	// "D:/WorkSpace/WebAutomations_/DesktopAutomation/src/test/resources/drivers/";

	@Test
	public void test1() {
		openApplication();
	}

	/*
	 * public static void launchBrowser() {
	 * System.out.println("launching browser...");
	 * //System.setProperty("webdriver.chrome.driver", driverPath +
	 * "chromedriver.exe");
	 * //"By using webDriver manager to automate the download the relevant binaries/executables"
	 * //WebDriverManager.chromedriver().setup(); //driver = new ChromeDriver(); new
	 * basePage().initialize(); }
	 */

	public void openApplication() {
		// driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		openApplicationUnderTest();
		System.out.println("browser launched");
		//new Driver().closeDriver();
		//System.out.println("browser closed");
		

	}

}
