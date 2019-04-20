package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.Introduce;

import java.util.List;

/**
 * 专业特色介绍的服务接口。
 */
public interface IntroduceService {
	Introduce save(Introduce introduce);

	void deleteById(Integer id);

	Introduce update(Introduce introduce);


	Introduce findById(Integer id);


	List<Introduce> findAll(String title);
}
