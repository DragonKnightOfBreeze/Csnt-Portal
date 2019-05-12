package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.Introduce;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 专业特色介绍的持久接口。
 */
public interface IntroduceRepository extends JpaRepository<Introduce, Integer> {
}
