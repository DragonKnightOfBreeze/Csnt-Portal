package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户的持久接口。
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsernameAndPassword(String username, String password);

	User findByPhoneNumAndPassword(String phoneNum, String password);

	User findByEmailAndPassword(String email, String password);


	Page<User> findAllByNicknameLike(String nickname, Pageable pageable);

	Page<User> findAllByGenderOrderByRoleAscProfessionAsc(Gender gender, Pageable pageable);

	Page<User> findAllByRoleOrderByGenderAscProfessionAsc(Role role, Pageable pageable);

	Page<User> findAllByProfessionOrderByGenderAscRoleAsc(Profession profession, Pageable pageable);
}
