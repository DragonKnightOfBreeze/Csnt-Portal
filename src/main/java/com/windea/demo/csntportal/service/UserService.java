package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户的服务接口。<br>
 * 继承UserDetailsService接口以进行安全验证。继承UserDetailsPasswordService接口以修改密码。
 */
public interface UserService extends UserDetailsService, UserDetailsPasswordService {
	User save(User user);

	User update(User user);


	User findById(Integer id);

	User findByUsername(String username);


	Page<User> findAll(Pageable pageable);

	Page<User> findAllByNicknameContaining(String nickname, Pageable pageable);

	Page<User> findAllByGender(Gender gender, Pageable pageable);

	Page<User> findAllByRole(Role role, Pageable pageable);

	Page<User> findAllByProfession(Profession profession, Pageable pageable);


	boolean exists(String username, String email, String phoneNum);
}
