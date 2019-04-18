package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户的控制器。
 */
@CrossOrigin
@RestController
public class UserController {
	private final UserService service;

	public UserController(UserService service) {this.service = service;}
}
