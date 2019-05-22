package com.windea.demo.csntportal.enums;

import com.windea.java.template.ITextEnum;

/**
 * 动态分类的枚举。
 */
public enum DynamicCategory implements ITextEnum {
	CHAT("闲聊"),
	SEEK_HELP("求助"),
	DISCUSS("讨论"),
	NOTICE("通知"),
	NEWS_FOCUS("新闻聚焦"),
	CAMPUS_NEWS("校园要闻"),
	MEDIA_REPORTS("媒体报道"),
	ALUMNI_MIEN("校友风采");


	private final String text;

	DynamicCategory(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
