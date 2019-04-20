package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.ReformColumn;
import com.windea.demo.csntportal.exception.ResultEmptyException;
import com.windea.demo.csntportal.exception.ResultNotFoundException;
import com.windea.demo.csntportal.repository.ReformColumnRepository;
import com.windea.demo.csntportal.service.ReformColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ReformColumnServiceImpl implements ReformColumnService {
	private final ReformColumnRepository repository;

	public ReformColumnServiceImpl(ReformColumnRepository repository) {this.repository = repository;}


	@Override
	public ReformColumn save(ReformColumn developmentColumn) {
		return repository.save(developmentColumn);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public ReformColumn update(ReformColumn developmentColumn) {
		var origin = findById(developmentColumn.getId());
		origin.setTitle(developmentColumn.getTitle());
		origin.setAuthor(developmentColumn.getAuthor());
		origin.setContent(developmentColumn.getContent());
		return repository.save(origin);
	}


	@Override
	public ReformColumn findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
		return result;
	}


	@Override
	public Page<ReformColumn> findAllByTitleLike(String title, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		title = title.strip();
		Page<ReformColumn> resultPage;
		if(title.isEmpty()) {
			resultPage = findAll(pageable);
		} else {
			resultPage = repository.findAllByTitleLikeIgnoreCase(title, pageable);
		}
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<ReformColumn> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}
}
