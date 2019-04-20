package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.request.DynamicSearchVo;
import com.windea.demo.csntportal.enums.DynamicCategory;
import com.windea.demo.csntportal.exception.UserNotFoundException;
import com.windea.demo.csntportal.repository.DynamicRepository;
import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

@Service
public class DynamicServiceImpl implements DynamicService {
	private final DynamicRepository repository;
	private final UserRepository userRepository;

	public DynamicServiceImpl(DynamicRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}


	@Override
	public Dynamic saveBySponsorUsername(Dynamic dynamic, String username) {
		var user = userRepository.findByUsername(username);
		Assert.notNull(user, () -> {throw new UserNotFoundException();});
		dynamic.setSponsorUser(user);
		return repository.save(dynamic);
	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public void deleteByIdAndSponsorUsername(Integer id, String username) {

	}


	@Override
	public Dynamic findById(Integer id) {
		return null;
	}


	@Override
	public Page<Dynamic> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public Page<Dynamic> findAllByTitleContaining(String title, Pageable pageable) {
		return null;
	}

	@Override
	public Page<Dynamic> findAllByCategoryIn(Set<DynamicCategory> categorySet, Pageable pageable) {
		return null;
	}

	@Override
	public Page<Dynamic> findAllBySponsorUsername(String username, Pageable pageable) {
		return null;
	}

	@Override
	public Page<Dynamic> findAllByConditions(DynamicSearchVo vo, Pageable pageable) {
		return null;
	}
}
