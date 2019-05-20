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
	Dynamic createBySponsorUsername(Dynamic dynamic, String username);

	void delete(Integer id);

	Dynamic get(Integer id);

	Page<Dynamic> list(Pageable pageable);

	Page<Dynamic> searchBySubject(String subject, Pageable pageable);

	Page<Dynamic> searchBySponsorUsername(String username, Pageable pageable);

	Page<Dynamic> searchByCategory(Set<DynamicCategory> categorySet, Pageable pageable);

	Page<Dynamic> advanceSearch(DynamicSearchVo vo, Pageable pageable);
}
