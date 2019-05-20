package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;

/**
 * 教师详情的服务接口。
 */
public interface TeacherInfoService {
	TeacherInfo createByTeacherTeamId(TeacherInfo teacherInfo, Integer teacherTeamId);

	void delete(Integer id);

	TeacherInfo update(TeacherInfo teacherInfo);

	TeacherInfo get(Integer id);
}
