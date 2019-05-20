package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.domain.vo.TeacherTeamSearchVo;
import com.windea.demo.csntportal.enums.ProfessionLevel;
import com.windea.demo.csntportal.exception.ValidationException;
import com.windea.demo.csntportal.service.TeacherTeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 教师队伍的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher-team")
public class TeacherTeamController {
	private final TeacherTeamService service;

	public TeacherTeamController(TeacherTeamService service) {this.service = service;}


	/**
	 * 新建教师队伍信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/create")
	public TeacherTeam create(
		@Valid @RequestBody TeacherTeam teacherTeam,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult);});

		var result = service.save(teacherTeam);
		return result;
	}

	/**
	 * 删除教师队伍信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public void delete(
		@PathVariable Integer id
	) {
		service.deleteById(id);
	}

	/**
	 * 更新教师队伍信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public TeacherTeam update(
		@PathVariable Integer id,
		@Valid @RequestBody TeacherTeam teacherTeam,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult);});

		var result = service.update(teacherTeam);
		return result;
	}

	/**
	 * 得到教师队伍信息。
	 */
	@GetMapping("/{id}")
	public TeacherTeam get(
		@PathVariable Integer id
	) {
		var result = service.findById(id);
		return result;
	}

	/**
	 * 得到教师队伍的教师信息列表。
	 */
	@GetMapping("/{id}/teacher-info-list")
	public List<TeacherInfo> getTeacherInfoList(
		@PathVariable Integer id
	) {
		var result = service.getTeacherInfoListById(id);
		return result;
	}

	/**
	 * 查询所有教师队伍信息。
	 */
	@GetMapping("/list")
	public Page<TeacherTeam> list(
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAll(pageable);
		return resultPage;
	}

	/**
	 * 根据名字查询教师队伍信息。
	 */
	@GetMapping(value = "/search", params = "name")
	public Page<TeacherTeam> searchByName(
		@RequestParam String name,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByName(name, pageable);
		return resultPage;
	}

	/**
	 * 根据专业级别查询教师队伍信息。
	 */
	@GetMapping(value = "/search", params = "professionLevel")
	public Page<TeacherTeam> searchByProfessionLevel(
		@RequestParam Set<ProfessionLevel> levelSet,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByProfessionLevel(levelSet, pageable);
		return resultPage;
	}

	/**
	 * 根据人数查询教师队伍信息。
	 */
	@GetMapping(value = "/search", params = {"min", "max"})
	public Page<TeacherTeam> searchByTeacherCount(
		@RequestParam Integer min,
		@RequestParam Integer max,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByTeacherCount(min, max, pageable);
		return resultPage;
	}

	/**
	 * 通过高级查询查询教师队伍信息。
	 */
	@PostMapping("/advanceSearch")
	public Page<TeacherTeam> advanceSearch(
		@RequestBody TeacherTeamSearchVo vo,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByConditions(vo, pageable);
		return resultPage;
	}
}
