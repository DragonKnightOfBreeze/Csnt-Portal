# 功能需求

## 通用的功能

* 整合SpringBoot和Angular
* Restful风格的url
* 分页功能
* 参数验证（前端使用h5参数验证，后台使用java validation验证）
* 安全验证（使用jwt和spring security）
* 全局异常管理（后台配置Spring异常处理器，返回http状态码）
* 缓存管理（后台使用Redis）

## 具体的功能

* 注册登录系统
    * 注册
    * 登录
    * 注销
    * 找回密码？
* 用户主页
    * ［需要登录］修改账户信息
    * ［需要登录］查看用户动态
* 专业特色介绍（文章，允许切换分页）
    * 单一数据展示
    * 所有数据展示
    * ［管理员］数据增加、删除、修改
    * 启用Redis缓存 
* 专业发展专栏（文章列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * ［管理员］数据增加、删除、修改
    * 启用Redis缓存 
* 教学改革专栏（文章列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * ［管理员］数据增加、删除、修改
    * 启用Redis缓存
* 学习专栏（文章列表，存在详情页面）
    * ［需要登录］单一数据展示
    * ［需要登录］所有数据展示
    * ［需要登录］分页功能
    * ［需要登录］简单的查询功能
    * ［管理员］数据增加、删除、修改
    * 启用Redis缓存 
* 实时动态（媒体列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * 复杂的查询功能
	* ［需要登录］用户动态增加
	* ［需要登录］用户动态删除
	* ［管理员］数据删除
	* 启用Redis缓存
* 教师队伍（媒体列表，存在详情页面）
    * 单一数据展示
    * 所有数据展示
    * 分页功能
    * 简单的查询功能
    * 复杂的查询功能
    * ［管理员］数据增加、删除、修改
    * 启用Redis缓存
* 用户
    * ［管理员］单一数据展示
    * ［管理员］所有数据展示
    * ［管理员］分页功能
    * ［管理员］简单的查询功能
    * 启用Redis缓存

# 功能实现

## 优化和重构

- [ ] 传递后台参数校验数据到前端（bindingResult）

## 前端功能实现

- [X] 公共配置
    - [X] 路由
    - [X] 环境配置
- [X] 分页组件 page
    - [X] html
    - [X] scss
    - [X] ts
    - [X] 更加详细的表单验证错误提示警告框
    - [X] 我的账户页面
    - [X] 其他重复的列表和详情分页
    - [ ] ~~管理员相关代码~~
- [X] 页面组成组件 part
    - [X] html
    - [X] scss
    - [X] ts
- [X] 实体类 domain
    - [X] 后台数据实体类 entity
    - [X] 后台类型接口 interface
    - [X] 视图对象实体类 vo
- [X] 枚举 enums
- [X] 服务 service
    - [X] 后台api服务 api
    - [X] 权限守卫 guard
    - [X] http拦截器 interceptor
- [ ] ~~自定义指令 directive~~~~
- [X] 自定义管道 pipe
    - [X] 用于遍历枚举值的管道
    - [X] 枚举值转化为对应文本的管道

## 后台功能实现

- [X] 配置文件
    * **［注意事项］**
        * @PropertySource,@Value只能与properties文件配套使用，而非yaml
        * @ConfigurationProperties可以实现自动注入，但是需要使用@EnableConfigurationProperties包含该组件类/Bean类
- [X] 实体类 domain
    * **［添加必要的Jpa注解］**
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
    * **［添加必要的Validation注解］**
        * 除了实体类之外，也可以为组件类添加@ConfigurationProperties，@Validated以启用参数验证
    * **［指定默认值］**
        * 第一种方案：使用`@ColumnDefault("'abc'")`
        * 第二种方案：使用`@Column(columnDefinition = "text default 'abc'")`，其中类型是必须的
    * 不使用Lombok
- [X] 枚举 enums
    * **［注意事项］**
        * 枚举值要全大写
        * 为枚举定义常量只读属性以及相应的构造方法，然后将枚举常量为`NAME("名字")`，即可非常方便地得到枚举值的拓展信息。
- [X] 异常类 exception
    * **［注意事项］**
        * 存在基础异常，同时所有自定义异常都是运行时异常
- [X] 安全 security
    * **［注意事项］**
        * 路径权限规则需要自行在配置类中配置，也可以使用@PreAuthorize指定方法级别的权限规则
        * 登录用户、记住密码、登出用户等功能，直接使用默认配置即可
