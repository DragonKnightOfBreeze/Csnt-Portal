package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import com.windea.demo.csntportal.domain.projection.TeacherInfoPr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 教师详情的持久接口。
 */
public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Integer> {
	@Query("select ti.teacherTeam as teacherTeam from TeacherInfo ti where ti.id=:id")
	TeacherInfoPr findTeacherTeamById(Integer id);
}
