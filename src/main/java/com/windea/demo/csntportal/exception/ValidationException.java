package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;
import org.springframework.validation.BindingResult;

/**
 * 参数验证错误时的异常。
 */
public class ValidationException extends BaseException {
	private static final long serialVersionUID = 7640466193309767723L;

	public ValidationException() {
		super(ResultState.VALIDATE_ERROR.text());
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(BindingResult bindingResult) {
		super(bindingResult.getAllErrors().get(0).toString());
	}
}
