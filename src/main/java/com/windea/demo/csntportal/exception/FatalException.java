package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.domain.enums.ResultState;

/**
 * 致命异常。
 */
public class FatalException extends BaseException {
	private static final long serialVersionUID = 8383347505896659664L;

	public FatalException() {
		super(ResultState.FATAL_ERROR.text());
	}

	public FatalException(String message) {
		super(message);
	}
}
