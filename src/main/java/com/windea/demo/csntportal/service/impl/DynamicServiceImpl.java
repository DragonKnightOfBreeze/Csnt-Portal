package com.windea.demo.csntportal.service.impl;

import com.windea.commons.springboot.utils.PageUtils;
import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.vo.DynamicSearchVo;
import com.windea.demo.csntportal.enums.DynamicCategory;
import com.windea.demo.csntportal.exception.*;
import com.windea.demo.csntportal.repository.DynamicRepository;
import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "dynamicServiceCache")
public class DynamicServiceImpl implements DynamicService {
	private final DynamicRepository repository;
	private final UserRepository userRepository;

	public DynamicServiceImpl(DynamicRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}


	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public Dynamic saveBySponsorUsername(Dynamic dynamic, String username) {
		var user = userRepository.findByUsername(username);
		Assert.notNull(user, () -> {throw new UserNotAcceptableException();});
		dynamic.setSponsorUser(user);
		return repository.save(dynamic);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public void deleteByIdAndSponsorUsername(Integer id, String username) {
		//需要检查发起用户的用户名是否与当前的用户名相一致
		var user = repository.findSponsorUserById(id).getSponsorUser();
		var matches = Objects.equals(username, user.getUsername());
		Assert.isTrue(matches, () -> {throw new UserNotAcceptableException();});
		repository.deleteById(id);
	}


	@Cacheable
	@Override
	public Dynamic findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
		return result;
	}


	@Cacheable
	@Override
	public Page<Dynamic> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> findAllBySubject(String subject, Pageable pageable) {
		subject = subject.strip();
		var resultPage = repository.findAllBySubjectContainingIgnoreCase(subject, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> findAllByCategory(Set<DynamicCategory> categorySet, Pageable pageable) {
		var resultPage = repository.findAllByCategoryIn(categorySet, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> findAllBySponsorUsername(String username, Pageable pageable) {
		var resultPage = repository.findAllBySponsorUsername(username, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> findAllByConditions(DynamicSearchVo vo, Pageable pageable) {
		var page1 = repository.findAllBySubjectContainingIgnoreCase(vo.getSubject(), pageable);
		var page2 = repository.findAllByCategoryIn(vo.getCategorySet(), pageable);
		var page3 = repository.findAllBySponsorUsername(vo.getSponsorUsername(), pageable);
		var resultPage = PageUtils.concat(pageable, page1, page2, page3);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Cacheable
	@Override
	public User findSponsorUserById(Integer id) {
		var result = repository.findSponsorUserById(id).getSponsorUser();
		return result;
	}
}
