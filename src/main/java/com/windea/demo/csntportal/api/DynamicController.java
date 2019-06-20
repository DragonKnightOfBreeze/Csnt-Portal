package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.enums.DynamicCategory;
import com.windea.demo.csntportal.domain.vo.DynamicQueryVo;
import com.windea.demo.csntportal.exception.UserNotMatchedException;
import com.windea.demo.csntportal.exception.ValidationException;
import com.windea.demo.csntportal.service.DynamicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;
import java.util.Set;

/**
 * 实时动态的控制器。
 */
@CrossOrigin
@RestController
@RequestMapping("/dynamic")
public class DynamicController {
	private final DynamicService service;

	public DynamicController(DynamicService service) {
		this.service = service;
	}


	/**
	 * 新建动态信息。
	 */
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public Dynamic create(
		@Valid @RequestBody Dynamic dynamic,
		BindingResult bindingResult,
		Principal principal
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidationException(bindingResult.getAllErrors());});

		var username = principal.getName();
		var result = service.createBySponsorUsername(dynamic, username);
		return result;
	}

	/**
	 * 删除动态信息。
	 */
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/{id}")
	public void delete(
		@PathVariable Integer id,
		Principal principal
	) {
		//判断当前用户是否是管理员，如果不是，则只能删除自己发布的动态
		var role = ((UserDetails) principal).getAuthorities().toArray()[0].toString();
		if(!Objects.equals(role, "ROLE_ADMIN")) {
			var username = principal.getName();
			var sponsorUsername = service.get(id).getSponsorUser().getUsername();
			var matched = Objects.equals(username, sponsorUsername);
			Assert.isTrue(matched, () -> {throw new UserNotMatchedException();});
		}
		service.delete(id);
	}

	/**
	 * 得到动态信息。
	 */
	@GetMapping("/{id}")
	public Dynamic get(
		@PathVariable Integer id
	) {
		var result = service.get(id);
		return result;
	}

	/**
	 * 查询所有动态信息。
	 */
	@GetMapping("/list")
	public Page<Dynamic> list(
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.list(pageable);
		return resultPage;
	}

	/**
	 * 根据主题查询动态信息。
	 */
	@GetMapping(value = "/search", params = "subject")
	public Page<Dynamic> searchBySubject(
		@RequestParam String subject,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.searchBySubject(subject, pageable);
		return resultPage;
	}

	/**
	 * 根据发起用户的用户名查询动态信息。
	 */
	@GetMapping(value = "/search", params = "sponsorUsername")
	public Page<Dynamic> searchBySponsorUsername(
		@RequestParam String sponsorUsername,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.searchBySponsorUsername(sponsorUsername, pageable);
		return resultPage;
	}

	/**
	 * 根据分类查询动态信息。
	 */
	@GetMapping(value = "/search", params = "categorySet")
	public Page<Dynamic> searchByCategory(
		@RequestParam Set<DynamicCategory> categorySet,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.searchByCategory(categorySet, pageable);
		return resultPage;
	}

	/**
	 * 根据高级查询查询动态信息。
	 */
	@PostMapping("/advanceSearch")
	public Page<Dynamic> advanceSearch(
		@RequestBody DynamicQueryVo vo,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.advanceSearch(vo, pageable);
		return resultPage;
	}
}
