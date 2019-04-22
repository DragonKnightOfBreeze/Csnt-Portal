# 功能需求

## 通用的功能

* 整合SpringBoot和Angular
* Restful风格的url
* 全局异常管理（使用Spring异常处理器）
* 全局缓存管理？（使用Redis）

## 具体的功能

* 注册登录系统
    * 注册
    * 登录
    * 注销
    * 找回密码？
* 用户主页
    * ［需要登录］修改账户信息
    * ［需要登录］查看用户动态？

* 专业特色介绍（文章，允许切换分页）
    * 单一数据展示
    * 所有数据展示
    * ［管理员］数据增加、删除、修改
* 专业发展专栏（文章列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * ［管理员］数据增加、删除、修改
* 教学改革专栏（文章列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * ［管理员］数据增加、删除、修改
* 学习专栏（文章列表，存在详情页面）
    * ［需要登录］单一数据展示
    * ［需要登录］所有数据展示
    * ［需要登录］分页功能
    * ［需要登录］简单的查询功能
    * ［管理员］数据增加、删除、修改
* 实时动态（媒体列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * 复杂的查询功能
	* ［需要登录］用户动态增加
	* ［需要登录］用户动态删除
	* ［管理员］数据删除
* 教师队伍（媒体列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * 复杂的查询功能
    * ［管理员］数据增加、删除、修改

# 功能实现

- [X] 配置文件
    * @PropertySource,@Value只能与properties文件配套使用，而非yaml
    * @ConfigurationProperties可以实现自动注入，但是需要使用@EnableConfigurationProperties包含该组件类/Bean类
- [X] 实体类 domain
    * **［注意事项］**
        * 添加必要的Jpa注解
            * 对于@Id，添加@GeneratedValue(strategy = GenerationType.AUTO)
            * 对于@OneToOne、@OneToMany，指定mappedBy（以及fetch和cascade？）属性（除非单向）
            * CascadeType：给当前设置的实体操作另一个实体的权限
            * 一般情况下，为所有者的对应字段指定CascadeType.ALL，为附属者的对应字段指定CascadeType.MERGE
            * 一般情况下，如果多个实体类可以操作同一个实体类，即@ManyToAny，则为前者添加CascadeType.REFRESH
            * ~~如果所有者以附属者作为主要内容，应该为附属者的对应字段指定CascadeType.REMOVE~~
            * 如果id是随机字符串，不应使用CascadeType.PERSIST，而应使用CascadeType.MERGE
            * 对于基础类型字段，可选添加@Basic，对于基础类型集合字段，需要添加@ElementCollection
            * 对于枚举字段，需要添加@Enumerated，并按情况再添加@ElementCollection，按情况替换成@MapKeyEnumerated
            * 存到数据库中的仍然是枚举值的默认名字
            * 对于属于附属者向所有者的引用的字段，一般是注有@ManyToOne的字段，添加@JsonIgnore以避免无限循环
        * 指定默认值
            * 第一种方案：使用`@ColumnDefault("'abc'")`
            * 第二种方案：使用`@Column(columnDefinition = "text default 'abc'")`，其中类型是必须的
        * 添加必要的Validation注解
            * 除了实体类之外，也可以为组件类添加@ConfigurationProperties，@Validated以启用参数验证
        * 不使用Lombok
	- [X] 展示数据 
		* 专业特色介绍：标题，作者，内容，发表时间，更新时间
		* 专业发展专栏：标题，作者，内容，发表时间，更新时间
		    * 查询的视图对象：标题，作者
		* 教学改革专栏：标题，作者，内容，发表时间，更新时间
		    * 查询的视图对象：标题，作者
		* 学习专栏：标题，作者，专业，难度级别，内容，发表时间，更新时间
			* 查询的视图对象：标题，作者，专业，难度级别
		* 实时动态：主题，动态分类，内容，发起用户（一对一），发起时间
			* 查询的视图对象：主题，动态分类，发起者
		* 教师队伍：名称，专业级别，介绍，教师列表（一对多），创建时间，人数（只读）
			* 教师信息：姓名，性别，专业，介绍
			* 查询的视图对象：名称，等级，人数
	- [X] 用户
		* 管理员：用户名，密码
		* 用户：用户名，昵称，密码，性别，手机号，邮箱，身份，专业，注册时间
			* 用户注册表单：用户名，昵称，密码，性别，手机号，QQ号，邮箱，身份
			* 用户登录表单：用户名/手机号/邮箱，密码
	- [ ] 相关的视图对象
- [X] 枚举 enums
    * **［注意事项］**
        * 枚举值要全大写
        * 为枚举定义常量只读属性，然后将枚举常量定义使用`NAME("名字")`，即可非常方便地得到枚举值的拓展信息。
- [X] 异常类 exception
    * **［注意事项］**
        * 存在基础异常，同时所有自定义异常都是运行时异常
- [X] Spring Security security
    * **［注意事项］**
        * 路径权限规则需要自行在配置类中配置，也可以使用@PreAuthorize指定方法级别的权限规则
        * 登录用户、记住密码、登出用户等功能，直接使用默认配置即可
- [ ] 持久层 repository
    * **［注意事项］**
        * 没什么好说的写法，接口继承自`JpaRepository<T, ID>`，添加必要的命名有规则的方法
        * 方法命名规则：越详细越好，关注忽略大小写，关注必要的分页，不关注排序
        * 保留那些目前可能用不上，以后用来拓展功能的方法
- [ ] 服务层 service
    * **［注意事项］**
        * 分为接口和实现类，实现类在`service.impl`中，类名以`Impl`结尾
        * 不处理异常，断言结果以抛出运行时异常
        * 方法命名规则：除了排序等部分以外，与持久层保持一致
        * 为所有删改操作开启事务，即添加@Transactional
        * 不要编写专门的根据用户名和密码查找用户的方法，方法经过加密，由Spring Security代理获得即可
- [ ] 控制层 api
    * **［注意事项］**
        * 使用Rest风格的url，同时总是使用@RestController
        * 在类上加上@CrossOrigin以开启跨域
        * 处理验证错误，执行认真认证逻辑
        * 捕获异常，根据异常类型设置不同的状态码，基于ResponseEntity，将数据放到响应体中
        * 方法命名规则：贴近实际更的命名
        * 如：`add,register,deleteById,cancelById,update,get,list,searchByTitle,advanceSearch`
        * 返回值不再是代表url的字符串，而是数据或以数据类型为泛型的ResponseEntity
        * 使用@Valid指定需要验证的输入数据，使用紧随的bindingResult得到可能的验证错误
        * 使用MultipleFile file得到传入的文件数据
        * 整合Spring Security和Jwt
        * 为方法加上`@PreAuthorize("hasRole('ADMIN')")`以开启权限限制，注意数据库中存储的应该是`ROLE_ADMIN`

# API参考

* `/index`,`/login`,`/logout`,`/register`,`/reset-password`
* `/account`,/`account/{username}`,`/account/{username}/dynamic`,`/account/{username}/settings`
* `/dynamic`,`/dynamic/{id}`,`/dynamic/list`
* `/admin`,`/admin/user/{id}`,`/admin/user/list`   
