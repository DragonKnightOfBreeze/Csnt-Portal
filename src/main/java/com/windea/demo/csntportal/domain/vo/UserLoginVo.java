package com.windea.demo.csntportal.domain.vo;


import com.windea.demo.csntportal.domain.enums.RegexConsts;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户登录表单的视图对象。
 * TODO 适配多种登录方式，但本质上还是用户名登录。
 */
@PropertySource("classpath:messages.properties")
public class UserLoginVo {
	/** 用户名。 */
	@NotEmpty(message = "{validation.user.username.notEmpty}")
	@Pattern(regexp = RegexConsts.USERNAME, message = "{validation.user.username.pattern}")
	private String username;

	/** 密码。 */
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
