package com.example.selenium.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Base Class containing reusable methods for setting and getting configuration
 * properties
 * 
 * @author michaere
 *
 */
public abstract class BaseConfiguration {

	/**
	 * Loads a properties file from a given path
	 * 
	 * @param propertiesFile
	 * @return
	 */
	public static Properties loadProperties(String propertiesFile) {
		Properties properties = null;
		try {
			properties = new Properties();
			properties.load(new FileInputStream(propertiesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
