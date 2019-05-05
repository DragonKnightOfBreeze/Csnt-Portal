package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;

/**
 * 用户不被接受时的异常。
 */
public class UserNotAcceptableException extends BaseException {
	private static final long serialVersionUID = 2924809862252473699L;

	public UserNotAcceptableException() {
		super(ResultState.USER_NOT_ACCEPTED.text());
	}

	public UserNotAcceptableException(String message) {
		super(message);
	}
}
