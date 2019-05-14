package com.windea.demo.csntportal.domain.vo;

import com.windea.demo.csntportal.enums.ProfessionLevel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 教师队伍高级查询表单的视图对象。
 */
public class TeacherTeamSearchVo {

	/** 名字。 */
	private String name;

	/** 专业级别。 */
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<ProfessionLevel> levelSet = new HashSet<>();

	/** 最少人数。 */
	private Integer min;

	/** 最大人数。 */
	private Integer max;


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

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