- [X] 持久层 repository
    * **［注意事项］**
        * 没什么好说的写法，接口继承自`JpaRepository<T, ID>`，添加必要的命名有规则的方法
        * 方法命名规则：越详细越好，关注忽略大小写，关注必要的分页，不关注排序
        * 可以直接调用实体类的实体类属性的属性
        * 保留那些目前可能用不上，以后用来拓展功能的方法
        * `@RepositoryRestResource`：自动根据参数和持久层方法名构建Rest，允许直接从控制台根据特定url进行测试，存在默认配置
    * **［自定义查询规则］**
        * `@Query`：自定义查询规则，返回匹配对象或集合对象或结果接口，可能需要添加`@Modify`
        * `:username`表示与注有`@Param("username")`的方法参数相匹配的参数，名字相同时可省略
        * sql语句中的是实体类以及实体类的属性，可以直接调用实体类的实体类属性的属性
    * **［启用缓存功能］**
        * 在`application.yml`中添加相应的配置项
        * 为配置类添加`@EnableCaching`
        * 为需要启用缓存的类添加`@CacheConfig`，以共享缓存配置。一般会配置`cacheNames`
        * `@Caching`：为一个方法声明多个`@Cacheable`，`@CacheEvict`，`@CachePut`
        * `@Cacheable`：为入口操作启用缓存（查找）
        * `@CacheEvict`：为出口操作启用缓存（增加，删除），一般需要设置`allEntries=true`
        * `@CachePut`：为更新操作启用缓存（修改）
        * 使用示例：
        * `@Cacheable(cacheNames="books", key="T(someType).hash(#isbn)", keyGenerator="myKeyGen", sync=true)`
        * `@Cacheable(cacheNames="book", condition="#name.length() < 32", unless="#result?.hardback")`
        * **注意：** 不要在持久层接口上使用cache注解，推荐在服务层实现类上使用。
- [X] 服务层 service
    * **［注意事项］**
        * 分为接口和实现类，实现类在`service.impl`中，类名以`Impl`结尾
        * 不处理异常，断言结果以抛出运行时异常
        * 方法命名规则：除了排序等部分以外，与持久层保持一致
        * 为所有增删改操作开启事务，即添加@Transactional
        * 不要编写专门的根据用户名和密码查找用户的方法，密码经过加密，由Spring Security代理获得即可
- [X] 控制层 api
    * **［注意事项］**
        * 使用Rest风格的url，同时总是使用@RestController
        * 在类上加上@CrossOrigin以开启跨域
        * 处理验证错误，执行安全认证逻辑
        * 捕获异常，根据异常类型设置不同的状态码，基于ResponseEntity，将数据放到响应体中
        * 方法命名规则：贴近更实际的命名
        * 如：`add,register,deleteById,cancelById,update,get,list,searchByTitle,advanceSearch`
        * 返回值不再是代表url的字符串，而是要返回到前端的后台数据类型
        * 使用@Valid指定需要验证的输入数据，使用紧随的bindingResult得到可能的验证错误
        * 使用MultipleFile file得到传入的文件数据
    * **［安全验证］**
        * 为方法加上`@PreAuthorize("hasRole('ADMIN')")`以开启权限限制。注意对应的枚举值是`ADMIN`，需要添加相应的注解以启用。
        * ~~数据库中存储的应该是`ROLE_ADMIN`~~ 数据库中存的是枚举好不好！
        * 为映射方法注入用户名：`principal.name`/`authentication.name`（实际上是用于验证的字段）
		* 为映射方法注入用户详情实体类：`authentication.principal`（需要强转）
		* 为映射方法注入用户密码：`authentication.credentials`（但也可以是其他用于确保身份正确的字段）
		* 为映射方法注入用户权限：`authentication.authorities`
    * **［异常处理］**
        * 返回值是ResponseEntity的情况下，可以捕获异常，分别返回，并带有不同的状态码和实体数据。
        * 也可以为异常类添加`@HttpStatus`注解以进行简单的异常处理
        * 也可以定义一个控制器，实现ErrorController，然后在里面定义数个注有`@ExceptionHandler(YourException.class)`的方法
        * 将这些方法的返回值设为`ResponseEntity<T>`，即可当其他映射方法发生异常时，返回对应异常的处理方法返回的`ResponseEntity<T>`。

# 测试

## 整合测试

- [X] 前端发送GET请求得到后台数据

## 前端测试

- [X] 成功启动前端项目
- [X] 应用全局样式（bootstrap和font-awesome）
- [X] 全局错误处理（重定向到不同的错误页面）
- [ ] 可以接收的分页功能实现并通过测试

## 后台测试

- [X] 成功启动后台项目
- [X] 控制层的一般数据库操作功能测试
- [X] 全局错误处理（定义全局异常处理器）
- [X] 参数验证功能（验证出错时返回bindingResult）
- [X] 参数验证消息的正确显示（IDE已能够正确地解析属性）
- [X] 权限验证功能（返回了正确的状态码）
