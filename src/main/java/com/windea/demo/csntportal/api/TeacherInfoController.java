package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import com.windea.demo.csntportal.exception.ValidationException;
import com.windea.demo.csntportal.service.TeacherInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 教师详情的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher-info")
public class TeacherInfoController {
	private final TeacherInfoService service;

	public TeacherInfoController(TeacherInfoService service) {this.service = service;}


	/**
	 * 新增教师信息。
	 */
	@PostMapping("/create")
	public TeacherInfo create(
		@Valid @RequestBody TeacherInfo teacherInfo,
		BindingResult bindingResult,
		Integer teacherTeamId
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult.getAllErrors());});

		var result = service.createByTeacherTeamId(teacherInfo, teacherTeamId);
		return result;
	}

	/**
	 * 删除教师信息。
	 */
	@DeleteMapping("/{id}")
	public void delete(
		@PathVariable Integer id
	) {
		service.delete(id);
	}

	/**
	 * 更新教师信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public TeacherInfo update(
		@PathVariable Integer id,
		@Valid @RequestBody TeacherInfo teacherInfo,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult.getAllErrors());});

		var result = service.update(teacherInfo);
		return result;
	}

	/**
	 * 得到教师信息。
	 */
	@GetMapping("/{id}")
	public TeacherInfo get(
		@PathVariable Integer id
	) {
		var result = service.get(id);
		return result;
	}
}
