package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 实时动态的持久接口。
 */
public interface DynamicRepository extends JpaRepository<Dynamic, Integer> {
}
