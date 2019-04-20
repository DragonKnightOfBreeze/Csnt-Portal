package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.DevelopmentColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 专业发展专栏的服务接口。
 */
public interface DevelopmentColumnService {
	DevelopmentColumn save(DevelopmentColumn introduce);

	void deleteById(Integer id);

	DevelopmentColumn update(DevelopmentColumn introduce);


	DevelopmentColumn findById(Integer id);


	Page<DevelopmentColumn> findAll(Pageable pageable);

	Page<DevelopmentColumn> findAllByTitleLike(String title, Pageable pageable);
}
