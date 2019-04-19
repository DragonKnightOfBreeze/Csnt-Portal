package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.web.bind.annotation.*;

/**
 * 实时动态的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/dynamic")
public class DynamicController {
	private final DynamicService service;

	public DynamicController(DynamicService service) {this.service = service;}
}
