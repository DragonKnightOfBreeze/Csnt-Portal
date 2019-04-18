package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.ReformColumnService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教学改革专栏的控制器。
 */
@CrossOrigin
@RestController
public class ReformColumnController {
	private final ReformColumnService service;

	public ReformColumnController(ReformColumnService service) {this.service = service;}
}
