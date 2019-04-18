package com.windea.demo.csntportal.domain.entity;

import com.windea.demo.csntportal.GlobalConsts;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 管理员的实体类。
 */
//指定这个类是一个实体类，以映射到对应名称的数据库表
//实体类最好继承Serializable接口，并带有一个名为serialVersionUID的静态字段
//@Table注解可省略，默认表名同类名
//非静态非瞬态字段的@Column注解可省略，默认行名同字段名
@Entity
public class Admin implements Serializable {
	private static final long serialVersionUID = -5927059299025934714L;

	/** 主键。 */
	//指定这个字段是对应数据库表的主键
	@Id
	//指定生成策略
	//指定strategy为IDENTITY，以生成递增主键，默认为AUTO
	//指定generator为uuid，并注上@GenericGenerator(name="uuid",strategy = "uuid")，以生成uuid
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 用户名。 */
	@NotEmpty(message = "{user.username.notEmpty}")
	@Pattern(regexp = GlobalConsts.RE_USERNAME, message = "{user.username.pattern}")
	private String username;

	/** 密码。 */
	@NotEmpty(message = "{user.password.notEmpty}")
	@Pattern(regexp = GlobalConsts.RE_PASSWORD, message = "{user.password.pattern}")
	private String password;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Admin() {}

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
