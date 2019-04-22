package com.windea.demo.csntportal.enums;

import com.windea.commons.base.template.IEnumWithText;

/**
 * 用户或文章对应的专业的枚举。
 */
public enum Profession implements IEnumWithText {
	NONE("无"),
	CSNT("计算机科学与技术"),
	SE("软件工程"),
	IOT("物联网工程"),
	IMNIS("信息管理与信息系统"),
	DMT("数字媒体技术");


	private final String text;

	Profession(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
