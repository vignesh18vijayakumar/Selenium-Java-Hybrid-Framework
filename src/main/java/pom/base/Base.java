package pom.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import pom.env.Enviroment;
import pom.methods.BaseMethod;
import pom.util.Util;

public class Base extends Util implements BaseMethod {

	public Base() {
		try {
			data = new Properties();
			xpath = new Properties();
			FileInputStream ip = new FileInputStream("./data.properties");
			FileInputStream ip1 = new FileInputStream("./xpath.properties");
			data.load(ip);
			xpath.load(ip1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Initialization() {
		startExtentReports("openSelectedBrowser");
		browserName = data.getProperty("browser");
		driver = Enviroment.CreateWebDriver(browserName);
		extentInfo("Open browser successfully");
		System.out.println("Open browser successfully");
		navigationObj.navigateTo(data.getProperty("url"));
		extentInfo("Open Application successfully: " + driver.getTitle());
		System.out.println("Open Application successfully: " + driver.getTitle());
		stopExtentReport();
	}
}