package pom.methods;

import java.util.Arrays;

public class MiscMethods {
	// locator type
	public boolean valid_locator_type(String type) {
		return Arrays.asList("id", "class", "css", "name", "xpath").contains(type);
	}

	// validate the locator
	public void validateLocator(String type) throws Exception {
		if (!valid_locator_type(type))
			throw new Exception("Invalid locator type - " + type);
	}

	// method to validate dropdown selector
	public boolean valid_option_by(String option_by) {
		return Arrays.asList("text", "value", "index").contains(option_by);
	}

	// validate the options
	public void validateOptionBy(String optionBy) throws Exception {
		if (!valid_option_by(optionBy))
			throw new Exception("Invalid option by - " + optionBy);
	}
}
