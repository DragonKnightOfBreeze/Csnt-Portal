package com.windea.demo.csntportal.domain.enums;

import com.windea.java.template.ITextEnum;

/**
 * 用户或文章对应的专业的枚举。
 */
public enum Profession implements ITextEnum {
	NONE("无"),
	CSNT("计算机科学与技术"),
	SE("软件工程"),
	IOT("物联网工程"),
	IMNIS("信息管理与信息系统"),
	DMT("数字媒体技术"),
	ET("教育技术");


	private final String text;

	Profession(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
