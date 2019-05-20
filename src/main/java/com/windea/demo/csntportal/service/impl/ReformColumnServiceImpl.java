package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.ReformColumn;
import com.windea.demo.csntportal.exception.NotFoundException;
import com.windea.demo.csntportal.repository.ReformColumnRepository;
import com.windea.demo.csntportal.service.ReformColumnService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "reformColumnCache")
public class ReformColumnServiceImpl implements ReformColumnService {
	private final ReformColumnRepository repository;

	public ReformColumnServiceImpl(ReformColumnRepository repository) {this.repository = repository;}


	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public ReformColumn create(ReformColumn column) {
		return repository.save(column);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@CachePut
	@Transactional
	@Override
	public ReformColumn update(ReformColumn column) {
		var origin = get(column.getId());
		origin.setTitle(column.getTitle());
		origin.setAuthor(column.getAuthor());
		origin.setContent(column.getContent());
		return repository.save(origin);
	}

	@Cacheable
	@Override
	public ReformColumn get(Integer id) {
		var result = repository.findById(id)
			.orElseThrow(() -> {throw new NotFoundException();});
		return result;
	}

	@Cacheable
	@Override
	public Page<ReformColumn> searchByTitle(String title, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		title = title.strip();
		var resultPage = repository.findAllByTitleContainsIgnoreCase(title, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<ReformColumn> list(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		return resultPage;
	}
}
