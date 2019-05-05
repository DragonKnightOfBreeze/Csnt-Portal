package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;

/**
 * 查询结果为空时的异常。
 */
public class ResultEmptyException extends BaseException {
	private static final long serialVersionUID = 993434392118874134L;

	public ResultEmptyException() {
		super(ResultState.RESULT_EMPTY.text());
	}

	public ResultEmptyException(String message) {
		super(message);
	}
}
