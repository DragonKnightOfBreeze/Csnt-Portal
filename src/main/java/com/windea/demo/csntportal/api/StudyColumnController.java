package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import com.windea.demo.csntportal.exception.ValidateException;
import com.windea.demo.csntportal.service.StudyColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 学习专栏的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/study-column")
public class StudyColumnController {
	private final StudyColumnService service;

	public StudyColumnController(StudyColumnService service) {this.service = service;}


	/**
	 * 新建学习专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/create", params = "role=admin")
	public StudyColumn create(
		@Valid @RequestBody StudyColumn column,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidateException();});

		var result = service.save(column);
		return result;
	}

	/**
	 * 删除学习专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}", params = "role=admin")
	public ResponseEntity delete(
		@PathVariable Integer id
	) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * 更新学习专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/update", params = "role=admin")
	public StudyColumn update(
		@Valid @RequestBody StudyColumn column,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidateException();});

		var result = service.update(column);
		return result;
	}


	/**
	 * 得到学习专栏信息。
	 */
	@GetMapping("/{id}")
	public StudyColumn get(
		@PathVariable Integer id
	) {
		var result = service.findById(id);
		return result;
	}


	/**
	 * 查询所有学习专栏信息。
	 */
	@GetMapping("/list")
	public Page<StudyColumn> list(
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAll(pageable);
		return resultPage;
	}

	/**
	 * 根据标题查询学习专栏信息。
	 */
	@GetMapping(value = "/search", params = "method=title")
	public Page<StudyColumn> searchByTitle(
		@RequestParam String title,
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByTitleContaining(title, pageable);
		return resultPage;
	}
}
