package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;
import org.springframework.validation.BindingResult;

/**
 * 用户重复时的异常。
 */
public class UserDuplicateException extends ValidationException {
	private static final long serialVersionUID = 7043120389926838859L;

	private BindingResult bindingResult;

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public UserDuplicateException() {
		super(ResultState.USER_DUPLICATE.text());
	}

	public UserDuplicateException(String message) {
		super(message);
	}

	public UserDuplicateException(BindingResult bindingResult) {
		super(bindingResult);
		bindingResult.reject("validation.user.duplicate");
	}
}
