package pom.methods;

import pom.base.Base;

public class AlertHandleMethods extends Base implements BaseMethod {

	public void handleAlert(String decision) {
		if (decision.equals("accept")) {
			String alert = driver.switchTo().alert().getText();
			System.out.println("Alert message: " + alert);
			extentInfo("Alert message: " + alert);
			driver.switchTo().alert().accept();
		} else {
			String alert = driver.switchTo().alert().getText();
			System.out.println("Alert message: " + alert);
			extentInfo("Alert message: " + alert);
			driver.switchTo().alert().dismiss();
		}
	}

	public void tryAlert() {
		try {
			String alert = driver.switchTo().alert().getText();
			System.out.println("Alert message: " + alert);
			extentInfo("Alert message: " + alert);
			driver.switchTo().alert().accept();
		} catch (Exception e) {

		}
	}
}
