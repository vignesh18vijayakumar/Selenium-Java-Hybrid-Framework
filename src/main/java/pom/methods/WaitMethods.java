package pom.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitMethods extends SelectElementByType implements BaseMethod {
	// wait
	public void wait(String time) throws NumberFormatException, InterruptedException {
		// sleep method takes parameter in milliseconds
		Thread.sleep(Integer.parseInt(time) * 1000);
	}

	// wait for Element To Display
	public void waitForElementToDisplay(String accessType, String accessName, String duration) {
		By byEle = getelementbytype(accessType, accessName);
		WebDriverWait wait = (new WebDriverWait(driver, Integer.parseInt(duration) * 1000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
	}

	// Wait For Element To Click
	public void waitForElementToClick(String accessType, String accessName, String duration) {
		By byEle = getelementbytype(accessType, accessName);
		WebDriverWait wait = (new WebDriverWait(driver, Integer.parseInt(duration) * 1000));
		wait.until(ExpectedConditions.elementToBeClickable(byEle));
	}

}
