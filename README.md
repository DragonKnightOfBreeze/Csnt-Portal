# 概述

学期项目：计算机科学与技术门户网站。

介绍专业特色、实时动态、教师队伍、专业发展、教学改革和学习专栏等。

# 参考连接

* Angular教程
	* [Angular - 什么是 Angular？](https://www.angular.cn/docs)
* Ionic官方文档
    * [Ionic Framework - Ionic Documentation](https://ionicframework.com/docs/)
* SpringBoot教程
    * [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle)
	* [Spring Boot 中文导航](http://springboot.fun/)

# 功能需求

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

# 环境要求

* 通用
	* Idea（最新版本，淘宝教育版帐号并不贵，同时安装好相关插件）
	* JDK（11，同时配置好相关环境变量）
	* Maven（最新版本，同时配置好相关环境变量）
	* MySQL（最新版本，同时配置好相关环境变量）
	* Git（最新版本，同时配置好相关环境变量）
	* NPM（最新版本，同时配置好相关环境变量）
* Npm包
	* Angular（最新版本，通过npm安装）
	* Ionic & Cordova（最新版本，通过npm安装，**注意：cordova需要通过jdk1.8启动**）
	* TypeScript（最新版本，通过npm安装）
* 其他
	* Github帐号（同时配置好ssh私钥）

# 技术要求

* 通用
	* Markdown
	* Xml
	* Json
	* Yaml
	* Git
	* Docker？
* 前端
	* Html5
	* Css3
	* Javascript(ES6)
	* Npm
	* Bootstrap4
	* Font Awesome
	* Typescript
	* Sass
	* Angular7
	* Angular Cli
    * Ionic & Cordova
* 后台
	* Java
	* Spring Boot
	* Spring
	* Spring Web Mvc
	* Spring Data Jpa
	* Spring Security
	* JWT Authentication
	* ~~Lombok？~~
	* Swagger？
	* Redis
	* MySQL
	* Maven

# 目录说明

## 目录结构

```
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
    * 在`src/frontend`目录下打开控制台，自行使用`npm install`命令下载ppm依赖包。考虑翻墙或配置淘宝镜像。
* 不包含Jar依赖包。
    * 建立Maven本地仓库，在Idea中自行启用自动导入功能。
* 忽略`target`目录。
    * 在Idea中自行构建项目。

# 注意事项

## 如何运行项目

首先运行后台的SpringBoot项目，然后运行前端的Angular / Ionic项目即可。

```bash
# 准备并运行后台程序
cd project-dir
mvn install
mvn spring-boot:run

# 准备并运行前端程序
cd src/main/frontend
npm install
# ng build --prod
ng serve

### 或者：
cd src/main/mobile
npm install
# ng build --prod
ionic serve
```

## 如何整合前后端

* 后端直接使用`@RestController`，绑定Rest风格的url，收发json数据。
* 仍然需要添加必要的`@PathVariable`，为GET请求添加必要的`@RequestParam`，为其他请求添加必要的`@RequestBody`
* 前端直接通过http请求，指定对应数据类型的泛型，以对应的url和http头为参数，收发json数据。
* 后端不需要配置视图解析器，跳过模版引擎。

```
//添加英雄的前端方法，参数为实体类数据，返回值为实体类数据的可观察对象
addHero (hero: Hero): Observable<Hero> {
  //发送http请求，请求类型为POST，泛型为实体类，参数为api地址、请求体、http选项
  return this.http.post<Hero>(this.heroesUrl, hero, httpOptions).pipe(
	//tap()方法用于处理得到的数据，可选参数为响应体对应的数据
    tap((newHero: Hero) => this.log(`added hero w/ id=${newHero.id}`)),
	//catch()方法用于处理错误，可选参数为http状态码
    catchError(this.handleError<Hero>('addHero'))
  );
}
```

## 如何运行Angular端对端测试

[Angular E2E 测试之路一](https://segmentfault.com/a/1190000002454789)

```bash
npm install -g protractor
# 大概率会报超时错误，请尝试配置淘宝镜像或翻墙，
# 或者直接将项目的reference/_other/selenium目录下的文件，
# 移动到npm全局或者项目前端的node_modules\protractor\node_modules\webdriver-manager目录下。
webdriver-manager update --gecko false --alternate_cdn
webdriver-manager start
ng build
ng serve
ng e2e
```

## 数据库连接出错

* 对于Idea的数据库连接，打开配置，在参数`URL`后面加上`?GMT%2B8`。
* 如果运行时出错，在`application.yml`中的`spring.datasource.url`后面也要加上，或者特别配置`serverTimezone: GMT-8`。
* 解决关闭数据池时报错SSLException问题：特别配置`useSSL: false`
* 解决内存溢出问题：在tomcat的配置文件`context.xml`中配置Resource.closeMethod=close

## 后台问题解决

* RequestRejectedException
    * 解决方案：将请求链接写成`http://localhost:8080/csnt-portal/api`的形式。
    * 参考链接：[Stack Overflow](https://stackoverflow.com/questions/48453980/spring-5-0-3-requestrejectedexception-the-request-was-rejected-because-the-url)

## 如何让方滨兴教授加倍去世

[淘宝 NPM 镜像使用](https://segmentfault.com/a/1190000002558754)

## Ionic项目页面设计的一般思路

* 项目的模版是tabs，包含3个tab：tab-home、tab-info、tab-info。
* 项目的模块包括：app、tabs和所有的tab，以及app-route、tabs-route、tab-info-route等。
* home页面包括静态展示信息，只需写在一个组件里。使用轮播图、卡片列表等标签。
* info页面包括从后台读取的条目信息，分别写在不同的组件里，共享header和footer。使用搜索框、卡片列表等标签。
    * info页面的头部标签下面也有自己的导航栏。
    * info页面的内容底部有一个简单的分页栏。
* account页面包括用户的信息页面，显示并可同时更改必要的用户资料。使用表单输入、按钮等标签。
    * 页面头部右上角有一个下拉菜单，里面目前仅包含注销按钮。
    * 如果用户未登录/注册，account页面将会显示登录/注册按钮。
* 所有页面的头部左上角都有返回上一页按钮。
* 发生错误时，跳转到统一的错误页面。

## ionic的问题解决 

错误信息：

```
ERROR in node_modules/@angular/core/src/render3/ng_dev_mode.d.ts(9,11): error TS2451: Cannot redeclare block-scoped variable 'ngDevMode'.
node_modules/angular-io-overlay/node_modules/@angular/core/src/render3/ng_dev_mode.d.ts(9,11): error TS2451: Cannot redeclare block-scoped variable 'ngDevMode'.
```

解决方法：在项目根目录的`tsconfig.json`的`compilerOptions`里面添加：

```
//锁定库的版本
"paths": {
  "@angular/*": ["node_modules/@angular/*"]
}
//或者：忽略重复的库
"skipLibCheck": true
```

必要时，执行命令`npm uninstall` `npm install`

******

错误信息：

```
ERROR Error: Uncaught (in promise): Error: RouterModule.forRoot() called twice. Lazy loaded modules should use RouterModule.forChild() instead.
Error: RouterModule.forRoot() called twice. Lazy loaded modules should use RouterModule.forChild() instead.
```

解决方法：

* 这是使用懒加载时可能发生的问题
* 只限顶层模块使用`RouterModule.forRoot()`，并且不能使用`{preloadingStrategy: PreloadAllModules}`
* 子模块必须使用`RooterModule.forChild()`
* 不要在其他模块中导入顶层模块，创建一个共享模块，然后包含需要经常使用的指令

******

错误信息：

```
ERROR Error: StaticInjectorError(AppModule)[IonRouterOutlet -> ChildrenOutletContexts]: 
StaticInjectorError(Platform: core)[IonRouterOutlet -> ChildrenOutletContexts]: 
```

解决方法：

* 对于每个包含`router-outlet`的组件的html文件，其ts文件中必须具有对`RouterModule.forRoot()`的引用。
