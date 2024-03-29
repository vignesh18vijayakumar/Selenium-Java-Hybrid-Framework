package pom.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import pom.base.Base;

public class SelectElementByType extends Base {
	public SelectElementByType() {
		wait = new WebDriverWait(driver, 30);
	}

	public By getelementbytype(String type, String access_name) {
		switch (type) {
		case "id":
			return By.id(access_name);
		case "name":
			return By.name(access_name);
		case "class":
			return By.className(access_name);
		case "xpath":
			return By.xpath(access_name);
		case "css":
			return By.cssSelector(access_name);
		case "linkText":
			return By.linkText(access_name);
		case "partialLinkText":
			return By.partialLinkText(access_name);
		case "tagName":
			return By.tagName(access_name);
		default:
			return null;

		}
		/*
		 * if(type.equals("id")) return By.id(access_name); else if
		 * (type.equals("name")) return By.name(access_name); else if
		 * (type.equals("class")) return By.className(access_name); else if
		 * (type.equals("xpath")) return By.xpath(access_name); else if
		 * (type.equals("css")) return By.cssSelector(access_name); else
		 * if(type.equals("linkText")) return By.linkText(access_name); else
		 * if(type.equals("partialLinkText")) return By.partialLinkText(access_name);
		 * else if(type.equals("tagName")) return By.tagName(access_name); else return
		 * null;
		 */
	}
}
