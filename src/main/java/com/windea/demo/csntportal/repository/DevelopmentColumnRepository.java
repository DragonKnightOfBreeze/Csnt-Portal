package com.windea.demo.csntportal.repository;


import com.windea.demo.csntportal.domain.entity.DevelopmentColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 专业发展专栏的持久接口。
 */
public interface DevelopmentColumnRepository extends JpaRepository<DevelopmentColumn, Integer> {
	Page<DevelopmentColumn> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
