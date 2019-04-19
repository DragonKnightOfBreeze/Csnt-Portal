package com.windea.demo.csntportal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.windea.demo.csntportal.enums.ProfessionLevel;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 教师队伍的实体类。
 */
@Entity
public class TeacherTeam implements Serializable {
	private static final long serialVersionUID = -4818632335709225692L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 名字。 */
	@NotEmpty(message = "{teacherTeam.name.notEmpty}")
	@Size(min = 1, max = 32, message = "{teacherTeam.name.size}")
	private String name;

	/** 专业级别。 */
	@Enumerated(EnumType.STRING)
	private ProfessionLevel professionLevel;

	/** 介绍。 */
	@NotEmpty(message = "{teacherTeam.introduce.notEmpty}")
	@Size(min = 1, max = 256, message = "{teacherTeam.introduce.notEmpty}")
	private String introduce;

	/** 创建时间。 */
	@CreationTimestamp
	private LocalDateTime createTime;

	/** 教师详情的集合。 */
	//NOTE 这里的级联级别不能是ALL，当删除教师队伍时，教师详情不删除
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "teacherTeam")
	@JsonIgnore
	private List<TeacherDetail> teacherDetailList = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProfessionLevel getProfessionLevel() {
		return professionLevel;
	}

	public void setProfessionLevel(ProfessionLevel professionLevel) {
		this.professionLevel = professionLevel;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public List<TeacherDetail> getTeacherDetailList() {
		return teacherDetailList;
	}

	public void setTeacherDetailList(List<TeacherDetail> teacherDetailList) {
		this.teacherDetailList = teacherDetailList;
	}

	public Integer getTeacherCount() {
		return teacherDetailList.size();
	}


	public TeacherTeam() {
	}

	public TeacherTeam(String name, ProfessionLevel professionLevel, String introduce) {
		this.name = name;
		this.professionLevel = professionLevel;
		this.introduce = introduce;
	}
}
