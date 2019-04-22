package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;

/**
 * 用户不存在时的异常。
 */
public class UserNotFoundException extends BaseException {
	private static final long serialVersionUID = 5878515474722400311L;

	public UserNotFoundException() {
		super(ResultState.USER_NOT_FOUND.text());
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public UserNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
