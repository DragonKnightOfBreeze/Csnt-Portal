package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户的服务接口。
 */
public interface UserService {
	User loginByUsername(String username, String password);

	User loginByEmail(String email, String password);

	User loginByPhoneNum(String phoneNum, String password);


	Page<User> findByNicknameLike(String nickname, Pageable pageable);

	Page<User> findByGender(Gender gender, Pageable pageable);

	Page<User> findByRole(Role role, Pageable pageable);

	Page<User> findByProfession(Profession profession, Pageable pageable);
}
