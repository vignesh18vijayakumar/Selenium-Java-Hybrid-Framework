package pom.methods;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AssertionMethods extends SelectElementByType implements BaseMethod {

	private WebElement element = null;

	// Get Page Title
	public String getPageTitle() {
		return driver.getTitle();
	}

	// Check Page Title
	public void checkTitle(String title, boolean testCase) throws TestCaseFailed {
		String pageTitle = getPageTitle();

		if (testCase) {
			if (!pageTitle.equals(title))
				throw new TestCaseFailed("Page Title Not Matched, Actual Page Title : " + pageTitle);
		} else {
			if (pageTitle.equals(title))
				throw new TestCaseFailed("Page Title Matched, Actual Page Title : " + pageTitle);
		}
	}

	// Check Partial Page Title
	public void checkPartialTitle(String partialTitle, boolean testCase) throws TestCaseFailed {
		String pageTitle = getPageTitle();
		if (testCase) {
			if (!pageTitle.contains(partialTitle))
				throw new TestCaseFailed("Partial Page Title Not Present, Actual Page Title : " + pageTitle);
		} else {
			if (pageTitle.contains(partialTitle))
				throw new TestCaseFailed("Partial Page Title Present, Actual Page Title : " + pageTitle);
		}
	}

	// Get Element Text
	public String getElementText(String accessType, String accessName) {
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.getText();

	}

	// Check Element Text
	public void checkElementText(String accessType, String actualValue, String accessName, boolean testCase)
			throws TestCaseFailed {
		String elementText = getElementText(accessType, accessName);

		if (testCase) {
			if (!elementText.equals(actualValue))
				throw new TestCaseFailed("Text Not Matched");
		} else {
			if (elementText.equals(actualValue))
				throw new TestCaseFailed("Text Matched");
		}
	}

	// Check Element Partial Text
	public void checkElementPartialText(String accessType, String actualValue, String accessName, boolean testCase)
			throws TestCaseFailed {
		String elementText = getElementText(accessType, accessName);

		if (testCase) {
			if (!elementText.contains(actualValue))
				throw new TestCaseFailed("Text Not Matched");
		} else {
			if (elementText.contains(actualValue))
				throw new TestCaseFailed("Text Matched");
		}
	}

	// Check the Element is enabled
	public boolean isElementEnabled(String accessType, String accessName) {
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.isEnabled();
	}

	// Check the Element enabled
	public void checkElementEnable(String accessType, String accessName, boolean testCase) throws TestCaseFailed {
		boolean result = isElementEnabled(accessType, accessName);
		if (testCase) {
			if (!result)
				throw new TestCaseFailed("Element Not Enabled");
		} else {
			if (result)
				throw new TestCaseFailed("Element Enabled");
		}
	}

	// Get Attribute values
	public String getElementAttribute(String accessType, String accessName, String attributeName) {
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.getAttribute(attributeName);
	}

	// Check Element Attribute
	public void checkElementAttribute(String accessType, String attributeName, String attributeValue, String accessName,
			boolean testCase) throws TestCaseFailed {
		String attrVal = getElementAttribute(accessType, accessName, attributeName);
		if (testCase) {
			if (!attrVal.equals(attributeValue))
				throw new TestCaseFailed("Attribute Value Not Matched");
		} else {
			if (attrVal.equals(attributeValue))
				throw new TestCaseFailed("Attribute Value Matched");
		}
	}

	// Check Element is Displayed
	public boolean isElementDisplayed(String accessType, String accessName) {
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		return element.isDisplayed();
	}

	// Check Element is Present
	public void checkElementPresence(String accessType, String accessName, boolean testCase) throws TestCaseFailed {
		if (testCase) {
			if (!isElementDisplayed(accessType, accessName))
				throw new TestCaseFailed("Element Not Present");
		} else {
			try {
				if (isElementDisplayed(accessType, accessName))
					throw new Exception("Present"); // since it is negative test and we found element
			} catch (Exception e) {
				if (e.getMessage().equals("Present")) // only raise if it present
					throw new TestCaseFailed("Element Present");
			}
		}
	}

	// Check that is Checkbox
	public void isCheckboxChecked(String accessType, String accessName, boolean shouldBeChecked) throws TestCaseFailed {
		WebElement checkbox = wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		if ((!checkbox.isSelected()) && shouldBeChecked)
			throw new TestCaseFailed("Checkbox is not checked");
		else if (checkbox.isSelected() && !shouldBeChecked)
			throw new TestCaseFailed("Checkbox is checked");
	}

	// Check that is Radio button
	public void isRadioButtonSelected(String accessType, String accessName, boolean shouldBeSelected)
			throws TestCaseFailed {
		WebElement radioButton = wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		if ((!radioButton.isSelected()) && shouldBeSelected)
			throw new TestCaseFailed("Radio Button not selected");
		else if (radioButton.isSelected() && !shouldBeSelected)
			throw new TestCaseFailed("Radio Button is selected");
	}

	// method to assert option from radio button group is selected/unselected
	public void isOptionFromRadioButtonGroupSelected(String accessType, String by, String option, String accessName,
			boolean shouldBeSelected) throws TestCaseFailed {
		List<WebElement> radioButtonGroup = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getelementbytype(accessType, accessName)));

		for (WebElement rb : radioButtonGroup) {
			if (by.equals("value")) {
				if (rb.getAttribute("value").equals(option)) {
					if ((!rb.isSelected()) && shouldBeSelected)
						throw new TestCaseFailed("Radio Button not selected");
					else if (rb.isSelected() && !shouldBeSelected)
						throw new TestCaseFailed("Radio Button is selected");
				}
			} else if (rb.getText().equals(option)) {
				if ((!rb.isSelected()) && shouldBeSelected)
					throw new TestCaseFailed("Radio Button not selected");
				else if (rb.isSelected() && !shouldBeSelected)
					throw new TestCaseFailed("Radio Button is selected");
			}
		}
	}

	// Get the Alert Text
	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	// Verify the Alert Text
	public void checkAlertText(String text) throws TestCaseFailed {
		if (!getAlertText().equals(text))
			throw new TestCaseFailed("Text on alert pop up not matched");
	}

	// Dropdown Option
	public void isOptionFromDropdownSelected(String accessType, String by, String option, String accessName,
			boolean shouldBeSelected) throws TestCaseFailed {
		Select selectList = null;
		WebElement dropdown = wait
				.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
		selectList = new Select(dropdown);

		String actualValue = "";
		if (by.equals("text"))
			actualValue = selectList.getFirstSelectedOption().getText();
		else
			actualValue = selectList.getFirstSelectedOption().getAttribute("value");

		if ((!actualValue.equals(option)) && (shouldBeSelected))
			throw new TestCaseFailed("Option Not Selected From Dropwdown");
		else if ((actualValue.equals(option)) && (!shouldBeSelected))
			throw new TestCaseFailed("Option Selected From Dropwdown");
	}
}
