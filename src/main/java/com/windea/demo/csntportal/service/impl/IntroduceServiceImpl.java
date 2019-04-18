package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.repository.IntroduceRepository;
import com.windea.demo.csntportal.service.IntroduceService;
import org.springframework.stereotype.Service;

@Service
public class IntroduceServiceImpl implements IntroduceService {
	private final IntroduceRepository repository;

	public IntroduceServiceImpl(IntroduceRepository repository) {this.repository = repository;}
}
