package com.windea.demo.csntportal.enums;

import com.windea.commons.base.template.IEnumWithText;

/**
 * 结果状态的枚举。
 */
public enum ResultState implements IEnumWithText {
	FINE("正常。"),
	VALID_ERROR("错误：验证错误！"),
	RESULT_EMPTY("错误：查询结果为空！"),
	RESULT_NOT_FOUND("错误：查询结果不存在！"),
	USER_DUPLICATE("错误：用户重复！"),
	USER_NOT_ACCEPTED("错误：用户不被接受！"),
	USER_NOT_FOUND("错误：用户不存在！"),
	FATAL_ERROR("错误：致命错误！");


	private final String text;

	ResultState(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
