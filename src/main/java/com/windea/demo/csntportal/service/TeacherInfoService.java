package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import com.windea.demo.csntportal.domain.entity.TeacherTeam;

/**
 * 教师详情的服务接口。
 */
public interface TeacherInfoService {
	TeacherInfo saveByTeacherTeamId(TeacherInfo teacherInfo, Integer teacherTeamId);

	void deleteById(Integer id);

	TeacherInfo update(TeacherInfo teacherInfo);

	TeacherInfo findById(Integer id);

	TeacherTeam findTeacherTeamById(Integer id);
}
