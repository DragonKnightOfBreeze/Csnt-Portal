package com.windea.demo.csntportal.domain.response;

/**
 * Jwt响应的视图对象。<br>
 * 用于安全验证。<br>
 * TODO 套用到项目中
 */
public class JwtResponseVo {
	/** Jwt口令。 */
	private String token;

	/** Jwt类型 */
	private String type = "Bearer";

	/** Jwt帐号。 */
	private String account;

	/** Jwt姓名。 */
	private String name;

	/** Jwt身份。 */
	private String role;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public JwtResponseVo(String token, String account, String name, String role) {
		this.token = token;
		this.account = account;
		this.name = name;
		this.role = role;
	}
}
