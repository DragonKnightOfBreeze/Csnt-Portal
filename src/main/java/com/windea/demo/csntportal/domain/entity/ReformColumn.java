package com.windea.demo.csntportal.domain.entity;

import com.windea.commons.base.template.TBean;
import com.windea.demo.csntportal.GlobalConsts;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 教学改革专栏的实体类。
 */
@Entity
public class ReformColumn extends TBean {
	private static final long serialVersionUID = 2961174747531319125L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 标题。 */
	@NotEmpty(message = "{column.title.notEmpty}")
	@Size(min = 1, max = 32, message = "{column.title.size}")
	@ColumnDefault(GlobalConsts.PH_C_TITLE)
	private String title;

	/** 内容。 */
	@NotEmpty(message = "{column.content.notEmpty}")
	@ColumnDefault(GlobalConsts.PH_C_CONTENT)
	private String content;

	/** 作者。 */
	@NotEmpty(message = "{column.author.notEmpty}")
	@Size(min = 1, max = 32, message = "{column.author.size}")
	@ColumnDefault(GlobalConsts.PH_C_AUTHOR)
	private String author;

	/** 发表时间。 */
	@CreationTimestamp
	private LocalDateTime publishTime;

	/** 更新时间。 */
	@UpdateTimestamp
	private LocalDateTime updateTime;


	public ReformColumn() {}

	public ReformColumn(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
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
