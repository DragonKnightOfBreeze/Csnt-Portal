package com.windea.demo.csntportal.domain.entity;

import com.windea.demo.csntportal.GlobalConsts;
import com.windea.demo.csntportal.enums.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 用户的实体类。<br>
 * 实现UserDetails接口以进行安全验证。
 */
@Entity
public class User implements UserDetails {
	private static final long serialVersionUID = 1767704296003338587L;

	/** 主键。 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 用户名。 */
	@NotEmpty(message = "{user.username.notEmpty}")
	@Pattern(regexp = GlobalConsts.RE_USERNAME, message = "{user.username.pattern}")
	private String username;

	/** 密码。 */
	@NotEmpty(message = "{user.password.notEmpty}")
	@Pattern(regexp = GlobalConsts.RE_PASSWORD, message = "{user.password.pattern}")
	private String password;

	/** 电话号码。 */
	@NotEmpty(message = "{user.phoneNum.notEmpty}")
	@Size(min = 11, max = 11, message = "{user.phoneNum.size}")
	private String phoneNum;

	/** 邮箱地址。 */
	@NotEmpty(message = "{user.email.notEmpty}")
	@Email(message = "{user.email.email}")
	private String email;

	/** 昵称。 */
	@NotEmpty(message = "{user.nickname.notEmpty}")
	@Size(min = 1, max = 32, message = "{user.nickname.size}")
	private String nickname;

	/** 性别。 */
	//当字段的类型为枚举时需要加上这个注解，并以对应的字符串为准
	@Enumerated(EnumType.STRING)
	private Gender gender;

	/** 身份。 */
	@Enumerated(EnumType.STRING)
	private Role role;

	/** 专业。 */
	@Enumerated(EnumType.STRING)
	private Profession profession;

	/** 注册时间。 */
	//当字段表示数据的创建时间时需要加上这个注解，修改时间类同。
	@CreationTimestamp
	private LocalDateTime signUpTime;

	/** 关联的动态集合。 */
	//表示一对多关系
	//mapperBy指定要映射到的另一个实体类的属性名
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sponsorUser")
	private List<Dynamic> dynamicList = new ArrayList<>();


	public User() {}

	public User(String username, String password, String phoneNum, String email, String nickname, Gender gender,
		Role role, Profession profession) {
		this.username = username;
		this.password = password;
		this.phoneNum = phoneNum;
		this.email = email;
		this.nickname = nickname;
		this.gender = gender;
		this.role = role;
		this.profession = profession;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public LocalDateTime getSignUpTime() {
		return signUpTime;
	}

	public void setSignUpTime(LocalDateTime signUpTime) {
		this.signUpTime = signUpTime;
	}

	public List<Dynamic> getDynamicList() {
		return dynamicList;
	}

	public void setDynamicList(List<Dynamic> dynamicList) {
		this.dynamicList = dynamicList;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Set.of(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
