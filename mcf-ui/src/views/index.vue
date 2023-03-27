<template>
  <div class="app-container home">
    <!-- <h1>欢迎来到校园交友后台管理系统</h1>  -->
    <el-card class="box-card">
      <ul>
        <li>帮助大一新生了解大学生活，快速适应和融入大学生活</li>
        <li>为大学生提供一个寻找志同道合的交友平台</li>
        <li>大学生分享生活与知识的一个平台</li>
        <li>大学生可以在自己感兴趣的社区里互相讨论</li>
      </ul>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      // 版本号
      version: "3.4.0",
    };
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  h1 {
    text-align: center;
    font-size: 32px;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
    li {
      margin-top: 20px;
      margin-left: 20px;
      font-size: 16px;
    }
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  // ul {
  //   list-style-type: none;
  // }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

