package ru.playtox.util.validation;

public interface ValidationPattern {
	String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	String PASSWORD_PATTERN = "[^\\s]+";
}
