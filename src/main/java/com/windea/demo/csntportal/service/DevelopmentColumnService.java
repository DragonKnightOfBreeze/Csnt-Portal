package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.DevelopmentColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 专业发展专栏的服务接口。
 */
public interface DevelopmentColumnService {
	DevelopmentColumn save(DevelopmentColumn column);

	void deleteById(Integer id);

	DevelopmentColumn update(DevelopmentColumn column);


	DevelopmentColumn findById(Integer id);


	Page<DevelopmentColumn> findAll(Pageable pageable);

	Page<DevelopmentColumn> findAllByTitle(String title, Pageable pageable);
}
