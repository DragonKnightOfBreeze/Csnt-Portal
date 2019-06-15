package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.domain.enums.ResultState;

/**
 * 用户不匹配时的异常。
 */
public class UserNotMatchedException extends BaseException {
	private static final long serialVersionUID = 2924809862252473699L;

	public UserNotMatchedException() {
		super(ResultState.USER_NOT_MATCHED.text());
	}

	public UserNotMatchedException(String message) {
		super(message);
	}
}
