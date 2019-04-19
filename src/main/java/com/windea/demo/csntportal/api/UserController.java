package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * 用户的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/account")
public class UserController {
	private final UserService service;

	public UserController(UserService service) {this.service = service;}
}
