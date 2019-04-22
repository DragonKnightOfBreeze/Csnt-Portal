package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.Introduce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 专业特色介绍的持久接口。
 */
@RepositoryRestResource
public interface IntroduceRepository extends JpaRepository<Introduce, Integer> {
}
