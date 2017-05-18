package ru.playtox.dao.impl.exceptions;


import ru.playtox.dao.impl.AbstractDao;

import java.io.Serializable;

/**
 * An exception that occurs when fail attempt deleting a object.
 *
 * @author Yuriy Bochkarev
 * @see {@link AbstractDao#deleteByKey(Serializable)}
 */
public class RemoveException extends RuntimeException {

	/**
	 * Constructor a RemoveException using the given exception message.
	 *
	 * @param message The message explaining the reason for the exception.
	 */
	public RemoveException(String message) {
		super(message);
	}

	/**
	 * Constructs a RemoveException using the given message and underlying cause.
	 *
	 * @param message The message explaining the reason for the exception.
	 * @param cause   The underlying cause.
	 */
	public RemoveException(String message, Throwable cause) {
		super(message, cause);
	}
}
