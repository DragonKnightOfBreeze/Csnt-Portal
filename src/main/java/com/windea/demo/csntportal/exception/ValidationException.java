package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.domain.enums.ResultState;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 参数验证错误时的异常。
 */
public class ValidationException extends BaseException {
	private static final long serialVersionUID = 7640466193309767723L;

	private List<ObjectError> validationErrors;


	public ValidationException() {
		super(ResultState.VALIDATION_ERROR.text());
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(List<ObjectError> validationErrors) {
		super(ResultState.VALIDATION_ERROR.text());
		this.validationErrors = validationErrors;
	}


	public List<ObjectError> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ObjectError> validationErrors) {
		this.validationErrors = validationErrors;
	}
}
