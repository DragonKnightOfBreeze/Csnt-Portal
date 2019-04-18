package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.IntroduceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专业特色介绍的控制器。
 */
@CrossOrigin
@RestController
public class IntroduceController {
	private final IntroduceService service;

	public IntroduceController(IntroduceService service) {this.service = service;}
}
