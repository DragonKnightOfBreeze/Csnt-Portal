package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.repository.StudyColumnRepository;
import com.windea.demo.csntportal.service.StudyColumnService;
import org.springframework.stereotype.Service;

@Service
public class StudyColumnServiceImpl implements StudyColumnService {
	private final StudyColumnRepository repository;

	public StudyColumnServiceImpl(StudyColumnRepository repository) {this.repository = repository;}
}
