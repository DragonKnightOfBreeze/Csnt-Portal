package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.repository.TeacherTeamRepository;
import com.windea.demo.csntportal.service.TeacherTeamService;
import org.springframework.stereotype.Service;

@Service
public class TeacherTeamServiceImpl implements TeacherTeamService {
	private final TeacherTeamRepository repository;

	public TeacherTeamServiceImpl(TeacherTeamRepository repository) {this.repository = repository;}
}
