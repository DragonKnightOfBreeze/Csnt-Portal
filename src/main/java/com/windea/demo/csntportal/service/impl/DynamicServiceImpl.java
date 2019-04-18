package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.repository.DynamicRepository;
import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.stereotype.Service;

@Service
public class DynamicServiceImpl implements DynamicService {
	private final DynamicRepository repository;

	public DynamicServiceImpl(DynamicRepository repository) {this.repository = repository;}
}
