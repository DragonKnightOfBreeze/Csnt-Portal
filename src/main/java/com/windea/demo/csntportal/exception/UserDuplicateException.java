package com.windea.demo.csntportal.exception;

/**
 * 用户重复的异常。
 */
public class UserDuplicateException extends RuntimeException {
	private static final long serialVersionUID = 7043120389926838859L;

	public UserDuplicateException() {
		super();
	}

	public UserDuplicateException(String message) {
		super(message);
	}

	public UserDuplicateException(Throwable throwable) {
		super(throwable);
	}

	public UserDuplicateException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
