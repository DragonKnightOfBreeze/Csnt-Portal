package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.ReformColumn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 教学改革专栏的服务接口。
 */
public interface ReformColumnService {
	ReformColumn create(ReformColumn column);

	void delete(Integer id);

	ReformColumn update(ReformColumn column);

	ReformColumn get(Integer id);

	Page<ReformColumn> list(Pageable pageable);

	Page<ReformColumn> searchByTitle(String title, Pageable pageable);
}
