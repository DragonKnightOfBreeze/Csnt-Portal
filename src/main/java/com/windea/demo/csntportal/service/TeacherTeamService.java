package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.domain.vo.TeacherTeamSearchVo;
import com.windea.demo.csntportal.enums.ProfessionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * 教师队伍的服务接口。
 */
public interface TeacherTeamService {
	TeacherTeam save(TeacherTeam teacherTeam);

	void deleteById(Integer id);

	TeacherTeam update(TeacherTeam teacherTeam);

	TeacherTeam findById(Integer id);

	Page<TeacherTeam> findAll(Pageable pageable);

	Page<TeacherTeam> findAllByName(String name, Pageable pageable);

	Page<TeacherTeam> findAllByProfessionLevel(Set<ProfessionLevel> levelSet, Pageable pageable);

	Page<TeacherTeam> findAllByTeacherCount(Integer min, Integer max, Pageable pageable);

	Page<TeacherTeam> findAllByConditions(TeacherTeamSearchVo vo, Pageable pageable);

}
