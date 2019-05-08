package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.ReformColumn;
import com.windea.demo.csntportal.exception.ValidationException;
import com.windea.demo.csntportal.service.ReformColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 教学改革专栏的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/reform-column")
public class ReformColumnController {
	private final ReformColumnService service;

	public ReformColumnController(ReformColumnService service) {this.service = service;}


	/**
	 * 新建教学改革专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/create")
	public ReformColumn create(
		@Valid @RequestBody ReformColumn column,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult);});

		var result = service.save(column);
		return result;
	}

	/**
	 * 删除教学改革专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public void delete(
		@PathVariable Integer id
	) {
		service.deleteById(id);
	}

	/**
	 * 更新教学改革专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/update")
	public ReformColumn update(
		@Valid @RequestBody ReformColumn column,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult);});

		var result = service.update(column);
		return result;
	}


	/**
	 * 得到教学改革专栏信息。
	 */
	@GetMapping("/{id}")
	public ReformColumn get(
		@PathVariable Integer id
	) {
		var result = service.findById(id);
		return result;
	}


	/**
	 * 查询所有教学改革专栏信息。
	 */
	@GetMapping("/list")
	public Page<ReformColumn> list(
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAll(pageable);
		return resultPage;
	}

	/**
	 * 根据标题查询教学改革专栏信息。
	 */
	@GetMapping(value = "/search", params = "method=title")
	public Page<ReformColumn> searchByTitle(
		@RequestParam String title,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByTitle(title, pageable);
		return resultPage;
	}
}
