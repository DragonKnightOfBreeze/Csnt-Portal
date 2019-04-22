package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.DevelopmentColumn;
import com.windea.demo.csntportal.service.DevelopmentColumnService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PostMapping(value = "/create", params = "admin")
	public ResponseEntity create(
		@Valid @RequestBody DevelopmentColumn column, BindingResult bindingResult
	) {
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult);
		}
		try {
			var result = service.save(column);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * 删除专业发展专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}", params = "admin")
	public ResponseEntity delete(
		@PathVariable Integer id
	) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * 更新专业发展专栏信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/update", params = "admin")
	public ResponseEntity update(
		@Valid @RequestBody DevelopmentColumn column, BindingResult bindingResult
	) {
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult);
		}
		try {
			var result = service.update(column);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}


	/**
	 * 得到专业发展专栏信息。
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DevelopmentColumn> get(
		@PathVariable Integer id
	) {
		try {
			var result = service.findById(id);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}


	/**
	 * 查询所有专业发展专栏信息。
	 */
	@GetMapping("/list")
	public ResponseEntity<Page<DevelopmentColumn>> list(
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		try {
			var pageable = PageRequest.of(page - 1, size);
			var resultPage = service.findAll(pageable);
			return ResponseEntity.ok(resultPage);
		} catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * 根据标题查询专业发展专栏信息。
	 */
	@GetMapping("/search")
	public ResponseEntity<Page<DevelopmentColumn>> searchByTitle(
		@RequestParam String title,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		try {
			var pageable = PageRequest.of(page - 1, size);
			var resultPage = service.findAllByTitleContaining(title, pageable);
			return ResponseEntity.ok(resultPage);
		} catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
}
