# 1.x.x

## 1.1.x

* 1.1.0 完成基本的功能。
* 1.1.1 移除后台验证的本地化消息以及关联的注解字段。
* 1.1.2 略微调整参数验证逻辑。
* 1.1.3 整理文档和目录。

## 1.2.x

* 1.2.0 创建ionic子项目，更新README文档。
* 1.2.1 完成ionic目录和文件的创建，完成ionic路由的配置。
* 1.2.2 重命名组件显示的标题，完善ionic路由的配置，修正userService，使用ionic自身的storage。
* 1.2.3 为详情页添加ion-back-button。
* 1.2.4 添加info-menu组件，并为列表页添加ion-menu-toggle。
* 1.2.5 添加account-menu组件，替换ion-menu-toggle为ion-menu-button。
* 1.2.6 完成account-page组件的html、ts，为各组件导入AppModule。
* 1.2.7 完成login、register组件的html、ts。
* 1.2.8 完成各种column组件的html、ts（复制粘贴）。
* 1.2.9 完成dynamic-list、dynamic-detail组件的html、ts。创建其他相关的dynamic组件。
* 1.2.10 完成teacher-info-detail、teacher-team-detail组件的html、ts。
* 1.2.11 完成除了popover和modal之外的所有组件的html、ts。
* 1.2.12 将info目录下的组件拆分到具体的子目录中，去除ts中不必要的代码，使用解构声明重构。
* 1.2.13 重命名文件（Vo），移动目录（enums），整理依赖。
* 1.2.14 修正account组件中的bug。
* 1.2.15 完成全部的前端代码，待测试。
* 1.2.16 修正错误中。
* 1.2.17 修正错误使项目能够正常启动，待测试。更新README.md。 
* 1.2.18 调整页面格式，适用按钮对齐和标签前置。
* 1.2.19 修正*ngFor的错误用法，使之位于元素标签上（而非集合标签），尽可能地使用ng-container。
* 1.2.20 整理项目文档，删除TODO.md。
* 1.2.21 抽取menu组件所在目录到app/page/menu目录下。
* 1.2.22 解决排版问题，解决menu不能打开的问题，完善路由，抽取menu组件所在目录到app/menu目录下，数据读取仍有错误。
* 1.2.23 修正详情页的排版和显示格式。
* 1.2.24 修正显示格式，解决目前为止发现的所有bug。

**——已通过测试——**

* 1.2.25 重命名模块，更新文档。
* 1.2.26 修正查询参数直接传递对象而非json字符串的bug。
* 1.2.27 修正bug，尝试使用异步数据重构组件。 
* 1.2.28 修正查询参数变化时，页面数据不刷新的bug。
* 1.2.29 修正查询参数变化时，页面数据不刷新的bug（确信）。

# TODO

- [ ] 整合Docker。
- [ ] 整合Swagger。
- [ ] 使用Vue代替Angular。
- [ ] 使用Weex替代Ionic。
- [ ] 重构后台高级查询的实现，基于Specification的Spring Data Jpa查询。
