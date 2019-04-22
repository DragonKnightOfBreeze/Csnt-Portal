package com.windea.demo.csntportal.enums;

/**
 * 用于参数验证的正则表达式的常量一览。
 */
public class RegexConsts {
	/** 用户名规则：6-12位的以字母开头的字母、数字和下划线。 */
	public static final String USERNAME = "[a-zA-Z]\\w{5,11}";

	/** 密码规则：6-16位的字母、数字和下划线。 */
	public static final String PASSWORD = "\\w{6,16}";
}
