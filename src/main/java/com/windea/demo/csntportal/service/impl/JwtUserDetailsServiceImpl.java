package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.enums.ResultState;
import com.windea.demo.csntportal.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 用于安全验证的用户详情服务实现类。
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService {
	protected final UserRepository repository;

	public JwtUserDetailsServiceImpl(UserRepository repository) {this.repository = repository;}


	/**
	 * 通过用户名得到用户，以进行权限验证。
	 * 可能会抛出运行时异常{@code UsernameNotFoundException}。
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		var result = repository.findByUsername(username);
		Assert.notNull(result, () -> {throw new UsernameNotFoundException(ResultState.USER_NOT_FOUND.text());});
		return result;
	}

	/**
	 * 修改密码。
	 * 这里不需要手动进行加密。
	 */
	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		var origin = repository.findByUsername(user.getUsername());
		Assert.notNull(origin, () -> {throw new UsernameNotFoundException(ResultState.USER_NOT_FOUND.text());});
		origin.setPassword(user.getPassword());
		return repository.save(origin);
	}
}
