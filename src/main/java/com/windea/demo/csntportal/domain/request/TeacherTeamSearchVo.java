package com.windea.demo.csntportal.domain.request;

import com.windea.demo.csntportal.enums.ProfessionLevel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 教师队伍的查询表单的视图对象。
 */
public class TeacherTeamSearchVo {

	/** 名字。 */
	private String name;

	/** 专业级别。 */
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<ProfessionLevel> levelSet = new HashSet<>();

	/** 最少人数。 */
	private Integer begin;

	/** 最大人数。 */
	private Integer end;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProfessionLevel> getLevelSet() {
		return levelSet;
	}

	public void setLevelSet(Set<ProfessionLevel> levelSet) {
		this.levelSet = levelSet;
	}

	public Integer getBegin() {
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
}
