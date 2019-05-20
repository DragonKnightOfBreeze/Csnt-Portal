package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户的服务接口。
 */
public interface UserService {
	User save(User user);

	User update(User user);

	User findById(Integer id);

	User findByUsername(String username);

	List<Dynamic> getDynamicListById(Integer id);

	Page<User> findAll(Pageable pageable);

	Page<User> findAllByNickname(String nickname, Pageable pageable);

	Page<User> findAllByGender(Gender gender, Pageable pageable);

	Page<User> findAllByRole(Role role, Pageable pageable);

	Page<User> findAllByProfession(Profession profession, Pageable pageable);

	boolean exists(String username, String email, String phoneNum);
}
