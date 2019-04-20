package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import com.windea.demo.csntportal.exception.*;
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
		//不允许重复用户名、邮箱和电话号码的新注册用户
		var exists = exists(user.getUsername(), user.getEmail(), user.getPhoneNum());
		Assert.isTrue(exists, () -> {throw new UserDuplicateException();});
		//加密密码
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@Transactional
	@Override
	public User update(User user) {
		//得到原始的用户信息，然后更改必要的属性
		var origin = findById(user.getId());
		origin.setPassword(passwordEncoder.encode(user.getPassword()));
		origin.setPhoneNum(user.getPhoneNum());
		origin.setEmail(user.getEmail());
		origin.setNickname(user.getNickname());
		return repository.save(origin);
	}


	@Override
	public User findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
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
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByNicknameLike(String nickname, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		nickname = nickname.strip();
		Page<User> resultPage;
		if(nickname.isEmpty()) {
			resultPage = findAll(pageable);
		} else {
			resultPage = repository.findAllByNicknameLikeIgnoreCase(nickname, pageable);
		}
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByGender(Gender gender, Pageable pageable) {
		var resultPage = repository.findAllByGender(gender, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByRole(Role role, Pageable pageable) {
		var resultPage = repository.findAllByRole(role, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<User> findAllByProfession(Profession profession, Pageable pageable) {
		var resultPage = repository.findAllByProfession(profession, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}


	@Override
	public boolean exists(String username, String email, String phoneNum) {
		var result = repository.existsByUsernameOrEmailOrPhoneNum(username, email, phoneNum);
		return result;
	}

	/**
	 * 通过用户名得到用户，以进行权限验证。
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		var result = findByUsername(username);
		return result;
	}

	/**
	 * 修改密码。
	 */
	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		var origin = findByUsername(user.getUsername());
		origin.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(origin);
	}
}
