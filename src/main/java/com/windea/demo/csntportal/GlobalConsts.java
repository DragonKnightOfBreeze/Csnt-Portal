package com.windea.demo.csntportal;

/**
 * 项目的公共常量。<br>
 * TODO 使用配置文件？<br>
 * NOTE 用于@ColumnDefault的常量字符串，如果要表示数据库表的行，则需要写成`"'name'"`的形式
 */
public class GlobalConsts {
	//正则模式

	//6-12位的以字母开头的字母、数字和下划线。
	public static final String RE_USERNAME = "[a-zA-Z]\\w{5,11}";
	//6-16位的字母、数字和下划线。
	public static final String RE_PASSWORD = "\\w{6,16}";

	//占位符

	public static final String PH_C_TITLE = "'请输入标题'";
	public static final String PH_C_CONTENT = "'请输入内容。'";
	public static final String PH_C_AUTHOR = "'匿名'";

	public static final String PH_D_SUBJECT = "'请输入动态主题'";
	public static final String PH_D_CONTENT = "'请输入动态内容。'";
}
