package com.windea.demo.csntportal.domain.request;

import com.windea.demo.csntportal.enums.DynamicCategory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 实时动态的查询表单的视图对象。
 */
public class DynamicSearchVo {
	/** 主题。 */
	private String subject;

	/** 动态分类集合。 */
	//当集合字段的元素的类型为枚举时需要加上这两个注解，并以对应的字符串为准
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<DynamicCategory> categorySet = new HashSet<>();

	/** 发起用户的用户名。 */
	private String sponsorUsername;


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Set<DynamicCategory> getCategorySet() {
		return categorySet;
	}

	public void setCategorySet(Set<DynamicCategory> categorySet) {
		this.categorySet = categorySet;
	}

	public String getSponsorUsername() {
		return sponsorUsername;
	}

	public void setSponsorUsername(String sponsorUsername) {
		this.sponsorUsername = sponsorUsername;
	}
}
