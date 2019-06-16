package com.windea.demo.csntportal.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.windea.demo.csntportal.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Jwt用户详情类。实现UserDetails接口以进行安全验证。
 * 不需要进行参数验证。
 */
public class JwtUserDetails implements UserDetails {
	private static final long serialVersionUID = 1767704296003338587L;

	/** 主键。 */
	private final Integer id;

	/** 用户名。 */
	private final String username;

	/** 密码。 */
	@JsonIgnore
	private final String password;

	/** 已授予权限集合。 */
	@JsonIgnore
	private final Collection<? extends GrantedAuthority> authorities;


	public JwtUserDetails(Integer id, String username, String password,
		Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static JwtUserDetails create(User user) {
		var authorities = Set.of(new SimpleGrantedAuthority(user.getRole().toString()));
		var userDetails = new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities);
		return userDetails;
	}


	public Integer getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
