package pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pom.base.Base;

public class HomePage extends Base{
	@FindBy (xpath = "//*[text()='Gmail']")
	WebElement gmaillink;
	
	@FindBy (id ="")
	WebElement googlefld;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyhomepage() {
		String title = assertionObj.getPageTitle();
		return title;
	}
	
	public void gmaillink() {
		gmaillink.click();
	}
}
