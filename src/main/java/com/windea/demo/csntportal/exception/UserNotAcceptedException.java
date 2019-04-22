package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;

/**
 * 用户不被接受时的异常。
 */
public class UserNotAcceptedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotAcceptedException() {
		super(ResultState.USER_NOT_ACCEPTED.text());
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
