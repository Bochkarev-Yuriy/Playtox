package ru.playtox.service.exceptions;

import ru.playtox.model.User;
import ru.playtox.service.impl.UserServiceImpl;

/**
 * Indicates that an exception occurred upon attempt to write an existing {@link User} in the DataBase.
 *
 * @author Yuriy Bochkarev
 * @see {@link UserServiceImpl#addUser(User)}
 */
public class UserDuplicateException extends RuntimeException {

	/**
	 * Constructor a UserDuplicateException using the given exception message.
	 *
	 * @param message The message explaining the reason for the exception.
	 */
	public UserDuplicateException(String message) {
		super(message);
	}
}
