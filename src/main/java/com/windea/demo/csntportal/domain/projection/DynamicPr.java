package com.windea.demo.csntportal.domain.projection;

import com.windea.demo.csntportal.domain.entity.User;

/**
 * 实时动态的查询结果投影接口。
 */
public interface DynamicPr {
	User getSponsorUser();
}
