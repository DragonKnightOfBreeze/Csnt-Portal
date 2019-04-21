package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.projection.DynamicPr;
import com.windea.demo.csntportal.enums.DynamicCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 实时动态的持久接口。<br>
 * NOTE 可以直接调用实体类的实体类属性的属性
 */
public interface DynamicRepository extends JpaRepository<Dynamic, Integer> {
	Page<Dynamic> findAllBySubjectContainingIgnoreCase(String title, Pageable pageable);

	Page<Dynamic> findAllByCategoryIn(Set<DynamicCategory> categorySet, Pageable pageable);

	@Query("select d from Dynamic d where d.sponsorUser.username=:username")
	Page<Dynamic> findAllBySponsorUsername(@Param("username") String username, Pageable pageable);

	Page<Dynamic> findAllBySponsorTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);


	@Query("select u as sponsorUser from Dynamic d join d.sponsorUser u where d.id=:id")
	DynamicPr findSponsorUserById(@Param("id") Integer id);
}
