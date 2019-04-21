package com.windea.demo.csntportal.exception;

/**
 * 用户不被允许时的异常。
 */
public class UserNotAcceptedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotAcceptedException() {
		super();
	}

	public UserNotAcceptedException(String message) {
		super(message);
	}

	public UserNotAcceptedException(Throwable throwable) {
		super(throwable);
	}

	public UserNotAcceptedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
