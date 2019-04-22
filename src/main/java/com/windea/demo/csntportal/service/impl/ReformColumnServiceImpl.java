package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.ReformColumn;
import com.windea.demo.csntportal.exception.ResultEmptyException;
import com.windea.demo.csntportal.exception.ResultNotFoundException;
import com.windea.demo.csntportal.repository.ReformColumnRepository;
import com.windea.demo.csntportal.service.ReformColumnService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@CacheConfig(cacheNames = "reformColumnCache")
public class ReformColumnServiceImpl implements ReformColumnService {
	private final ReformColumnRepository repository;

	public ReformColumnServiceImpl(ReformColumnRepository repository) {this.repository = repository;}


	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public ReformColumn save(ReformColumn column) {
		return repository.save(column);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@CachePut
	@Transactional
	@Override
	public ReformColumn update(ReformColumn column) {
		var origin = findById(column.getId());
		origin.setTitle(column.getTitle());
		origin.setAuthor(column.getAuthor());
		origin.setContent(column.getContent());
		return repository.save(origin);
	}


	@Cacheable
	@Override
	public ReformColumn findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
		return result;
	}


	@Cacheable
	@Override
	public Page<ReformColumn> findAllByTitleContaining(String title, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		title = title.strip();
		var resultPage = repository.findAllByTitleContainingIgnoreCase(title, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<ReformColumn> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}
}
