package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;

/**
 * 查询结果不存在时的异常。
 */
public class ResultNotFoundException extends BaseException {
	private static final long serialVersionUID = -5529897156485992446L;

	public ResultNotFoundException() {
		super(ResultState.RESULT_NOT_FOUND.text());
	}

	public ResultNotFoundException(String message) {
		super(message);
	}
}
