# 概述

学期项目：计算机科学与技术门户网站。

介绍专业特色、实时动态、教师队伍、专业发展、教学改革和学习专栏等。

# 参考链接

* Angular教程
	* [Angular - 什么是 Angular？](https://www.angular.cn/docs)
* SpringBoot教程
	* [Spring Boot 中文导航](http://springboot.fun/)
* Demo参考
	* [Angular-SpringBoot-REST-JWT](https://github.com/mrin9/Angular-SpringBoot-REST-JWT)
	* [SpringBootAngular7Integration](https://github.com/SKrudra/SpringBootAngular7Integration)
	* [SpringBoot-Angular7-ShoppingCart](https://github.com/zhulinn/SpringBoot-Angular7-ShoppingCart)

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
* 介绍专业特色（文章）
    * 数据展示
* 实时动态（媒体列表，存在详情页面）
    * 数据展示
    * 分页功能
    * 简单的查询功能
        * 查询表单数据传递
        * 前端表单验证
        * 后台表单验证
	* 添加动态
* 教师队伍（媒体列表，存在详情页面）
    * 数据展示
    * 分页功能
    * 简单的查询功能
        * 查询表单数据传递
        * 前端表单验证
        * 后台表单验证
    * 复杂的查询功能
        * 查询表单数据传递
        * 前端表单验证
        * 后台表单验证
* 专业发展（文章列表，存在详情页面）
    * 数据展示
    * 分页功能
* 教学改革（文章列表，存在详情页面）
    * 数据展示
    * 分页功能
* 学习专栏（文章列表，存在详情页面）
    * 数据展示
    * 分页功能
    * 简单的查询功能
        * 查询表单数据传递
        * 前端表单验证
        * 后台表单验证
    * 权限限制功能（需要登录）

# 功能实现

- [ ] 实体类 domain
    * **［注意事项］**
        * 添加必要的Jpa注解
            * 对于@Id，添加@GeneratedValue(strategy = GenerationType.AUTO)
            * 对于@OneToOne、@OneToMany，指定mappedBy（以及fetch和cascade？）属性，并添加@JsonIgnore
        * 添加必要的Validation注解
            - [ ] 暂不写上验证消息，以后放在`messages.yml`中去
        * 不使用Lombok
            - [ ] 使用IDE生成get、set方法、无参构造、全参构造，暂不重写`equals()`等方法
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
			* 教师详情：姓名，性别，专业，介绍
			* 查询的视图对象：名称，等级，人数
	- [X] 用户
		* 管理员：用户名，密码
		* 用户：用户名，昵称，密码，性别，手机号，邮箱，身份，专业，注册时间
			* 用户注册表单：用户名，昵称，密码，性别，手机号，QQ号，邮箱，身份
			* 用户登录表单：用户名/手机号/邮箱，密码
	- [ ] 相关的视图对象
- [ ] 枚举 enums
    * **［注意事项］**
        * 枚举值要全大写，同时大部分情况下需要重载`toString()`方法
- [ ] 异常类 exception
    * **［注意事项］**
        * 存在基础异常，同时所有自定义异常都是运行时异常
- [ ] 持久层 repository
    * **［注意事项］**
        * 没什么好说的写法，接口继承自`JpaRepository<T, ID>`，添加必要的命名有规则的方法
- [ ] 服务层 service
    * **［注意事项］**
        * 分为接口和实现类，实现类在`service.impl`中，类名以`Impl`结尾
        * 不处理异常，使用@NonNull等注解判断空值
- [ ] 控制层 api
    * **［注意事项］**
        * 使用Rest风格的url，同时总是使用@RestController
        * 在类上加上@CrossOrigin，以开启跨域
        * 返回值不再是代表url的字符串，而是数据或以数据类型为泛型的ResponseEntity
        * 使用@Valid指定需要验证的输入数据，使用紧随的bindingResult得到可能的验证错误
        * 使用MultipleFile file得到传入的文件数据
        - [ ] 整合Spring Security、Jwt

# 环境要求

* 通用
	* Idea（最新版本，淘宝教育版帐号并不贵，同时安装好相关插件）
	* **［特别注意］** JDK（12，同时配置好相关环境变量）
	* Maven（最新版本，同时配置好相关环境变量）
	* MySQL（最新版本，同时配置好相关环境变量）
	* Git（最新版本，同时配置好相关环境变量）
	* NPM（最新版本，同时配置好相关环境变量）
* Npm包
	* Angular（最新版本，通过npm安装）
	* TypeScript（最新版本，通过npm安装）
* 其他
	* Github帐号（同时配置好ssh私钥）

# 技术要求

* 通用
	* Markdown
	* Xml
	* Json
	* Yaml
	* Maven
	* Git
	* Docker？
	* Spring Security？
	* JWT Authentication？
* 前端
	* Html5
	* Css3
	* JavaScript(ES6)
	* Npm
	* Bootstrap4
	* TypeScript
	* Sass
	* Angular7
	* Angular Cli
	* H2？
* 后台
	* Java
	* Spring
	* Spring Boot
	* Spring Web Mvc
	* Spring Data Jba
	* ~~Lombok？~~
	* Swagger？
	* Redis
	* MySQL
	
# 目录概述

## 目录结构

```文档
[documents]     # 全局文档目录
  [common]          # 一般文档目录（文档，doc/ppt/uml）
  [notes]           # 笔记文档目录
[reference]     # 参考资料目录
  [data]            # 参考数据目录（参考，xsl/yaml，非测试用的数据）
  [assets]          # 参考资源目录（参考，包括图片、音频等）
  [documents]       # 文档模版目录（参考，doc/ppt，用于参照编写项目文档）
  [webpage]         # 网页模版目录（参考，用于参照编写前端页面）
[src]           # 全局源文件目录
  [main]            # 源文件目录
    [java]              # java源代码目录（后台，SpringBoot）
    [resources]         # 资源文件目录（后台，SpringBoot）
    [frontend]          # 前端文件目录（前端，Angular）
  [test]            # 测试文件目录
    [java]              # java测试代码目录（后台，SpringBoot）
.gitignore      # Git忽略项文件
pom.xml         # Maven依赖项文件
README.md       # 说明文档
...
```

## 需要注意的地方

* 忽略`src/frontend/node_modules`目录。
    * 在`src/frontend`目录下打开控制台，自行使用`cnpm install`命令下载Npm依赖包。
    * 可能会出一些问题……
* 不包含Jar依赖包。
    * 建立Maven本地仓库，在Idea中自行启用自动导入功能。
* 忽略`target`目录。
    * 在Idea中自行构建项目。

# 注意事项

## 特殊注释

* 标有`TODO`的注释：有待完成的代码。
* 标有`NOTE`的注释：需要特别配置或者特别注意的代码。

## 数据库连接出错

* 对于Idea的数据库连接，打开配置，在参数`URL`后面加上`?GMT%2B8`。
* 如果运行时出错，在`application.yml`中的`spring.datasource.url`后面也要加上，或者特别配置`serverTimezone: GMT-8`。

## 如何整合前后端

* 后端直接使用RestController，绑定Rest风格的url，收发json数据。
* 前端直接跳过http请求，指定泛型，以对应的url和http头为参数，收发json数据。
* 后端不需要指定视图解析器，
