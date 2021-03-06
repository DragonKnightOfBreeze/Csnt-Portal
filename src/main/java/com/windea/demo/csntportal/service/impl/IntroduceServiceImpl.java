package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.Introduce;
import com.windea.demo.csntportal.exception.NotFoundException;
import com.windea.demo.csntportal.repository.IntroduceRepository;
import com.windea.demo.csntportal.service.IntroduceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IntroduceServiceImpl implements IntroduceService {
	private final IntroduceRepository repository;

	public IntroduceServiceImpl(IntroduceRepository repository) {this.repository = repository;}


	@Transactional
	@Override
	public Introduce create(Introduce introduce) {
		return repository.save(introduce);
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public Introduce update(Introduce introduce) {
		var origin = get(introduce.getId());
		origin.setTitle(introduce.getTitle());
		origin.setAuthor(introduce.getAuthor());
		origin.setContent(introduce.getContent());
		return repository.save(origin);
	}

	@Override
	public Introduce get(Integer id) {
		var result = repository.findById(id)
			.orElseThrow(() -> {throw new NotFoundException();});
		return result;
	}

	@Override
	public List<Introduce> list() {
		var resultList = repository.findAll();
		return resultList;
	}
}
