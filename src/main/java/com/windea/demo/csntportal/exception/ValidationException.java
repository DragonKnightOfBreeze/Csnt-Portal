package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.domain.enums.ResultState;
import org.springframework.validation.BindingResult;

/**
 * 参数验证错误时的异常。
 */
public class ValidationException extends BaseException {
	private static final long serialVersionUID = 7640466193309767723L;

	protected BindingResult bindingResult;


	public ValidationException() {
		super(ResultState.VALIDATION_ERROR.text());
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(BindingResult bindingResult) {
		super(ResultState.VALIDATION_ERROR.text());
		this.bindingResult = bindingResult;
	}


	public BindingResult getBindingResult() {
		return bindingResult;
	}
}
