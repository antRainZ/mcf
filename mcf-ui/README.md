# element-ui的样式问题
+ element-ui select 下拉框位置错乱

```html
<!-- 默认是插入到body -->
<el-select v-model="form.deptId" placeholder="请选择" :popper-append-to-body="false">
<!-- 添加自己样式 -->
<style scoped>
.el-select-dropdown {
  top: 38px !important;
  left: 0 !important;
}
</style>
```
+ element-ui 的 table 组件时 表格线条不对齐
```css
body .el-table th.gutter {
    display: table-cell !important
}
```
+ 在vue中添加了样式，但在页面时无效果, 使用css预处理器的时候，使用`/deep/` 样式深度影响
```html
<style scoped>
  .avatar-uploader /deep/ .el-upload:hover {
    border-color: #409EFF;
  }
</style>
```

## element-ui组件
+ 使用Steps步骤条和Tabs 选项卡组件的 使用 `:active="parseInt(activeIndex)" `两者使用不同的类型
	+ Steps active 是需要数值
	+ Tab name 需要字符串
+ el-menu-item 组件 有route 属性，可以传路由对象，单独传string, 会在当前路径上拼接上，建议使用route对象
+ el-image 图片初始化加载失败问题： 原因可能是src路径一旦初始化加载失败的话就没有异步刷新，可以使用v-if进行判断
+ el-table: 通过 Scoped slot 可以获取到 `row, column, $index 和 store`（table 内部的状态管理）的数据。

# vue
+ 组件起名的时候不要使用vue内置的名称，可能会报 `Do not use built-in or reserved HTML elements as component id` 
	+ [参考：聊聊 Vue 组件命名那些事](https://cnodejs.org/topic/5816aabdcf18d0333412d323)
+ 在为自己定义的组件绑定事件的时候，使用 `@click.native` , native 用来监听根元素的原生事件

### 样式
+ [vue中修改第三方组件的样式不生效](https://www.cnblogs.com/youhong/p/11637695.html)
+ 解决方案一:  vue可以混用本地和全局样式，因此对子组件的非根元素样式修改，可以单独放在一个非 scoped 样式中
+ 解决方案二：加上`/deep/`，组件的样式可以渗透到子组件相应的元素上

### props
+ vue是单向数据流,  当直接在子组件修改基本数据的值，会报错，而对象保存的是地址，修改对象的内的值不会报错
	+ 通过$emit派发一个自定义事件，父组件收到后，由父组件进行修改
	+ 如果考虑只在当前子组件进行修改，应该在子组件里深复制一个副本
	+ 通过计算属性修改
	+ 父组件 传进去的时候加上 .sync, 子组件 通过`this.$emit(‘update:xxx’, params) `
+ vue 子组件使用props接收来自父组件的传的值，在子组件的created,mounted生命周期读取不到props的值
	+ 原因：父组件传值使用异步传输，在vue初始化这些声明周期方法的时候，值未传过来
	+ 解决方法：使用vue的watch去监听，完成需要初始化的方法
+ Vue子组件调用父组件的方法
	+ 直接在子组件中通过`this.$parent.event`来调用父组件的方法 （少用）
	+ 在子组件里用$emit向父组件触发一个事件，父组件监听这个事件 （常用）
	+ 在子组件里用$emit向父组件触发一个事件，父组件监听这个事件 （不用）

### 数据响应式变化
+ 数组中添加了数据, 但是打印的时候为[], 点击空数组时里面却有数据，原因在异步之前打印，控制台点击展开会获取最后的结果
	+ 可以使用`setTimeout`等待一段时候再获取数据