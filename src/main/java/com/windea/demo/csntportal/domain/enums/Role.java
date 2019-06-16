package com.windea.demo.csntportal.domain.enums;

import com.windea.java.template.ITextEnum;

/**
 * 用户身份的枚举。
 * NOTE 这里不需要为枚举值添加`ROLE_`前缀
 */
public enum Role implements ITextEnum {
	VISITOR("游客"),
	STUDENT("学生"),
	TEACHER("教师"),
	ADMIN("管理员");


	private final String text;

	Role(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
