package ru.playtox.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class ValidationHelper {
	private static MessageSource messageSource;

	@Autowired
	private MessageSource tmpMessage;

	@PostConstruct
	private void init() {
		messageSource = tmpMessage;
	}

	private ValidationHelper() {
	}

	public static boolean checkConfEqualToPass(String password, String confirmPassword) {
		if (confirmPassword.isEmpty() || !password.equals(confirmPassword)) {
			return false;
		}
		return true;
	}
}
