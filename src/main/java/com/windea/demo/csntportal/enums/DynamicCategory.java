package com.windea.demo.csntportal.enums;

/**
 * 动态分类的枚举。
 */
public enum DynamicCategory {
	CHAT, SEEK_HELP, DISCUSS, NOTICE;

	@Override
	public String toString() {
		return switch(this) {
			case CHAT -> "闲聊";
			case SEEK_HELP -> "求助";
			case DISCUSS -> "讨论";
			case NOTICE -> "通知";
		};
	}
}
