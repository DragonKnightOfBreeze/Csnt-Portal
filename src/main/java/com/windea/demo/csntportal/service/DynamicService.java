package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.domain.vo.DynamicSearchVo;
import com.windea.demo.csntportal.enums.DynamicCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * 实时动态的服务接口。
 */
public interface DynamicService {
	Dynamic saveBySponsorUsername(Dynamic dynamic, String username);

	void deleteById(Integer id);

	Dynamic findById(Integer id);

	Page<Dynamic> findAll(Pageable pageable);

	Page<Dynamic> findAllBySubject(String subject, Pageable pageable);

	Page<Dynamic> findAllByCategory(Set<DynamicCategory> categorySet, Pageable pageable);

	Page<Dynamic> findAllBySponsorUsername(String username, Pageable pageable);

	Page<Dynamic> findAllByConditions(DynamicSearchVo vo, Pageable pageable);
}
