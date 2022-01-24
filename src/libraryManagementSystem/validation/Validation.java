package libraryManagementSystem.validation;

public class Validation {

	public boolean matchPassword(String password, String cPassword) {
		boolean match = false;
		if (password.equals(cPassword)) {
			match = true;
		} else {
			match = false;
		}
		return match;
	}
}
