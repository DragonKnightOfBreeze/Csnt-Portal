package com.windea.demo.csntportal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.windea.commons.base.template.TBean;
import com.windea.demo.csntportal.enums.ProfessionLevel;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 教师队伍的实体类。
 */
@Entity
public class TeacherTeam extends TBean {
	private static final long serialVersionUID = -4818632335709225692L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 名字。 */
	@NotEmpty(message = "validation.teacherTeam.name.notEmpty")
	@Size(min = 1, max = 32, message = "validation.teacherTeam.name.size")
	@Column(nullable = false, length = 32)
	private String name;

	/** 专业级别。 */
	@Enumerated(EnumType.STRING)
	private ProfessionLevel professionLevel;

	/** 介绍。 */
	@NotEmpty(message = "validation.teacherTeam.introduce.notEmpty")
	@Size(min = 1, max = 256, message = "validation.teacherTeam.introduce.notEmpty")
	@Column(nullable = false, columnDefinition = "text")
	private String introduce;

	/** 创建时间。 */
	@CreationTimestamp
	private LocalDateTime createTime;

	/** 教师信息的集合。 */
	@JsonIgnore
	//NOTE fetchType默认LAZY，谁显示声明谁SB
	//NOTE 这里的级联级别不能是ALL，当删除教师队伍时，教师信息不删除
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "teacherTeam")
	private List<TeacherInfo> teacherInfoList = new ArrayList<>();

	@Column(nullable = false, columnDefinition = "int default 0")
	private Integer teacherCount;


	public TeacherTeam() {}

	public TeacherTeam(String name, ProfessionLevel professionLevel, String introduce) {
		this.name = name;
		this.professionLevel = professionLevel;
		this.introduce = introduce;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<TeacherInfo> getTeacherInfoList() {
		return teacherInfoList;
	}

	public void setTeacherInfoList(List<TeacherInfo> teacherInfoList) {
		this.teacherInfoList = teacherInfoList;
	}

	public Integer getTeacherCount() {
		teacherCount = teacherInfoList.size();
		return teacherCount;
	}

	public void setTeacherCount(Integer teacherCount) {
		this.teacherCount = teacherInfoList.size();
	}
}
