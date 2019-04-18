package com.windea.demo.csntportal.domain.entity;

import com.windea.demo.csntportal.GlobalConsts;
import com.windea.demo.csntportal.enums.DifficultyLevel;
import com.windea.demo.csntportal.enums.Profession;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 学习专栏的实体类。
 */
@Entity
public class StudyColumn implements Serializable {
	private static final long serialVersionUID = 2648588976833523229L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 标题。 */
	@ColumnDefault(GlobalConsts.PH_C_TITLE)
	@NotEmpty(message = "{column.title.notEmpty}")
	@Size(min = 1, max = 32, message = "{column.title.size}")
	private String title;

	/** 内容。 */
	@ColumnDefault(GlobalConsts.PH_C_CONTENT)
	@NotEmpty(message = "{column.content.notEmpty}")
	private String content;

	/** 作者。 */
	@ColumnDefault(GlobalConsts.PH_C_AUTHOR)
	@NotEmpty(message = "{column.author.notEmpty}")
	@Size(min = 1, max = 32, message = "{column.author.size}")
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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


	public StudyColumn() {
	}

	public StudyColumn(String title, String content, String author, Profession profession,
			DifficultyLevel difficultyLevel) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.profession = profession;
		this.difficultyLevel = difficultyLevel;
	}
}
