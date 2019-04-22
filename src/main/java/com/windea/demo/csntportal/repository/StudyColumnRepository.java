package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 学习专栏的持久接口。
 */
@RepositoryRestResource
public interface StudyColumnRepository extends JpaRepository<StudyColumn, Integer> {
	Page<StudyColumn> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}
