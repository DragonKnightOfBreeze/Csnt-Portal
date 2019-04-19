package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.StudyColumnService;
import org.springframework.web.bind.annotation.*;

/**
 * 学习专栏的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/study/column")
public class StudyColumnController {
	private final StudyColumnService service;

	public StudyColumnController(StudyColumnService service) {this.service = service;}
}
