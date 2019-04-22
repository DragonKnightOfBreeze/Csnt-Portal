package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.enums.ProfessionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 教师队伍的持久接口。
 */
@RepositoryRestResource
public interface TeacherTeamRepository extends JpaRepository<TeacherTeam, Integer> {
	Page<TeacherTeam> findAllByNameContainingIgnoreCase(String title, Pageable pageable);

	Page<TeacherTeam> findAllByProfessionLevelIn(Set<ProfessionLevel> levelSet, Pageable pageable);

	Page<TeacherTeam> findAllByTeacherCountBetween(Integer start, Integer end, Pageable pageable);

	Page<TeacherTeam> findAllByCreateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
