package com.windea.demo.csntportal.domain.vo;

import com.windea.demo.csntportal.enums.RegexConsts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户登录表单的视图对象。<br>
 * TODO 适配多种登录方式，但本质上还是用户名登录。
 */
public class UserLoginVo {
	/** 用户名。 */
	@NotEmpty(message = "{user.username.notEmpty}")
	@Pattern(regexp = RegexConsts.USERNAME, message = "{user.username.pattern}")
	private String username;

	/** 密码。 */
	@NotEmpty(message = "{user.password.notEmpty}")
	@Pattern(regexp = RegexConsts.PASSWORD, message = "{user.password.pattern}")
	private String password;

	/** 记住登录。 */
	private boolean rememberMe;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
