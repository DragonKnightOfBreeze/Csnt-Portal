package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import com.windea.demo.csntportal.exception.ResultEmptyException;
import com.windea.demo.csntportal.exception.ResultNotFoundException;
import com.windea.demo.csntportal.repository.StudyColumnRepository;
import com.windea.demo.csntportal.service.StudyColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class StudyColumnServiceImpl implements StudyColumnService {
	private final StudyColumnRepository repository;

	public StudyColumnServiceImpl(StudyColumnRepository repository) {this.repository = repository;}


	@Override
	public StudyColumn save(StudyColumn developmentColumn) {
		return repository.save(developmentColumn);
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public StudyColumn update(StudyColumn developmentColumn) {
		var origin = findById(developmentColumn.getId());
		origin.setTitle(developmentColumn.getTitle());
		origin.setAuthor(developmentColumn.getAuthor());
		origin.setContent(developmentColumn.getContent());
		return repository.save(origin);
	}


	@Override
	public StudyColumn findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
		return result;
	}


	@Override
	public Page<StudyColumn> findAllByTitleLike(String title, Pageable pageable) {
		//如果搜索域为空，则查询所有数据
		title = title.strip();
		Page<StudyColumn> resultPage;
		if(title.isEmpty()) {
			resultPage = findAll(pageable);
		} else {
			resultPage = repository.findAllByTitleLikeIgnoreCase(title, pageable);
		}
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<StudyColumn> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}
}
