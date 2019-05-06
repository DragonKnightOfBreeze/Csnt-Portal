package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.enums.ResultState;

/**
 * 查询结果为空时的异常。
 */
public class NoContentException extends BaseException {
	private static final long serialVersionUID = 993434392118874134L;

	public NoContentException() {
		super(ResultState.NO_CONTENT.text());
	}

	public NoContentException(String message) {
		super(message);
	}
}
