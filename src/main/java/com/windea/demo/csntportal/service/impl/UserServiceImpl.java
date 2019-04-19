package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import com.windea.demo.csntportal.exception.ResultNotFoundException;
import com.windea.demo.csntportal.exception.UserNotFoundException;
import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 用户的服务类。
 */
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}


	@Transactional
	@Override
	public User save(User user) {
		//加密密码
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Transactional
	@Override
	public User update(User user) {
		var origin = repository.findById(user.getId()).orElseThrow(() -> {throw new UserNotFoundException();});
		origin.setPassword(passwordEncoder.encode(user.getPassword()));
		origin.setPhoneNum(user.getPhoneNum());
		origin.setEmail(user.getEmail());
		origin.setNickname(user.getNickname());
		return repository.save(origin);
	}


	@Override
	public User findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new UserNotFoundException();});
		return result;
	}

	@Override
	public User findByUsername(String username) {
		var result = repository.findByUsername(username);
		Assert.notNull(result, () -> {throw new UserNotFoundException();});
		return result;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		var result = repository.findByUsernameAndPassword(username, password);
		Assert.notNull(result, () -> {throw new UserNotFoundException();});
		return result;
	}


	@Override
	public Page<User> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultNotFoundException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByNicknameLike(String nickname, Pageable pageable) {
		nickname = nickname.strip();
		var resultPage = nickname.isEmpty() ? repository.findAll(pageable) :
			repository.findAllByNicknameLike(nickname, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultNotFoundException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByGender(Gender gender, Pageable pageable) {
		var resultPage = repository.findAllByGenderOrderByRoleAscProfessionAsc(gender, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultNotFoundException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByRole(Role role, Pageable pageable) {
		var resultPage = repository.findAllByRoleOrderByGenderAscProfessionAsc(role, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultNotFoundException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByProfession(Profession profession, Pageable pageable) {
		var resultPage = repository.findAllByProfessionOrderByGenderAscRoleAsc(profession, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultNotFoundException();});
		return resultPage;
	}


	/**
	 * 通过用户名得到用户，以进行权限验证。
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		var result = repository.findByUsername(username);
		Assert.notNull(result, () -> {throw new UserNotFoundException();});
		return result;
	}

	/**
	 * 修改密码。
	 */
	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		var origin = repository.findByUsername(user.getUsername());
		Assert.notNull(origin, () -> {throw new UserNotFoundException();});
		origin.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(origin);
	}
}
