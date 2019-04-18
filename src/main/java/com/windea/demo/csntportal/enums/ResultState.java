package com.windea.demo.csntportal.enums;

/**
 * 结果状态的枚举。
 * TODO 套用到项目中
 */
public enum ResultState {
	FINE, VALID_ERROR, USER_NOT_FOUND, RESULT_NOT_FOUND, FATAL_EXCEPTION;

	@Override
	public String toString() {
		return switch(this) {
			case FINE -> "正常";
			case VALID_ERROR -> "验证错误";
			case USER_NOT_FOUND -> "用户未找到";
			case RESULT_NOT_FOUND -> "结果未找到";
			case FATAL_EXCEPTION -> "致命错误";
		};
	}
}
