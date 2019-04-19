package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.service.TeacherTeamService;
import org.springframework.web.bind.annotation.*;

/**
 * 教师队伍的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher-team")
public class TeacherTeamController {
	private final TeacherTeamService service;

	public TeacherTeamController(TeacherTeamService service) {this.service = service;}
}
