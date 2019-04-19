package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import com.windea.demo.csntportal.exception.ResultNotFoundException;
import com.windea.demo.csntportal.exception.UserNotFoundException;
import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 用户的服务类。
 */
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repository;

	public UserServiceImpl(UserRepository repository) {this.repository = repository;}

	@Override
	public User loginByUsername(String username, String password) {
		var result = repository.findByUsernameAndPassword(username, password);
		Assert.notNull(result, () -> {throw new UserNotFoundException();});
		return result;
	}

	@Override
	public User loginByEmail(String email, String password) {
		var result = repository.findByEmailAndPassword(email, password);
		Assert.notNull(result, () -> {throw new UserNotFoundException();});
		return result;
	}

	@Override
	public User loginByPhoneNum(String phoneNum, String password) {
		var result = repository.findByPhoneNumAndPassword(phoneNum, password);
		Assert.notNull(result, () -> {throw new UserNotFoundException();});
		return result;
	}


	@Override
	public Page<User> findByNicknameLike(String nickname, Pageable pageable) {
		var page = repository.findAllByNicknameLike(nickname, pageable);
		Assert.notEmpty(page.getContent(), () -> {throw new ResultNotFoundException();});
		return page;
	}

	@Override
	public Page<User> findByGender(Gender gender, Pageable pageable) {
		var page = repository.findAllByGenderOrderByRoleAscProfessionAsc(gender, pageable);
		Assert.notEmpty(page.getContent(), () -> {throw new ResultNotFoundException();});
		return page;
	}

	@Override
	public Page<User> findByRole(Role role, Pageable pageable) {
		var page = repository.findAllByRoleOrderByGenderAscProfessionAsc(role, pageable);
		Assert.notEmpty(page.getContent(), () -> {throw new ResultNotFoundException();});
		return page;
	}

	@Override
	public Page<User> findByProfession(Profession profession, Pageable pageable) {
		var page = repository.findAllByProfessionOrderByGenderAscRoleAsc(profession, pageable);
		Assert.notEmpty(page.getContent(), () -> {throw new ResultNotFoundException();});
		return page;
	}
}
