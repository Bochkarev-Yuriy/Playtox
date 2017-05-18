package ru.playtox.service.validation;

public interface ValidationPattern {
	String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	String LOGIN_PATTERN = "[a-zA-Z0-9]+";

	String PASSWORD_PATTERN = "[^\\s]+";
}
