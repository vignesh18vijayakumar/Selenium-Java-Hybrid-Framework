package pom.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pom.base.Base;
import pom.env.Enviroment;
import pom.pages.BasePage;

public class HomePageTest extends Base implements BasePage{

	@BeforeClass
	public void beforeclass() {
		
	}
	@BeforeTest
	public void beforeTest() {
		CreateADirectory(scriptExecutionPath(), "Reports");
		createExtentReport(ExtentReportspath);
		Initialization();
	}
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		Enviroment.closeDriver();
		stopExtentReport();
	}

	@BeforeMethod
	public void beforeMethod(java.lang.reflect.Method method) {
		System.out.println();
		startExtentReports(method.getName());
		testcasename = method.getName();
	}

	@AfterMethod
	public void afterMethod() {
		alertHandling();
		
	}
	
	@Test(priority = 1, groups = "sanity")
	public static void verifyhomepage() throws Exception{
		System.out.println("Verify the Home page");
		Assert.assertEquals(data.getProperty("homepagetitle"), HomePageobj.verifyhomepage());
		// or
		assertionObj.checkTitle(data.getProperty("homepagetitle"), true);
		
	}
	
	@Test(priority = 2, dependsOnMethods = "verifyhomepage",groups = "regression")
	public void Selectgmaillink() {
		HomePageobj.gmaillink();
	}
}
