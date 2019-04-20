package com.windea.demo.csntportal.exception;

/**
 * 查询结果为空时的异常。
 */
public class ResultEmptyException extends RuntimeException {
	private static final long serialVersionUID = 993434392118874134L;

	public ResultEmptyException() {
		super();
	}

	public ResultEmptyException(String message) {
		super(message);
	}

	public ResultEmptyException(Throwable throwable) {
		super(throwable);
	}

	public ResultEmptyException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
