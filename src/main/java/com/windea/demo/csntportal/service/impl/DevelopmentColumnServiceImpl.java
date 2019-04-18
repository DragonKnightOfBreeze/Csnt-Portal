package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.DevelopmentColumnService;
import org.springframework.stereotype.Service;

@Service
public class DevelopmentColumnServiceImpl implements DevelopmentColumnService {
	private final UserRepository repository;

	public DevelopmentColumnServiceImpl(UserRepository repository) {this.repository = repository;}
}
