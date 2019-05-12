package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import com.windea.demo.csntportal.exception.NoContentException;
import com.windea.demo.csntportal.exception.NotFoundException;
import com.windea.demo.csntportal.repository.StudyColumnRepository;
import com.windea.demo.csntportal.service.StudyColumnService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@CacheConfig(cacheNames = "studyColumnCache")
public class StudyColumnServiceImpl implements StudyColumnService {
	private final StudyColumnRepository repository;

	public StudyColumnServiceImpl(StudyColumnRepository repository) {this.repository = repository;}


	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public StudyColumn save(StudyColumn column) {
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
	public StudyColumn update(StudyColumn column) {
		var origin = findById(column.getId());
		origin.setTitle(column.getTitle());
		origin.setAuthor(column.getAuthor());
		origin.setContent(column.getContent());
		return repository.save(origin);
	}

	@Cacheable
	@Override
	public StudyColumn findById(Integer id) {
		var result = repository.findById(id)
			.orElseThrow(() -> {throw new NotFoundException();});
		return result;
	}

	@Cacheable
	@Override
	public Page<StudyColumn> findAllByTitle(String title, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		title = title.strip();
		var resultPage = repository.findAllByTitleContainingIgnoreCase(title, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new NoContentException();});
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<StudyColumn> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new NoContentException();});
		return resultPage;
	}
}
