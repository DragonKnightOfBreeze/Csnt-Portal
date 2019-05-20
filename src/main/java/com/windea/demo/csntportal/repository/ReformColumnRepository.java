package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.ReformColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 教学改革专栏的持久接口。
 */
public interface ReformColumnRepository extends JpaRepository<ReformColumn, Integer> {
	Page<ReformColumn> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);
}
