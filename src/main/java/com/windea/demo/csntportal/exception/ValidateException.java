package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;

/**
 * 参数验证错误时的异常。
 */
public class ValidateException extends BaseException {
	private static final long serialVersionUID = 7640466193309767723L;

	public ValidateException() {
		super(ResultState.VALIDATE_ERROR.text());
	}

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(Throwable throwable) {
		super(throwable);
	}

	public ValidateException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
