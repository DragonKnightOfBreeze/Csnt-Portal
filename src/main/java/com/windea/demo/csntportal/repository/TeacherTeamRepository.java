package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.domain.enums.ProfessionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 教师队伍的持久接口。
 */
public interface TeacherTeamRepository extends JpaRepository<TeacherTeam, Integer> {
	Page<TeacherTeam> findAllByNameContainsIgnoreCase(String title, Pageable pageable);

	Page<TeacherTeam> findAllByTeacherCountBetween(Integer min, Integer max, Pageable pageable);

	Page<TeacherTeam> findAllByProfessionLevelIn(Set<ProfessionLevel> levelSet, Pageable pageable);

	Page<TeacherTeam> findAllByCreateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

	Page<TeacherTeam> findAllByNameContainsAndTeacherCountBetweenAndProfessionLevelIn(
		String title, Integer min, Integer max, Set<ProfessionLevel> levelSet, Pageable pageable
	);
}
