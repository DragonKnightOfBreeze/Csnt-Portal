package com.windea.demo.csntportal.domain.entity;

import com.windea.demo.csntportal.enums.DifficultyLevel;
import com.windea.demo.csntportal.enums.Profession;
import com.windea.java.template.TBean;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 学习专栏的实体类。
 */
@Entity
public class StudyColumn extends TBean {
	private static final long serialVersionUID = 2648588976833523229L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 标题。 */
	@NotEmpty()
	@Size(min = 1, max = 32)
	@Column(nullable = false, length = 32, columnDefinition = "varchar default '请输入标题'")
	private String title;

	/** 内容。 */
	@NotEmpty()
	@Column(nullable = false, columnDefinition = "text")
	private String content;

	/** 作者。 */
	@NotEmpty()
	@Size(min = 1, max = 32)
	@Column(nullable = false, length = 32, columnDefinition = "varchar default '匿名'")
	private String author;

	/** 专业。 */
	@Enumerated(EnumType.STRING)
	private Profession profession;

	/** 难度级别。 */
	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficultyLevel;

	/** 发表时间。 */
	@CreationTimestamp
	private LocalDateTime publishTime;

	/** 更新时间。 */
	@UpdateTimestamp
	private LocalDateTime updateTime;


	public StudyColumn() {}

	public StudyColumn(String title, String content, String author, Profession profession,
		DifficultyLevel difficultyLevel) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.profession = profession;
		this.difficultyLevel = difficultyLevel;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(LocalDateTime publishTime) {
		this.publishTime = publishTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
}
