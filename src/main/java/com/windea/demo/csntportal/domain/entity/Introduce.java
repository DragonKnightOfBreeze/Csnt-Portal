package com.windea.demo.csntportal.domain.entity;

import com.windea.java.template.TBean;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 专业特色介绍的实体类。
 */
@Entity
public class Introduce extends TBean {
	private static final long serialVersionUID = -7416725898580708133L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 标题。 */
	@NotEmpty()
	@Size(min = 1, max = 32,)
	@Column(nullable = false, length = 32, columnDefinition = "varchar default '请输入标题'")
	private String title;

	/** 内容。 */
	@NotEmpty()
	@Column(nullable = false, columnDefinition = "text")
	private String content;

	/** 作者。 */
	@NotEmpty()
	@Size(min = 1, max = 32,)
	@Column(nullable = false, length = 32, columnDefinition = "varchar default '匿名'")
	private String author;

	/** 发表时间。 */
	@CreationTimestamp
	private LocalDateTime publishTime;

	/** 更新时间。 */
	@UpdateTimestamp
	private LocalDateTime updateTime;


	public Introduce() {}

	public Introduce(String title, String content, String author) {
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
