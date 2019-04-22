package com.windea.demo.csntportal.api;

import com.windea.commons.base.exception.NotImplementedException;
import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.request.UserLoginVo;
import com.windea.demo.csntportal.domain.request.UserResetPasswordVo;
import com.windea.demo.csntportal.domain.response.JwtResponseVo;
import com.windea.demo.csntportal.exception.UserDuplicateException;
import com.windea.demo.csntportal.exception.UserNotFoundException;
import com.windea.demo.csntportal.security.JwtProvider;
import com.windea.demo.csntportal.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

/**
 * 用户的控制器。
 */
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
		@Valid @RequestBody User user, BindingResult bindingResult
	) {
		//首先处理已判定的验证错误，然后调用服务层，捕获异常分别处理
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult);
		}
		try {
			var result = service.save(user);
			return ResponseEntity.ok(result);
		} catch(UserDuplicateException e) {
			bindingResult.reject("user.duplicate");
			return ResponseEntity.badRequest().body(bindingResult);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * 登录用户。使用参数验证。
	 */
	@PostMapping("/login")
	public ResponseEntity login(
		@Valid @RequestBody UserLoginVo vo, BindingResult bindingResult
	) {
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult);
		}
		try {
			//创建验证对象，然后进行验证，然后保存到SecurityContextHolder中
			var auth = new UsernamePasswordAuthenticationToken(vo.getUsername(), vo.getPassword());
			var fullAuth = authenticationManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(fullAuth);
			//生成jwt口令并据此找到用户实体类对象
			String jwt = jwtProvider.generate(fullAuth);
			var userDetails = (UserDetails) fullAuth.getPrincipal();
			var user = service.findByUsername(userDetails.getUsername());

			var result = new JwtResponseVo(jwt, user.getUsername(), user.getRole());
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}


	/**
	 * 修改用户信息。适用参数验证和权限认证。
	 */
	@PutMapping("/account")
	public ResponseEntity update(
		@Valid @RequestBody User user, BindingResult bindingResult,
		Principal principal
	) {
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult);
		}
		try {
			//一般情况下，principal.name返回的是用户详情实体类用于验证的字段，这里是userDetails.username
			var authenticated = Objects.equals(user.getUsername(), principal.getName());
			Assert.isTrue(!authenticated, () -> {throw new UserNotFoundException();});
			var result = service.update(user);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}


	/**
	 * 重置用户密码。适用参数验证。
	 */
	@PutMapping("/reset-password")
	public ResponseEntity resetPassword(
		@Valid @RequestBody UserResetPasswordVo vo, BindingResult bindingResult,
		Principal principal
	) {
		throw new NotImplementedException();
	}


	/**
	 * 得到用户的账户信息。适用权限认证。
	 */
	@GetMapping("/account/{username}")
	public ResponseEntity<User> getAccountInfo(
		@PathVariable String username,
		Principal principal
	) {
		try {
			//一般情况下，principal.name返回的是用户详情实体类用于验证的字段，这里是userDetails.username
			var authenticated = Objects.equals(username, principal.getName());
			Assert.isTrue(!authenticated, () -> {throw new UserNotFoundException();});
			var result = service.findByUsername(username);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 得到用户信息。
	 */
	@GetMapping(value = "/user/{id}", params = "admin")
	public ResponseEntity<User> get(
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
	 * 查询所有用户信息。
	 */
	@GetMapping(value = "/user/list", params = "admin")
	public ResponseEntity<Page<User>> list(
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
	 * 根据昵称查询用户信息。
	 */
	@GetMapping(value = "/user/search", params = "admin")
	public ResponseEntity<Page<User>> searchByNickname(
		@RequestParam String nickname,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer size
	) {
		try {
			var pageable = PageRequest.of(page - 1, size);
			var resultPage = service.findAllByNicknameContaining(nickname, pageable);
			return ResponseEntity.ok(resultPage);
		} catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
}
