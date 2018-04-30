package com.example.cucumber.stepdefinitions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.example.selenium.configuration.BaseConfiguration;
import com.example.selenium.webdriver.WebDriverHelper;

/**
 * Base Test Class containing reusable WebDriver functions
 * 
 * @author michaere
 *
 */
public abstract class BaseTest {

	/**
	 * Set a wait time following a POST event to ensure the page has fully
	 * rendered before the next operation can be started
	 */
	private static final Long WAIT_TIME = 3L;

	protected static WebDriver webDriver;

	protected static Properties properties;

	/**
	 * This variable is typically set from within the StepDefinition class
	 * itself as a properties file will contain properties which are local to
	 * the Step Definition itself
	 */
	protected static String propertiesFilePath;

	/**
	 * Method to close the browser on test completion
	 */

	/**
	 * Method to navigate to a given URL
	 * 
	 * @param url
	 */
	protected static void navigateToURL(String url) {
		getWebDriver().get(getProperties().getProperty(url));
	}

	/**
	 * Method to instantiate the Web Driver
	 * 
	 * @return WebDriver
	 */
	private static WebDriver getWebDriver() {
		if (webDriver == null) {
			webDriver = WebDriverHelper.getCurrentDriver();
		}
		return webDriver;
	}

	/**
	 * Method to return a properties file required for the particular Step
	 * Definition
	 * 
	 * @return Properties
	 */
	private static Properties getProperties() {
		if (properties == null) {
			properties = BaseConfiguration.loadProperties(propertiesFilePath);
		}
		return properties;
	}

	/**
	 * Method to close the browser on test completion
	 */
	protected static void closeBrowser() {
		WebDriverHelper.close();
		webDriver = null;
	}

	/**
	 * Method to return a WebElement within a web page based on its xPath
	 * 
	 * @param Xpath
	 * @return WebElement
	 */
	protected static WebElement findElementByXpath(String Xpath) {
		return getWebDriver().findElement(By.xpath(getProperties().getProperty(Xpath)));
	}

	/**
	 * Method which adds a time delay when clicking a button which performs a
	 * POST - ensures that the page has reloaded before the next action takes
	 * place
	 */
	protected static void addWait() {
		getWebDriver().manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.SECONDS);
	}

}
