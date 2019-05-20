package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 教师详情的持久接口。
 */
public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Integer> {
	List<TeacherInfo> findAllByTeacherTeam_Id(Integer id);

	Page<TeacherInfo> findAllByTeacherTeam_NameContainsIgnoreCase(String id, Pageable pageable);
}
