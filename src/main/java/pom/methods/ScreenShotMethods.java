package pom.methods;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import pom.base.Base;

public class ScreenShotMethods extends Base implements BaseMethod {

	// Screen shot
	public void takeScreenShot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));

		String scrFilepath = scrFile.getAbsolutePath();
		System.out.println("scrFilepath: " + scrFilepath);

		File currentDirFile = new File("Screenshots");
		String path = currentDirFile.getAbsolutePath();
		System.out.println("path: " + path + "+++");

		System.out.println("****\n" + path + "\\screenshot" + dateFormat.format(cal.getTime()) + ".png");

		FileUtils.copyFile(scrFile, new File(path + "\\screenshot" + dateFormat.format(cal.getTime()) + ".png"));

		/*
		 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Calendar
		 * cal = Calendar.getInstance();
		 * System.out.println(dateFormat.format(cal.getTime()));
		 */
	}
}
