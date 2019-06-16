package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.enums.DynamicCategory;
import com.windea.demo.csntportal.domain.vo.DynamicQueryVo;
import com.windea.demo.csntportal.exception.NotFoundException;
import com.windea.demo.csntportal.repository.DynamicRepository;
import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Dynamic createBySponsorUsername(Dynamic dynamic, String username) {
		var user = userRepository.findByUsername(username);
		dynamic.setSponsorUser(user);
		return repository.save(dynamic);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Cacheable
	@Override
	public Dynamic get(Integer id) {
		var result = repository.findById(id)
			.orElseThrow(() -> {throw new NotFoundException();});
		return result;
	}

	@Cacheable
	@Override
	public Page<Dynamic> list(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> searchBySubject(String subject, Pageable pageable) {
		subject = subject.strip();
		var resultPage = repository.findAllBySubjectContainsIgnoreCase(subject, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> searchBySponsorUsername(String username, Pageable pageable) {
		var resultPage = repository.findAllBySponsorUser_Username(username, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> searchByCategory(Set<DynamicCategory> categorySet, Pageable pageable) {
		var resultPage = repository.findAllByCategoryIn(categorySet, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<Dynamic> advanceSearch(DynamicQueryVo vo, Pageable pageable) {
		var resultPage = repository.findAllDistinctBySubjectContainsAndSponsorUser_UsernameAndCategoryInAllIgnoreCase(
			vo.getSubject(), vo.getSponsorUsername(), vo.getCategorySet(), pageable
		);
		return resultPage;
	}
}
