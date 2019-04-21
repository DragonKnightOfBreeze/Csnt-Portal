package com.windea.demo.csntportal.domain.request;

import com.windea.demo.csntportal.GlobalConsts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户登录表单的视图对象。<br>
 * TODO 适配多种登录方式
 */
public class UserLoginVo {
	/** 用户名。 */
	@NotEmpty(message = "{user.username.notEmpty}")
	@Pattern(regexp = GlobalConsts.RE_USERNAME, message = "{user.username.pattern}")
	private String username;

	/** 密码。 */
	@NotEmpty(message = "{user.password.notEmpty}")
	@Pattern(regexp = GlobalConsts.RE_PASSWORD, message = "{user.password.pattern}")
	private String password;


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
}