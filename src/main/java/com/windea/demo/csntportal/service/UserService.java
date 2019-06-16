package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户的服务接口。
 */
public interface UserService {
	User register(User user);

	User update(User user);

	User get(Integer id);

	User getByUsername(String username);

	List<Dynamic> getDynamicList(Integer id);

	Page<User> list(Pageable pageable);

	Page<User> searchByNickname(String nickname, Pageable pageable);

	Page<User> searchByGender(Gender gender, Pageable pageable);

	Page<User> searchByRole(Role role, Pageable pageable);

	Page<User> searchByProfession(Profession profession, Pageable pageable);

	boolean exists(String username, String email, String phoneNum);
}
