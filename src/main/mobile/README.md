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

对齐：

* 对于ion-row、ion-col：使用align-xxx进行内容的上下对齐，使用justify-xxx进行内容的左右对齐。
* justify-content-around：所有内容分别中间对齐，justify-content-between：所有内容分别左右拉伸。
* 可以使用对应的class，也可以使用对应的顺序。
* 可以省略ion-grid。ion-row里面必须是ion-col。
* 对于ion-row、ion-col、里面的div：使用text-xxx进行文本的对齐。
