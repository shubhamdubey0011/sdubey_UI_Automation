package com.app.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    Properties properties = new Properties();
    InputStream inputStream = null;

    public PropertyReader() 
    {
        loadProperties();
    }

    private void loadProperties() {
        try 
        {
        	//•To load Properties from property files into java Properties object.
            inputStream = new FileInputStream("D:/WorkSpace/WebAutomations_/DesktopAutomation/src/test/resources/com/app/TestData/config.properties");
            properties.load(inputStream);
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
