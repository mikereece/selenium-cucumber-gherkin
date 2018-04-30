package com.example.selenium.webdriver.configuration;

import java.util.Properties;
import java.util.logging.Level;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.example.selenium.configuration.BaseConfiguration;
import com.example.selenium.webdriver.BrowserType;

/**
 * Class to configure the WebDriver and browser based on properties
 * 
 * @author michaere
 *
 */
public class WebDriverConfiguration extends BaseConfiguration {

	private static final String PROPERTIES_FILE_PATH = "src/main/resources/webdriver.properties";

	// General browser constants
	private static String BROWSER;

	private static Boolean BROWSER_WINDOW_MAXIMISE;
	private static Dimension BROWSER_WINDOW_SIZE;
	private static Integer BROWSER_WINDOW_WIDTH;
	private static Integer BROWSER_WINDOW_HEIGHT;

	// Chrome specific constants
	private static String CHROME_DRIVER_ROOT_DIR;
	private static String CHROME_DISABLE_EXTENSIONS;
	private static String CHROME_DISABLE_WEB_SECURITY;

	// Firefox specific constants
	private static String FIREFOX_DRIVER_ROOT_DIR;
	private static Boolean FIREFOX_ACCEPT_SSL_CERTS;
	private static Boolean FIREFOX_DISABLE_RESTORE_SESSION_STATE;

	// IE specific constants
	private static Boolean IE_IGNORE_SECURITY_DOMAINS;
	private static String IE_VERSION;

	private static Properties properties;

	/**
	 * Method which loads the properties and sets them into constants
	 */
	private static void setConfiguration() {
		properties = loadProperties(PROPERTIES_FILE_PATH);

		// Generic WebDriver and Browser properties
		BROWSER = properties.getProperty("browser");

		BROWSER_WINDOW_WIDTH = Integer.parseInt(properties.getProperty("browser.width"));
		BROWSER_WINDOW_HEIGHT = Integer.parseInt(properties.getProperty("browser.height"));
		BROWSER_WINDOW_SIZE = new Dimension(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);

		BROWSER_WINDOW_MAXIMISE = Boolean.valueOf(properties.getProperty("browser.maximise"));

		// Chrome browser properties
		CHROME_DRIVER_ROOT_DIR = properties.getProperty("chrome.driver.root.dir");
		CHROME_DISABLE_EXTENSIONS = properties.getProperty("chrome.disable.extensions");
		CHROME_DISABLE_WEB_SECURITY = properties.getProperty("chrome.disable.web.security");

		// Firefox browser properties
		FIREFOX_DRIVER_ROOT_DIR = properties.getProperty("firefox.driver.root.dir");
		FIREFOX_ACCEPT_SSL_CERTS = Boolean.valueOf(properties.getProperty("firefox.accept.ssl.certs"));
		FIREFOX_DISABLE_RESTORE_SESSION_STATE = Boolean
				.valueOf(properties.getProperty("firefox.disable.restore.session.state"));

		// IE browser properties
		IE_IGNORE_SECURITY_DOMAINS = Boolean
				.valueOf(properties.getProperty("ie.introduce.flakiness.ignore.security.domains"));
		IE_VERSION = properties.getProperty("ie.version");

	}

	/**
	 * Method to create and return the WebDriver based on the loaded
	 * configuration properties
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getWebDriverForBrowser() {
		setConfiguration();

		WebDriver driver = null;
		BrowserType browserType = Enum.valueOf(BrowserType.class, BROWSER);

		// Determine the WebDriver type based on the property set
		switch (browserType) {
		case IE:
			driver = new InternetExplorerDriver(getIEDesiredCapabilities());
			break;
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_ROOT_DIR);

			driver = new FirefoxDriver(getFireFoxDesiredCapabilities());
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_ROOT_DIR);

			driver = new ChromeDriver(getChromeDesiredCapabilities());
			break;
		}

		// Set browser specific properties
		setBrowserProperties(driver);

		return driver;
	}

	/**
	 * Method to set browser display properties
	 * 
	 * @param webDriver
	 */
	private static void setBrowserProperties(WebDriver webDriver) {
		webDriver.manage().window().setSize(BROWSER_WINDOW_SIZE);

		if (BROWSER_WINDOW_MAXIMISE.booleanValue()) {
			webDriver.manage().window().maximize();
		}
	}

	/**
	 * Method to set functional properties for a Chrome Browser
	 * 
	 * @return DesiredCapabilities
	 */
	private static DesiredCapabilities getChromeDesiredCapabilities() {

		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.ALL);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments(CHROME_DISABLE_EXTENSIONS);
		chromeOptions.addArguments(CHROME_DISABLE_WEB_SECURITY);

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		return capabilities;
	}

	/**
	 * Method to set functional properties for a Firefox Browser
	 * 
	 * @return DesiredCapabilities
	 */
	private static DesiredCapabilities getFireFoxDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, FIREFOX_ACCEPT_SSL_CERTS.booleanValue());
		capabilities.setCapability("marionette", true);
		capabilities.setCapability("disable-restore-session-state",
				FIREFOX_DISABLE_RESTORE_SESSION_STATE.booleanValue());

		return capabilities;

	}

	/**
	 * Method to set functional properties for a IE Browser
	 * 
	 * @return DesiredCapabilities
	 */
	private static DesiredCapabilities getIEDesiredCapabilities() {
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);

		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				IE_IGNORE_SECURITY_DOMAINS.booleanValue());
		capabilities.setVersion(IE_VERSION);

		return capabilities;
	}
}
