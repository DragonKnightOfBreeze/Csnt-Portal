package com.windea.demo.csntportal.enums;

/**
 * 用户或文章对应的专业的枚举。
 */
public enum Profession {
	NONE, CSNT, SE, IOT, IMNIS, DMT;

	@Override
	public String toString() {
		return switch(this) {
			case NONE -> "无";
			case CSNT -> "计算机科学与技术";
			case SE -> "软件工程";
			case IOT -> "物联网工程";
			case IMNIS -> "信息管理与信息系统";
			case DMT -> "数字媒体技术";
		};
	}
}
