package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.request.DynamicSearchVo;
import com.windea.demo.csntportal.enums.DynamicCategory;
import com.windea.demo.csntportal.exception.*;
import com.windea.demo.csntportal.repository.DynamicRepository;
import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DynamicServiceImpl implements DynamicService {
	private final DynamicRepository repository;
	private final UserRepository userRepository;

	public DynamicServiceImpl(DynamicRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@Transactional
	@Override
	public Dynamic saveBySponsorUsername(Dynamic dynamic, String username) {
		var user = userRepository.findByUsername(username);
		Assert.notNull(user, () -> {throw new UserNotFoundException();});
		dynamic.setSponsorUser(user);
		return repository.save(dynamic);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteByIdAndSponsorUsername(Integer id, String username) {
		//需要检查发起用户的用户名是否与当前的用户名相一致
		var user = repository.findSponsorUserById(id).getSponsorUser();
		var matches = Objects.equals(username, user.getUsername());
		Assert.isTrue(matches, () -> {throw new UserNotAcceptedException();});
		repository.deleteById(id);
	}


	@Override
	public Dynamic findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
		return result;
	}


	@Override
	public Page<Dynamic> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<Dynamic> findAllBySubjectContaining(String title, Pageable pageable) {
		title = title.strip();
		var resultPage = repository.findAllBySubjectContainingIgnoreCase(title, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<Dynamic> findAllByCategoryIn(Set<DynamicCategory> categorySet, Pageable pageable) {
		var resultPage = repository.findAllByCategoryIn(categorySet, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<Dynamic> findAllBySponsorUsername(String username, Pageable pageable) {
		var resultPage = repository.findAllBySponsorUsername(username, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<Dynamic> findAllByConditions(DynamicSearchVo vo, Pageable pageable) {
		var subPage1 = repository.findAllBySubjectContainingIgnoreCase(vo.getSubject(), pageable);
		var subPage2 = repository.findAllByCategoryIn(vo.getCategorySet(), pageable);
		var subPage3 = repository.findAllBySponsorUsername(vo.getSponsorUsername(), pageable);
		//是否存在一个工具方法，或者有必要自己创建？
		var list = subPage1.and(subPage2).and(subPage3).stream().distinct().collect(Collectors.toList());
		Assert.notEmpty(list, () -> {throw new ResultEmptyException();});
		var resultPage = new PageImpl<>(list, pageable, list.size());
		return resultPage;
	}
}
