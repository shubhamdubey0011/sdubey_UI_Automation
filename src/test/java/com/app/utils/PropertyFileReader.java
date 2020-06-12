package com.app.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

public class PropertyFileReader {
	
	public static void fileReader() {
	Properties prop = new Properties();
	FileInputStream input;
	Path filePath;
	
	String locatorsDirPath = System.getProperty("user.dir");
	
	try {
		Stream<Path> filePathStream = Files.walk(Paths.get(locatorsDirPath));
		Iterator<Path> itr = filePathStream.iterator();
		while (itr.hasNext()) {
			filePath = itr.next();
			if (Files.isRegularFile(filePath) && filePath.toString().split("\\.")[1].equals("properties")) {
				System.out.println("reading file....."+filePath.toString());
				input = new FileInputStream(filePath.toFile());
				prop.load(input);
				Set<String> name = prop.stringPropertyNames();
				Iterator<String> allKeys = name.iterator();
				while (allKeys.hasNext()) {
					String key = allKeys.next().toString();
					String value = prop.getProperty(key);
					System.setProperty(key, value);
					//System.out.println(System.setProperty(key, value));
					
				}
				prop.clear();
			}
		}
		filePathStream.close();
	} catch (IOException e) {
	}


	}


}