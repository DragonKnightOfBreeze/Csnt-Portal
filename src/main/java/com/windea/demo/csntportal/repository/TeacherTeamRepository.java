package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 教师队伍的持久接口。
 */
public interface TeacherTeamRepository extends JpaRepository<TeacherTeam, Integer> {
}
