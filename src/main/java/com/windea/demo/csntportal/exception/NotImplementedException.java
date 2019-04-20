package com.windea.demo.csntportal.exception;

/**
 * 执行未完成代码时的异常。
 */
public class NotImplementedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotImplementedException() {
		super();
	}

	public NotImplementedException(String message) {
		super(message);
	}

	public NotImplementedException(Throwable throwable) {
		super(throwable);
	}

	public NotImplementedException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
