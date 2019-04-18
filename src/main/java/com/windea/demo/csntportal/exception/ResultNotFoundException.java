package com.windea.demo.csntportal.exception;

/**
 * 查询结果不存在时的异常。
 */
public class ResultNotFoundException extends BaseException {
	private static final long serialVersionUID = -5529897156485992446L;

	public ResultNotFoundException() {
		super();
	}

	public ResultNotFoundException(String message) {
		super(message);
	}

	public ResultNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public ResultNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
