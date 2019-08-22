package pom.env;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.base.Base;

public class Enviroment extends Base {

	public static String getBrowserName() {
		browserName = System.getProperty("browser");
		return browserName;
	}

	// Create WebDriver
	public static WebDriver CreateWebDriver(String browser) {
		System.out.println("Browser: " + browser);
		switch (browser.toLowerCase()) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "internetexplorer":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Invalid browser name " + browser);
			System.exit(0);
			break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	// close the Driver
	public static void closeDriver() {
		if (driver != null) {
			try {
				driver.manage().deleteAllCookies();
				driver.close();
				driver.quit();
			} catch (NoSuchMethodError nsme) {
			} catch (NoSuchSessionException nsse) {
			} catch (SessionNotCreatedException snce) {
			}
			driver = null;
		}
	}
}
