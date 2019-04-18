package com.windea.demo.csntportal.exception;

/**
 * 项目的基础异常。
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 7606669941220183553L;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable throwable) {
		super(throwable);
	}

	public BaseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
