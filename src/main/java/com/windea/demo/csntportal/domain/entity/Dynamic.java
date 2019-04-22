package com.windea.demo.csntportal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.windea.commons.base.template.TBean;
import com.windea.demo.csntportal.enums.DynamicCategory;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 实时动态的实体类。
 */
@Entity
public class Dynamic extends TBean {
	private static final long serialVersionUID = 4662673897790133674L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 主题。 */
	@NotEmpty(message = "{dynamic.subject.notEmpty}")
	@Size(min = 1, max = 32, message = "{dynamic.subject.size}")
	@Column(nullable = false, length = 32, columnDefinition = "varchar default '请输入主体'")
	private String subject;

	/** 动态分类。 */
	@Enumerated(EnumType.STRING)
	private DynamicCategory category;

	/** 内容。 */
	@NotEmpty(message = "{dynamic.content.notEmpty}")
	@Column(nullable = false, columnDefinition = "text default '请输入内容'")
	private String content;

	/** 发起的用户。 */
	//实体类或者集合属性可能需要加上这个注解
	@JsonIgnore
	//表示多对一关系
	//fetch指定是否延迟加载，cascade指定级联级别，处理持久态与瞬时态的切换问题
	//@JoinColumn注解可省略
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private User sponsorUser;

	/** 发起的时间。 */
	@CreationTimestamp
	private LocalDateTime sponsorTime;


	public Dynamic() {}

	public Dynamic(String subject, DynamicCategory category, String content, User sponsorUser) {
		this.subject = subject;
		this.category = category;
		this.content = content;
		this.sponsorUser = sponsorUser;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public DynamicCategory getCategory() {
		return category;
	}

	public void setCategory(DynamicCategory category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSponsorUser() {
		return sponsorUser;
	}

	public void setSponsorUser(User sponsorUser) {
		this.sponsorUser = sponsorUser;
	}

	public LocalDateTime getSponsorTime() {
		return sponsorTime;
	}

	public void setSponsorTime(LocalDateTime sponsorTime) {
		this.sponsorTime = sponsorTime;
	}
}
