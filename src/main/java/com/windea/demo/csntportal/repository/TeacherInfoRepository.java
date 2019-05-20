package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 教师详情的持久接口。
 */
public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Integer> {
}
