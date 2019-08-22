package pom.methods;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigateMethods extends SelectElementByType implements BaseMethod {
	// SelectElementByType eleType= new SelectElementByType();
	private WebElement element = null;
	private String old_win = null;
	private String lastWinHandle;

	// Navigate URL
	public void navigateTo(String url) {
		driver.get(url);
	}

	// Navigate Forward or Back
	public void navigate(String direction) {
		if (direction.equals("back"))
			driver.navigate().back();
		else
			driver.navigate().forward();
	}

	// Close the Driver
	public void closeDriver() {
		driver.close();
	}

	// keys control
	public Keys getKey() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win"))
			return Keys.CONTROL;
		else if (os.contains("nux") || os.contains("nix"))
			return Keys.CONTROL;
		else if (os.contains("mac"))
			return Keys.COMMAND;
		else
			return null;
	}

	// Zoom In/Out element
	public void zoomInOut(String inOut) {
		WebElement Sel = driver.findElement(getelementbytype("tagName", "html"));
		if (inOut.equals("ADD"))
			Sel.sendKeys(Keys.chord(getKey(), Keys.ADD));
		else if (inOut.equals("SUBTRACT"))
			Sel.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
		else if (inOut.equals("reset"))
			Sel.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
	}

	// Zoom In/Out Till Element Display
	public void zoomInOutTillElementDisplay(String accessType, String inOut, String accessName) {
		Actions action = new Actions(driver);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		while (true) {
			if (element.isDisplayed())
				break;
			else
				action.keyDown(getKey()).sendKeys(inOut).keyUp(getKey()).perform();
		}
	}

	// Resize the Browser
	public void resizeBrowser(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	// Maximize the Window
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	// MouseOver the Webelement
	public void hoverOverElement(String accessType, String accessName) {
		Actions action = new Actions(driver);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		action.moveToElement(element).perform();
	}

	// Scroll to the Element
	public void scrollToElement(String accessType, String accessName) {
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}

	// Scroll the page
	public void scrollPage(String to) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		if (to.equals("end"))
			executor.executeScript(
					"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		else if (to.equals("top"))
			executor.executeScript(
					"window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);");
		else
			throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
	}

	// Switch the Child Window
	public void switchToNewWindow() {
		old_win = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles())
			lastWinHandle = winHandle;
		driver.switchTo().window(lastWinHandle);
	}

	// Switch the PArent Window
	public void switchToOldWindow() {
		driver.switchTo().window(old_win);
	}

	// Switch the Window based on title
	public void switchToWindowByTitle(String windowTitle) throws Exception {
		// System.out.println("++"+windowTitle+"++");
		old_win = driver.getWindowHandle();
		boolean winFound = false;
		for (String winHandle : driver.getWindowHandles()) {
			String str = driver.switchTo().window(winHandle).getTitle();
			// System.out.println("**"+str+"**");
			if (str.equals(windowTitle)) {
				winFound = true;
				break;
			}
		}
		if (!winFound)
			throw new Exception("Window having title " + windowTitle + " not found");
	}

	// close the new window
	public void closeNewWindow() {
		driver.close();
	}

	// switch the child frame
	public void switchFrame(String accessType, String accessName) {
		if (accessType.equalsIgnoreCase("index"))
			driver.switchTo().frame(accessName);
		else {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
			driver.switchTo().frame(element);
		}
	}

	// switch the parent frame
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
}
