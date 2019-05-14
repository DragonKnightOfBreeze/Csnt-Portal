# 概述

学期项目：计算机科学与技术门户网站。

介绍专业特色、实时动态、教师队伍、专业发展、教学改革和学习专栏等。

# 参考连接

* Angular教程
	* [Angular - 什么是 Angular？](https://www.angular.cn/docs)
* SpringBoot教程
    * [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle)
	* [Spring Boot 中文导航](http://springboot.fun/)
* Demo参考
	* [Angular-SpringBoot-REST-JWT](https://github.com/mrin9/Angular-SpringBoot-REST-JWT)
	* [SpringBootAngular7Integration](https://github.com/SKrudra/SpringBootAngular7Integration)
	* [SpringBoot-Angular7-ShoppingCart](https://github.com/zhulinn/SpringBoot-Angular7-ShoppingCart)

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

## 特殊注释

* 标有`TODO`的注释：有待完成的代码。
* 标有`NOTE`的注释：需要特别配置或者特别注意的代码。

## 数据库连接出错

* 对于Idea的数据库连接，打开配置，在参数`URL`后面加上`?GMT%2B8`。
* 如果运行时出错，在`application.yml`中的`spring.datasource.url`后面也要加上，或者特别配置`serverTimezone: GMT-8`。
* 解决关闭数据池时报错SSLException问题：特别配置`useSSL: false`
* 解决内存溢出问题：在tomcat的配置文件`context.xml`中配置Resource.closeMethod=close

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

## 如何运行项目

首先运行后台的SpringBoot项目，然后运行前端的Angular项目即可。

```bash
# 准备并运行后台程序
cd project-dir
mvn install
mvn spring-boot:run
# 准备并运行前端程序
cd src/main/frontend
npm install
ng build --prod
ng serve
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

## 如何让方滨兴教授加倍去世

[淘宝 NPM 镜像使用](https://segmentfault.com/a/1190000002558754)


