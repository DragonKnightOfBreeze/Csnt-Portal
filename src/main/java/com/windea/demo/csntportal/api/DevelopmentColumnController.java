package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.DevelopmentColumnService;
import org.springframework.web.bind.annotation.*;

/**
 * 专业发展专栏的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/development-column")
public class DevelopmentColumnController {
	private final DevelopmentColumnService service;

	public DevelopmentColumnController(DevelopmentColumnService service) {this.service = service;}
}
