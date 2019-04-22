package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.vo.DynamicSearchVo;
import com.windea.demo.csntportal.enums.DynamicCategory;
import com.windea.demo.csntportal.exception.ValidateException;
import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

/**
 * 实时动态的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/dynamic")
public class DynamicController {
	private final DynamicService service;

	public DynamicController(DynamicService service) {this.service = service;}


	/**
	 * 新建动态信息。
	 */
	@PreAuthorize("hasAnyRole('STUDENT','TEACHER','VISITOR')")
	@PostMapping(value = "/create")
	public Dynamic create(
		@Valid @RequestBody Dynamic dynamic,
		BindingResult bindingResult,
		Principal principal
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidateException();});

		var username = principal.getName();
		var result = service.saveBySponsorUsername(dynamic, username);
		return result;
	}

	/**
	 * 删除动态信息。
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
	 * 删除用户的动态信息。
	 */
	@PreAuthorize("hasAnyRole('STUDENT','TEACHER','VISITOR')")
	@DeleteMapping(value = "/{id}", params = "role=nonAdmin")
	public ResponseEntity deleteByUser(
		@PathVariable Integer id,
		Principal principal
	) {
		var username = principal.getName();
		service.deleteByIdAndSponsorUsername(id, username);
		return ResponseEntity.ok().build();
	}


	/**
	 * 得到动态信息。
	 */
	@GetMapping("/{id}")
	public Dynamic get(
		@PathVariable Integer id
	) {
		var result = service.findById(id);
		return result;
	}

	/**
	 * 得到发起用户信息。
	 */
	@GetMapping("/{id}/sponsor-user")
	public User getSponsorUser(
		@PathVariable Integer id
	) {
		var result = service.findSponsorUserById(id);
		return result;
	}


	/**
	 * 查询所有动态信息。
	 */
	@GetMapping("/list")
	public Page<Dynamic> list(
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAll(pageable);
		return resultPage;
	}

	/**
	 * 根据主题查询动态信息。
	 */
	@GetMapping(value = "/search", params = "method=subject")
	public Page<Dynamic> searchBySubject(
		@RequestParam String subject,
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllBySubject(subject, pageable);
		return resultPage;
	}

	/**
	 * 根据发起用户的用户名查询动态信息。
	 */
	@GetMapping(value = "/search", params = "method=sponsorUsername")
	public Page<Dynamic> searchBySponsorUsername(
		@RequestParam String sponsorUsername,
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllBySponsorUsername(sponsorUsername, pageable);
		return resultPage;
	}

	/**
	 * 根据分类查询动态信息。
	 */
	@GetMapping(value = "/search", params = "method=dynamicCategory")
	public Page<Dynamic> searchBySubject(
		@RequestParam Set<DynamicCategory> categorySet,
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByCategory(categorySet, pageable);
		return resultPage;
	}

	/**
	 * 根据高级查询查询动态信息。
	 */
	@GetMapping(value = "/advanceSearch")
	public Page<Dynamic> advanceSearch(
		@RequestParam DynamicSearchVo vo,
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByConditions(vo, pageable);
		return resultPage;
	}
}
