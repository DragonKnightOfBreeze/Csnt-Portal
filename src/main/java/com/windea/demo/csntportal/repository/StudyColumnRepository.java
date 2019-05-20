package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 学习专栏的持久接口。
 */
public interface StudyColumnRepository extends JpaRepository<StudyColumn, Integer> {
	Page<StudyColumn> findAllByTitleContainsIgnoreCase(String title, Pageable pageable);
}
