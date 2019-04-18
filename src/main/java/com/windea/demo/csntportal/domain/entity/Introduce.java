package com.windea.demo.csntportal.domain.entity;

import com.windea.demo.csntportal.GlobalConsts;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 专业特色介绍的实体类。
 */
@Entity
public class Introduce implements Serializable {
	private static final long serialVersionUID = -7416725898580708133L;

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


	public Introduce() {
	}

	public Introduce(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
}
