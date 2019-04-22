package com.windea.demo.csntportal.enums;

import com.windea.commons.base.template.IEnumWithText;

/**
 * 用户身份的枚举。
 */
public enum Role implements IEnumWithText {
	ROLE_VISITOR("游客"),
	ROLE_STUDENT("学生"),
	ROLE_TEACHER("教师"),
	ROLE_ADMIN("管理员");


	private final String text;

	Role(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
