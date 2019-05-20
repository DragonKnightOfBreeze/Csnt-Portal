package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.Introduce;

import java.util.List;

/**
 * 专业特色介绍的服务接口。
 */
public interface IntroduceService {
	Introduce create(Introduce introduce);

	void delete(Integer id);

	Introduce update(Introduce introduce);

	Introduce get(Integer id);

	List<Introduce> list();
}
