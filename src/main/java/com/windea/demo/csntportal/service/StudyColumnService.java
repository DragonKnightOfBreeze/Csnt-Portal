package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.StudyColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 学习专栏的服务接口。
 */
public interface StudyColumnService {
	StudyColumn save(StudyColumn introduce);

	void deleteById(Integer id);

	StudyColumn update(StudyColumn introduce);


	StudyColumn findById(Integer id);


	Page<StudyColumn> findAll(Pageable pageable);

	Page<StudyColumn> findAllByTitleLike(String title, Pageable pageable);
}
