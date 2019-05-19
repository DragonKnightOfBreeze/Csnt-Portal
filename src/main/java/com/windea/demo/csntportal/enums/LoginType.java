package com.windea.demo.csntportal.enums;

import com.windea.commons.base.template.IEnumWithText;

/**
 * 用户登录类型。
 */
public enum LoginType implements IEnumWithText {
	BY_USERNAME("用户名登录"),
	BY_PHONE_NUM("手机号码登录"),
	BY_EMAIL("邮箱地址登录");


	private final String text;

	LoginType(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
