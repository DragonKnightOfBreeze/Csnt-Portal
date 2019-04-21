package com.windea.commons.base.exception;

/**
 * 执行未完成的代码时的异常。
 */
public class NotImplementedException extends RuntimeException {
	private static final long serialVersionUID = 1221511937742237208L;

	public NotImplementedException() {
		super("Not implemented code!");
	}

	public NotImplementedException(String message) {
		super("Not implemented code!\n" + message);
	}
}
