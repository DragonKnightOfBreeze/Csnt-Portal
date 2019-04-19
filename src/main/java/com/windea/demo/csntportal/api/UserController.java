package com.windea.demo.csntportal.api;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.request.UserLoginVo;
import com.windea.demo.csntportal.domain.response.JwtResponseVo;
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
import org.springframework.web.bind.annotation.*;

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
	 * 注册用户。
	 */
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody User user) {
		try {
			var result = service.save(user);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * 修改用户信息。
	 */
	@PutMapping("/account")
	public ResponseEntity<User> update(@RequestBody User user, Principal principal) {
		try {
			//一般情况下，principal.name返回的是userDetails.username
			Assert.isTrue(Objects.equals(user.getUsername(), principal.getName()),
				() -> {throw new UserNotFoundException();});
			var result = service.update(user);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}


	/**
	 * 登录用户。
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtResponseVo> login(@RequestBody UserLoginVo loginForm) {
		try {
			//创建验证对象，然后进行验证，然后保存到SecurityContextHolder中
			var auth = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
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
	 * 得到用户的账户信息。
	 */
	@GetMapping("/account/{username}")
	public ResponseEntity<User> getAccountInfo(@PathVariable("username") String username, Principal principal) {
		try {
			//一般情况下，principal.name返回的是userDetails.username
			Assert.isTrue(Objects.equals(username, principal.getName()),
				() -> {throw new UserNotFoundException();});
			var result = service.findByUsername(username);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 得到用户信息。
	 */
	@GetMapping("/admin/user/{id}")
	public ResponseEntity<User> get(@PathVariable("id") Integer id) {
		try {
			var result = service.findById(id);
			return ResponseEntity.ok(result);
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 查询所有用户。
	 */
	@GetMapping("/admin/user/list")
	public ResponseEntity<Page<User>> list(
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
		try {
			var pageable = PageRequest.of(page - 1, size);
			var resultPage = service.findAll(pageable);
			return ResponseEntity.ok(resultPage);
		} catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * 根据昵称查询用户。
	 */
	@GetMapping("/admin/user/list")
	public ResponseEntity<Page<User>> searchByNickname(@RequestParam String nickname,
		@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
		try {
			var pageable = PageRequest.of(page - 1, size);
			var resultPage = service.findAllByNicknameLike(nickname, pageable);
			return ResponseEntity.ok(resultPage);
		} catch(Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
}
