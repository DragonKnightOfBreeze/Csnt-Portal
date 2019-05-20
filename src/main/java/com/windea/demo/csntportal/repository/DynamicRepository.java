package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.enums.DynamicCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 实时动态的持久接口。
 */
public interface DynamicRepository extends JpaRepository<Dynamic, Integer> {
	Page<Dynamic> findAllBySubjectContainingIgnoreCase(String subject, Pageable pageable);

	Page<Dynamic> findAllByCategoryIn(Set<DynamicCategory> categorySet, Pageable pageable);

	@Query("from Dynamic d where d.sponsorUser.username=:username")
	Page<Dynamic> findAllBySponsorUsername(String username, Pageable pageable);

	Page<Dynamic> findAllBySponsorTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
