package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.domain.enums.ProfessionLevel;
import com.windea.demo.csntportal.domain.vo.TeacherTeamQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * 教师队伍的服务接口。
 */
public interface TeacherTeamService {
	TeacherTeam create(TeacherTeam teacherTeam);

	void delete(Integer id);

	TeacherTeam update(TeacherTeam teacherTeam);

	TeacherTeam get(Integer id);

	List<TeacherInfo> getTeacherInfoList(Integer id);

	Page<TeacherTeam> list(Pageable pageable);

	Page<TeacherTeam> searchByName(String name, Pageable pageable);

	Page<TeacherTeam> searchByTeacherCount(Integer min, Integer max, Pageable pageable);

	Page<TeacherTeam> searchByProfessionLevel(Set<ProfessionLevel> levelSet, Pageable pageable);

	Page<TeacherTeam> advanceSearch(TeacherTeamQueryVo vo, Pageable pageable);

}
