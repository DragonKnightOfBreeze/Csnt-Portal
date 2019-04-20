package com.windea.demo.csntportal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.windea.demo.csntportal.enums.Gender;
import com.windea.demo.csntportal.enums.Profession;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 教师信息的实体类。
 */
@Entity
public class TeacherInfo implements Serializable {
	private static final long serialVersionUID = 7294090774828562605L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 名字。 */
	@NotEmpty(message = "{teacherDetail.name.notEmpty}")
	@Size(min = 1, max = 32, message = "{teacherDetail.name.size}")
	private String name;

	/** 性别。 */
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/** 专业。 */
	@Enumerated(EnumType.STRING)
	private Profession profession;

	/** 介绍。 */
	@NotEmpty(message = "{teacherDetail.introduce.notEmpty}")
	@Size(min = 1, max = 256, message = "{teacherDetail.introduce.notEmpty}")
	private String introduce;

	/** 教师队伍。 */
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnore
	private TeacherTeam teacherTeam;


	public TeacherInfo() {}

	public TeacherInfo(String name, Gender gender, Profession profession, String introduce,
		TeacherTeam teacherTeam) {
		this.name = name;
		this.gender = gender;
		this.profession = profession;
		this.introduce = introduce;
		this.teacherTeam = teacherTeam;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public TeacherTeam getTeacherTeam() {
		return teacherTeam;
	}

	public void setTeacherTeam(TeacherTeam teacherTeam) {
		this.teacherTeam = teacherTeam;
	}
}
