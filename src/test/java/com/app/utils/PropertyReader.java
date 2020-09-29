package com.app.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;

public class PropertyReader {

	Properties properties = new Properties();
	InputStream inputStream = null;

	public PropertyReader() {
		loadProperties();
	}

	private void loadProperties() {
		try {
			// •To load Properties from property files into java Properties object.
			inputStream = new FileInputStream(
					"D:/WorkSpace/WebAutomations_/DesktopAutomation/src/test/resources/com/app/TestData/config.properties");
			properties.load(inputStream);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readProperty(String key) {
		return properties.getProperty(key);
	}

	public static Map<String, String> getDriverCapabilities(String platform) {
		/**
		 * Get Driver compatibilities from properties files present in resource folder
		 * according to the platform passed in parameter
		 */
		Properties prop = new Properties();
		InputStream input = null;

		Map<String, String> map = new HashMap<String, String>();
		try {
			input = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/configFiles/" + platform + ".properties");
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				map.put(key, value);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		System.out.println("Driver Capablities are :- " + map);
		return map;
	}

	public static void loadLocators(String driverName) {
		/**
		 * Get platform(android/ios) locators from properties files present in resource
		 * folder according to the driver passed in parameter
		 */
		Properties prop = new Properties();
		// String locatorsDirPath = System.getProperty("user.dir") +
		// "/src/test/resources/" + driverName + "_locators";

		String locatorsDirPath;

		if (driverName.equalsIgnoreCase("ios") || driverName.equalsIgnoreCase("android")) {
			locatorsDirPath = System.getProperty("user.dir") + "/src/test/resources/" + driverName + "_locators";
		} else {
			locatorsDirPath = System.getProperty("user.dir") + "/src/test/resources/web_locators";
		}

		try (Stream<Path> filePathStream = Files.walk(Paths.get(locatorsDirPath))) {
			filePathStream.forEach(filePath -> {
				if (Files.isRegularFile(filePath) && FilenameUtils.isExtension(filePath.toString(), "properties")) {
					InputStream input = null;

					try {
						input = new FileInputStream(filePath.toFile());
						prop.load(input);
						Enumeration<?> e = prop.propertyNames();
						while (e.hasMoreElements()) {
							String key = (String) e.nextElement();
							String value = prop.getProperty(key);
							System.setProperty(key, value);
						}
					} catch (IOException ex) {
						// TODO Auto-generated catch block
					} finally {
						if (input != null) {
							try {
								input.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}
	}
}
