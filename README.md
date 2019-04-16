# 概述

学期项目：计算机科学与技术门户网站。

介绍专业特色、实时动态、教师队伍、专业发展、教学改革和学习专栏等。

# 功能需求

# 功能实现

# 环境要求

* 通用
	* Idea（最新版本，淘宝教育版帐号并不贵，同时安装好相关插件）
	* JDK（11.0.2，同时配置好相关环境变量）
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
	* Maven
	* MySQL
	* Git
	* Markdown
	* Xml
	* Json
	* Yaml
* 前端
	* Html5
	* Css3
	* JavaScript(ES6)
	* Bootstrap4
	* TypeScript
	* Angular7
* 后台
	* Java
	* Spring
	* SpringBoot
	* SpringWebMvc
	* SpringDataJba
	* Redis
	
# 目录概述

```
[src] 
  [main]            #源文件目录 
    [java]              #java源代码目录（SpringBoot）
    [resources]         #资源文件目录
    [frontend]          #前端文件目录（Angular）
  [test]            #测试文件目录
    [java]              #java测试代码目录
.gitignore      # Git忽略项文件
pom.xml         # Maven依赖项文件
README.md      # 项目说明文档
...
```

# 注意事项

## 特殊注释

* 标有`TODO`的注释：有待完成的代码。
* 标有`NOTE`的注释：需要特别配置或者特别注意的代码。

## 数据库连接出错

* 对于Idea的数据库连接，打开配置，在参数`URL`后面加上`?GMT%2B8`。
* 如果运行时出错，在`application.yml`中也要特别配置`serverTimezone: GMT-8`。