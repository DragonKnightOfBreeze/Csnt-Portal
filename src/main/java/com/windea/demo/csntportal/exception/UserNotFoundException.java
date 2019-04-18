package com.windea.demo.csntportal.exception;

/**
 * 用户不存在时的异常。
 */
public class UserNotFoundException extends BaseException {
	private static final long serialVersionUID = 5878515474722400311L;

	public UserNotFoundException() {
		super();
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
