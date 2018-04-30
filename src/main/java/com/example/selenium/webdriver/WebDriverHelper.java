package com.example.selenium.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.selenium.webdriver.configuration.WebDriverConfiguration;

/**
 * Class to the basic actions of the Web Driver - create, close, clean up. The
 * class methods will be utilised by each Cucumber (Step Definition) Test
 * 
 * @author michaere
 *
 */
public class WebDriverHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverHelper.class);

	private static WebDriver mDriver;

	/**
	 * Method to generate the WebDriver based on the configuration properties
	 * 
	 * @return WebDriver
	 */
	public static synchronized WebDriver getCurrentDriver() {
		if (mDriver == null) {
			try {
				mDriver = WebDriverConfiguration.getWebDriverForBrowser();
			} finally {
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}
		}
		return mDriver;
	}

	/**
	 * Method to Close the Browser
	 * 
	 * @author michaere
	 *
	 */
	private static class BrowserCleanup implements Runnable {
		public void run() {
			LOGGER.info("Closing the browser");
			close();
		}
	}

	/**
	 * Method to clean down and nullify the WebDriver
	 */
	public static void close() {
		try {
			getCurrentDriver().quit();
			mDriver = null;
			LOGGER.info("closing the browser");
		} catch (UnreachableBrowserException e) {
			LOGGER.info("cannot close browser: unreachable browser");
		}
	}

}
