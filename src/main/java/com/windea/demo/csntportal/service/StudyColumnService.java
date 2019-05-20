package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 学习专栏的服务接口。
 */
public interface StudyColumnService {
	StudyColumn create(StudyColumn column);

	void delete(Integer id);

	StudyColumn update(StudyColumn column);

	StudyColumn get(Integer id);

	Page<StudyColumn> list(Pageable pageable);

	Page<StudyColumn> searchByTitle(String title, Pageable pageable);
}
