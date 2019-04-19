package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.Admin;
import com.windea.demo.csntportal.exception.UserNotFoundException;
import com.windea.demo.csntportal.repository.AdminRepository;
import com.windea.demo.csntportal.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 管理员的服务类。
 */
@Service
public class AdminServiceImpl implements AdminService {
	private final AdminRepository repository;

	//使用基于构造方法的自动依赖注入，不需要使用@Autowired
	public AdminServiceImpl(AdminRepository repository) {this.repository = repository;}

	//断言结果是否为空，为空抛出运行时异常，否则返回结果，且此类本身不处理异常
	@Override
	public Admin login(String username, String password) {
		var result = repository.findByUsernameAndPassword(username, password);
		Assert.notNull(result, () -> {throw new UserNotFoundException();});
		return result;
	}
}
