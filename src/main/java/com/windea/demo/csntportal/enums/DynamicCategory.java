package com.windea.demo.csntportal.enums;

import com.windea.commons.base.template.IEnumWithText;

/**
 * 动态分类的枚举。
 */
public enum DynamicCategory implements IEnumWithText {
	CHAT("闲聊"),
	SEEK_HELP("求助"),
	DISCUSS("讨论"),
	NOTICE("通知");


	private final String text;

	DynamicCategory(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
