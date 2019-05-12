package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.ReformColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 教学改革专栏的服务接口。
 */
public interface ReformColumnService {
	ReformColumn save(ReformColumn column);

	void deleteById(Integer id);

	ReformColumn update(ReformColumn column);

	ReformColumn findById(Integer id);

	Page<ReformColumn> findAll(Pageable pageable);

	Page<ReformColumn> findAllByTitle(String title, Pageable pageable);
}
