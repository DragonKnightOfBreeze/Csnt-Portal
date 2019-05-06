package com.windea.demo.csntportal.enums;

import com.windea.commons.base.template.IEnumWithText;

/**
 * 结果状态的枚举。
 */
public enum ResultState implements IEnumWithText {
	OK("信息：正常。"),
	FATAL_ERROR("错误：致命错误！"),
	NO_CONTENT("错误：查询结果为空！"),
	NOT_FOUND("错误：查询结果不存在！"),
	USER_NOT_FOUND("错误：用户不存在！"),
	USER_NOT_MATCHED("错误：用户不匹配！"),
	VALIDATION_ERROR("错误：验证错误！");


	private final String text;

	ResultState(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
