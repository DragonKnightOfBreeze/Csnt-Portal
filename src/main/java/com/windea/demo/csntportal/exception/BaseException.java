package com.windea.demo.csntportal.exception;

/**
 * 基础异常。
 */
public abstract class BaseException extends RuntimeException {
	private static final long serialVersionUID = 7606669941220183553L;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}
}
