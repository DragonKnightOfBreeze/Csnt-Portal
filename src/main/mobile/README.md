组件中成员排序：

* 参数（路径参数，矩阵参数，查询参数）
* 模型对象（需要展示的数据）
* 类型引用（对于枚举）
* 构造方法（与其他方法之间空2行）
* 钩子方法（如`onInit()`）
* 数据处理方法
* 其他方法
* 异步加载方法（如`presentModal()`）

默认样式：

* ion-list：lines="inset"
* ion-button：color="primary"
* ion-note: color="medium"

对齐：

* 对于ion-row、ion-col：使用align-xxx进行内容的上下对齐，使用justify-xxx进行内容的左右对齐。
* justify-content-around：所有内容分别中间对齐，justify-content-between：所有内容分别左右拉伸。
* 可以使用对应的class，也可以使用对应的顺序。
* 可以省略ion-grid。ion-row里面必须是ion-col。
* 对于ion-row、ion-col、里面的div：使用text-xxx进行文本的对齐。

模块导入问题：

* 每个模块都有必要导入必要的模块，作为提供商。
* BrowserModule只需要导入一次。其他模块导入CommonModule。
* 父模块使用RouterModule.forRoot()，子模块只能使用RouterModule.forChild()

其他问题：

* popover、modal等类型的组件，需要在对应模块的entryComponents中声明。
* 若要在页面打开时加载数据，使用ionViewWIllEnter或ionViewDidEnter，而不是onInit。
* 一旦一个页面中出现错误，整个页面都将不能正常显示。
