package pom.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Util {

	// Basic declaration
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static WebElement element = null;
	public static ChromeOptions options = null;
	public static FirefoxOptions option = null;
	public static String browserName = null;

	// initial property
	public static Properties data = null;
	public static Properties xpath = null;

	// ExtentReport declaration
	public static ExtentHtmlReporter htmlReporter = null;
	public static ExtentReports extent = null;
	public static ExtentTest logger = null;

	public static final String ExtentReportspath = scriptExecutionPath()+"//"+"Reports"+"./report"+currentDateandTime()+".html";
	public static final String ExtentReportsScreenshotpath = scriptExecutionPath()+"//"+"Reports";
	
	public static String testcasename = null;

	// check the written code here
//	@Test
	public static void test() throws Exception {
		System.out.println(currentDate());
	}

	// To get the current date to execute the full test suite
	public static String currentDate() {
		DateFormat df = new SimpleDateFormat("dd");
		Date dateobj = new Date();
		String dateandtime = df.format(dateobj);
		return dateandtime;
	}

	// To get the Current Date and Time
	public static String currentDateandTime() {
		DateFormat df = new SimpleDateFormat("MM.dd.yyyy_HH_mm_ss");
		Date dateobj = new Date();
		String dateandtime = df.format(dateobj);
		return dateandtime;
	}

	// To get the Window user name
	public static String windowUser() {
		String windowuser = System.getProperty("user.name");
		return windowuser;
	}

	// To get the Operating Systems name
	public static String osName() {
		String os = System.getProperty("os.name").toUpperCase();
		return os;
	}

	// To get the Script running browser name
//		public static String broswerName() {
//			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
//			String browserName = caps.getBrowserName();
//			String browserVersion = caps.getVersion();
//			return browserName + ", " + browserVersion;
//		}

	// To get the Script Execution Path
	public static String scriptExecutionPath() {
		String user = System.getProperty("user.dir");
		return user;
	}

	// Using the Explicit Wait
	public static void explicitWait(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.click();
	}

	// Alert Handling
	public static void alertHandling() {
		try {
			String alert = driver.switchTo().alert().getText();
			System.out.println("Alert message: " + alert);
			extentInfo("Alert message: " + alert);
			driver.switchTo().alert().accept();
		} catch (Exception e) {
		}
	}
	
	

	// Take Screenshot
	public static void takeScreenshot(String fileName, String path) throws IOException {
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// now copy the screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File(path + "/" + fileName + ".png"));

	}

	// To create the Extent Report
	public static void createExtentReport(String path) {
		File reportpath = new File(path);
		htmlReporter = new ExtentHtmlReporter(reportpath);
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
//		extent.setSystemInfo("Application", "Testing");
		extent.setSystemInfo("Date", currentDateandTime());
		extent.setSystemInfo("Operating Systems", osName());
		extent.setSystemInfo("Tester", windowUser());
		extent.setSystemInfo("Browser", browserName);
		extent.setSystemInfo("Script Execution Path", scriptExecutionPath());
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Results");
		htmlReporter.config().setReportName("Sanity Test Results");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		extent.setReportUsesManualConfiguration(true);
		htmlReporter.config().setTheme(Theme.DARK);
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
	}

	// To Start the Extent Report
	public static void startExtentReports(String testcasename) {
		logger = extent.createTest(testcasename);
	}

	// To Stop the extend report
	public static void stopExtentReport() {
		extent.flush();
	}

	// To display Green color text if the test case is pass in extent report
	public static void extentPass(String text) throws IOException {
		logger.log(Status.PASS, MarkupHelper.createLabel(text, ExtentColor.GREEN));
		takeScreenshot(testcasename, ExtentReportsScreenshotpath);
		logger.addScreenCaptureFromPath(ExtentReportsScreenshotpath+"//"+testcasename+".png");
	}

	// To display red color text if the test case is fail in extent report
	public static void extentFail(String text) throws IOException {
		logger.log(Status.FAIL, MarkupHelper.createLabel(text, ExtentColor.RED));
		takeScreenshot(testcasename, ExtentReportsScreenshotpath);
		logger.addScreenCaptureFromPath(ExtentReportsScreenshotpath+testcasename+".png");
	}

	// To display Indigo color text if the test case is skipped in extent report
	public static void extentSkip(String text) throws IOException {
		logger.log(Status.SKIP, MarkupHelper.createLabel(text, ExtentColor.INDIGO));
		takeScreenshot(testcasename, ExtentReportsScreenshotpath);
		logger.addScreenCaptureFromPath(ExtentReportsScreenshotpath);
	}

	// To display the information in Teal color in extent report
	public static void extentInfo(String text) {
		logger.log(Status.INFO, MarkupHelper.createLabel(text, ExtentColor.TEAL));
	}

	// Create folder
	public static void CreateADirectory(String directorypath, String directoryname) {
		// project directory
		String dir = directorypath + File.separator + directoryname;
		// create a directory in the path
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdir();
			System.out.println("Directory is created!: " + dir);
		} else {
			System.out.println("Directory is already existed!");
		}
	}

	// create Excelsheet
	public static void createExcelsheet(String excelpath, String sheetname) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(sheetname);

//			HSSFRow rowhead = sheet.createRow((short) 0);
//			rowhead.createCell(0).setCellValue("No.");
//			rowhead.createCell(1).setCellValue("Name");
//			rowhead.createCell(2).setCellValue("Address");
//			rowhead.createCell(3).setCellValue("Email");
//
//			HSSFRow row = sheet.createRow((short) 1);
//			row.createCell(0).setCellValue("1");
//			row.createCell(1).setCellValue("Sankumarsingh");
//			row.createCell(2).setCellValue("India");
//			row.createCell(3).setCellValue("sankumarsingh@gmail.com");

			FileOutputStream fileOut = new FileOutputStream(excelpath);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			System.out.println("Your excel file has been generated!");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// Read Excel sheet
	public static void readExcelsheet() {

	}

	// write Excel sheet
	public static void writeExcelsheet() {

	}
	
	
}
