package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实时动态的控制器。
 */
@CrossOrigin
@RestController
public class DynamicController {
	private final DynamicService service;

	public DynamicController(DynamicService service) {this.service = service;}
}
