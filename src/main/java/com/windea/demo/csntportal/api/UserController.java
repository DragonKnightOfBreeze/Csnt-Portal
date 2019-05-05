package com.windea.demo.csntportal.api;

import com.windea.commons.base.exception.NotImplementedException;
import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.vo.UserLoginVo;
import com.windea.demo.csntportal.domain.vo.UserResetPasswordVo;
import com.windea.demo.csntportal.exception.UserNotAcceptedException;
import com.windea.demo.csntportal.exception.ValidateException;
import com.windea.demo.csntportal.security.*;
import com.windea.demo.csntportal.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

/**
 * 用户的控制器。
 */
//@CrossOrigin用于适用跨域网页请求
//映射方法不要返回ResponseEntity，请自定义错误控制器，编写注有@ExceptionHandler(YourException.class)的方法
@CrossOrigin
@RestController
public class UserController {
	private final UserService service;
	private final JwtProvider jwtProvider;
	private final AuthenticationManager authenticationManager;

	public UserController(UserService service, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
		this.service = service;
		this.jwtProvider = jwtProvider;
		this.authenticationManager = authenticationManager;
	}


	/**
	 * 注册用户。适用参数验证。
	 */
	@PostMapping("/register")
	public ResponseEntity register(
		@Valid @RequestBody User user,
		BindingResult bindingResult
	) {
		//首先处理已判定的验证错误，然后调用服务层，异常处理交由全局异常处理器
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidateException();});

		var result = service.save(user);
		return ResponseEntity.ok(result);
	}

	/**
	 * 登录用户。使用参数验证。
	 */
	@PostMapping("/login")
	public JwtResponseVo login(
		@Valid @RequestBody UserLoginVo vo,
		BindingResult bindingResult
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidateException();});

		//创建验证令牌，然后进行验证，然后保存到SecurityContextHolder中
		var auth = new UsernamePasswordAuthenticationToken(vo.getUsername(), vo.getPassword());
		var validAuth = authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(validAuth);
		//生成jwt口令并据此找到用户详情实体类对象
		String jwt = jwtProvider.generate(validAuth);

		//根据用户详情实体类对象中的用户名字段，从数据库进行查找
		//NOTE 没有必要在服务层编写根据用户名和密码查找用户的方法，数据库中的密码已加密
		var userDetails = (JwtUserDetails) validAuth.getPrincipal();
		//NOTE 返回的是包含jwt令牌的jwt响应视图对象
		var role = userDetails.getAuthorities().toArray()[0].toString();
		var result = new JwtResponseVo(jwt, userDetails.getUsername(), role);
		return result;
	}


	/**
	 * 修改用户信息。适用参数验证和权限认证。
	 */
	@PutMapping("/account")
	public User update(
		@Valid @RequestBody User user,
		BindingResult bindingResult,
		Principal principal
	) {
		var validated = !bindingResult.hasErrors();
		Assert.isTrue(validated, () -> {throw new ValidateException();});

		//一般情况下，principal.name返回的是用户详情实体类用于验证的字段，这里是userDetails.username
		var authenticated = Objects.equals(user.getUsername(), principal.getName());
		Assert.isTrue(authenticated, () -> {throw new UserNotAcceptedException();});

		var result = service.update(user);
		return result;
	}


	/**
	 * 重置用户密码。适用参数验证。
	 */
	@PutMapping("/reset-password")
	public ResponseEntity resetPassword(
		@Valid @RequestBody UserResetPasswordVo vo,
		BindingResult bindingResult,
		Principal principal
	) {
		throw new NotImplementedException();
	}


	/**
	 * 得到用户的账户信息。适用权限认证。
	 */
	@GetMapping("/account/{username}")
	public User getAccountInfo(
		@PathVariable String username,
		Principal principal
	) {
		//一般情况下，principal.name返回的是用户详情实体类用于验证的字段，这里是userDetails.username
		var authenticated = Objects.equals(username, principal.getName());
		Assert.isTrue(authenticated, () -> {throw new UserNotAcceptedException();});

		var result = service.findByUsername(username);
		return result;
	}

	/**
	 * 得到用户信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/user/{id}", params = "role=admin")
	public User get(
		@PathVariable Integer id
	) {
		var result = service.findById(id);
		return result;
	}

	/**
	 * 查询所有用户信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/user/list", params = "role=admin")
	public Page<User> list(
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAll(pageable);
		return resultPage;
	}

	/**
	 * 根据昵称查询用户信息。
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/user/search", params = {"role=admin", "method=nickname"})
	public Page<User> searchByNickname(
		@RequestParam String nickname,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		var pageable = PageRequest.of(page - 1, size);
		var resultPage = service.findAllByNickname(nickname, pageable);
		return resultPage;
	}
}
