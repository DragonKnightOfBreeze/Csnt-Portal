package com.windea.demo.csntportal.exception;

import com.windea.demo.csntportal.domain.enums.ResultState;

/**
 * 查询结果不存在时的异常。
 */
public class NotFoundException extends BaseException {
	private static final long serialVersionUID = -5529897156485992446L;

	public NotFoundException() {
		super(ResultState.NOT_FOUND.text());
	}

	public NotFoundException(String message) {
		super(message);
	}
}
