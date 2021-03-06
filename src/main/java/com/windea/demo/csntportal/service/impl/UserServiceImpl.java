package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.enums.*;
import com.windea.demo.csntportal.exception.NotFoundException;
import com.windea.demo.csntportal.repository.DynamicRepository;
import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.UserService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 用户的服务类。
 */
@Service
@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final DynamicRepository dynamicRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository repository,
		DynamicRepository dynamicRepository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.dynamicRepository = dynamicRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	@CachePut
	@Transactional
	@Override
	public User update(User user) {
		//得到原始的用户信息，然后更改必要的属性
		//不允许修改密码
		var origin = get(user.getId());
		origin.setPhoneNum(user.getPhoneNum());
		origin.setEmail(user.getEmail());
		origin.setNickname(user.getNickname());
		return repository.save(origin);
	}

	@Cacheable
	@Override
	public User get(Integer id) {
		var result = repository.findById(id)
			.orElseThrow(() -> {throw new NotFoundException();});
		return result;
	}

	@Cacheable
	@Override
	public User getByUsername(String username) {
		var result = repository.findByUsername(username);
		Assert.notNull(result, () -> {throw new NotFoundException();});
		return result;
	}

	@Cacheable
	@Override
	public List<Dynamic> getDynamicList(Integer id) {
		var resultList = dynamicRepository.findAllBySponsorUser_Id(id);
		return resultList;
	}

	@Cacheable
	@Override
	public Page<User> list(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<User> searchByNickname(String nickname, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		nickname = nickname.strip();
		var resultPage = repository.findAllByNicknameContainsIgnoreCase(nickname, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<User> searchByGender(Gender gender, Pageable pageable) {
		var resultPage = repository.findAllByGender(gender, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<User> searchByRole(Role role, Pageable pageable) {
		var resultPage = repository.findAllByRole(role, pageable);
		return resultPage;
	}

	@Override
	public Page<User> searchByProfession(Profession profession, Pageable pageable) {
		var resultPage = repository.findAllByProfession(profession, pageable);
		return resultPage;
	}

	@Override
	public boolean exists(String username, String email, String phoneNum) {
		var result = repository.existsByUsernameOrEmailOrPhoneNum(username, email, phoneNum);
		return result;
	}
}
