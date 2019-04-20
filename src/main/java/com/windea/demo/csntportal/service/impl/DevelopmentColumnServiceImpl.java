package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.DevelopmentColumn;
import com.windea.demo.csntportal.exception.ResultEmptyException;
import com.windea.demo.csntportal.exception.ResultNotFoundException;
import com.windea.demo.csntportal.repository.DevelopmentColumnRepository;
import com.windea.demo.csntportal.service.DevelopmentColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class DevelopmentColumnServiceImpl implements DevelopmentColumnService {
	private final DevelopmentColumnRepository repository;

	public DevelopmentColumnServiceImpl(DevelopmentColumnRepository repository) {this.repository = repository;}


	@Override
	public DevelopmentColumn save(DevelopmentColumn developmentColumn) {
		return repository.save(developmentColumn);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public DevelopmentColumn update(DevelopmentColumn developmentColumn) {
		var origin = findById(developmentColumn.getId());
		origin.setTitle(developmentColumn.getTitle());
		origin.setAuthor(developmentColumn.getAuthor());
		origin.setContent(developmentColumn.getContent());
		return repository.save(origin);
	}


	@Override
	public DevelopmentColumn findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
		return result;
	}


	@Override
	public Page<DevelopmentColumn> findAllByTitleLike(String title, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		title = title.strip();
		Page<DevelopmentColumn> resultPage;
		if(title.isEmpty()) {
			resultPage = findAll(pageable);
		} else {
			resultPage = repository.findAllByTitleLikeIgnoreCase(title, pageable);
		}
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<DevelopmentColumn> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}
}
