package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户的持久接口。
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
