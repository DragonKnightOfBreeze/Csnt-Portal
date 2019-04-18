package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.repository.ReformColumnRepository;
import com.windea.demo.csntportal.service.ReformColumnService;
import org.springframework.stereotype.Service;

@Service
public class ReformColumnServiceImpl implements ReformColumnService {
	private final ReformColumnRepository repository;

	public ReformColumnServiceImpl(ReformColumnRepository repository) {this.repository = repository;}
}
