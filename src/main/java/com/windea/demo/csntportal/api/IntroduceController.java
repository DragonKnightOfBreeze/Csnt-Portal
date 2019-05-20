package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.Introduce;
import com.windea.demo.csntportal.exception.ValidationException;
import com.windea.demo.csntportal.service.IntroduceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 专业特色介绍的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/introduce")
public class IntroduceController {
	private final IntroduceService service;

	public IntroduceController(IntroduceService service) {this.service = service;}


	/**
	 * 新建专业特色介绍信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/create")
	public Introduce create(
		@Valid @RequestBody Introduce introduce,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult);});

		var result = service.save(introduce);
		return result;
	}

	/**
	 * 删除专业特色介绍信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public void delete(
		@PathVariable Integer id
	) {
		service.deleteById(id);
	}

	/**
	 * 更新专业特色介绍信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public Introduce update(
		@PathVariable Integer id,
		@Valid @RequestBody Introduce introduce,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult);});

		var result = service.update(introduce);
		return result;
	}

	/**
	 * 得到专业特色介绍信息。
	 */
	@GetMapping("/{id}")
	public Introduce get(
		@PathVariable Integer id
	) {
		var result = service.findById(id);
		return result;
	}

	/**
	 * 查询所有专业特色介绍信息。
	 */
	@GetMapping("/list")
	public List<Introduce> list() {
		var resultList = service.findAll();
		return resultList;
	}
}
