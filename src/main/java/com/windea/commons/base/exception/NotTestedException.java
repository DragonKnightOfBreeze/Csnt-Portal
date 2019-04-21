package com.windea.commons.base.exception;

/**
 * 执行未测试的代码时的异常。
 */
public class NotTestedException extends RuntimeException {
	private static final long serialVersionUID = 1357551786311081873L;

	public NotTestedException() {
		super("Not tested code!");
	}

	public NotTestedException(String message) {
		super("Not tested code!\n" + message);
	}
}
