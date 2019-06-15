package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.enums.DynamicCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 实时动态的持久接口。
 */
public interface DynamicRepository extends JpaRepository<Dynamic, Integer> {
	Page<Dynamic> findAllBySubjectContainsIgnoreCase(String subject, Pageable pageable);

	List<Dynamic> findAllBySponsorUser_Id(Integer id);

	Page<Dynamic> findAllBySponsorUser_Username(String username, Pageable pageable);

	Page<Dynamic> findAllByCategoryIn(Set<DynamicCategory> categorySet, Pageable pageable);

	Page<Dynamic> findAllBySponsorTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

	Page<Dynamic> findAllDistinctBySubjectContainsAndSponsorUser_UsernameAndCategoryInAllIgnoreCase(
		String subject, String username, Set<DynamicCategory> categorySet, Pageable pageable
	);
}
