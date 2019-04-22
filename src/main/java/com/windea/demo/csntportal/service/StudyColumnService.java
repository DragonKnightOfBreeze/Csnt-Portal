package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 学习专栏的服务接口。
 */
public interface StudyColumnService {
	StudyColumn save(StudyColumn column);

	void deleteById(Integer id);

	StudyColumn update(StudyColumn column);


	StudyColumn findById(Integer id);


	Page<StudyColumn> findAll(Pageable pageable);

	Page<StudyColumn> findAllByTitle(String title, Pageable pageable);
}
