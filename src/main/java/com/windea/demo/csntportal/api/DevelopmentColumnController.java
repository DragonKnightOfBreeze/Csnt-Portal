package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.DevelopmentColumn;
import com.windea.demo.csntportal.exception.ValidationException;
import com.windea.demo.csntportal.service.DevelopmentColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 专业发展专栏的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/development-column")
public class DevelopmentColumnController {
	private final DevelopmentColumnService service;

	public DevelopmentColumnController(DevelopmentColumnService service) {this.service = service;}


	/**
	 * 新建专业发展专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/create")
	public DevelopmentColumn create(
		@Valid @RequestBody DevelopmentColumn column,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult.getAllErrors());});

		var result = service.create(column);
		return result;
	}

	/**
	 * 删除专业发展专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public void delete(
		@PathVariable Integer id
	) {
		service.delete(id);
	}

	/**
	 * 更新专业发展专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public DevelopmentColumn update(
		@PathVariable Integer id,
		@Valid @RequestBody DevelopmentColumn column,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult.getAllErrors());});

		var result = service.update(column);
		return result;
	}

	/**
	 * 得到专业发展专栏信息。
	 */
	@GetMapping("/{id}")
	public DevelopmentColumn get(
		@PathVariable Integer id
	) {
		var result = service.get(id);
		return result;
	}

	/**
	 * 查询所有专业发展专栏信息。
	 */
	@GetMapping("/list")
	public Page<DevelopmentColumn> list(
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.list(pageable);
		return resultPage;
	}

	/**
	 * 根据标题查询专业发展专栏信息。
	 */
	@GetMapping(value = "/search", params = "title")
	public Page<DevelopmentColumn> searchByTitle(
		@RequestParam String title,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.searchByTitle(title, pageable);
		return resultPage;
	}
}
