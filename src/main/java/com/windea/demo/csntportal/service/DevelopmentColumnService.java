package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.DevelopmentColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 专业发展专栏的服务接口。
 */
public interface DevelopmentColumnService {
	DevelopmentColumn create(DevelopmentColumn column);

	void delete(Integer id);

	DevelopmentColumn update(DevelopmentColumn column);

	DevelopmentColumn get(Integer id);

	Page<DevelopmentColumn> list(Pageable pageable);

	Page<DevelopmentColumn> searchByTitle(String title, Pageable pageable);
}
