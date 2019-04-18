package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.repository.UserRepository;
import com.windea.demo.csntportal.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repository;

	public UserServiceImpl(UserRepository repository) {this.repository = repository;}
}
