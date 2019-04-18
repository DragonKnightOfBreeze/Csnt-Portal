package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.StudyColumnService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学习专栏的控制器。
 */
@CrossOrigin
@RestController
public class StudyColumnController {
	private final StudyColumnService service;

	public StudyColumnController(StudyColumnService service) {this.service = service;}
}
