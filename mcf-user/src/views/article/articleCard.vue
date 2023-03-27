<template>
  <div class="articleCard fx-ac" >
      <div class="head cur-po">
        <img src="" alt="">
      </div>
    <div class="main">
      <div class="title l-h-12 f-16">{{article.title}}</div>
      <div class="tagGroup">
        <Tag :content="name" v-for="(name,index) in article.labelName" :key="index"  />
      </div>
      <div class="dis-fx f-12">
        <div class="author c-888 l-h-12">{{article.nickname}}</div>
        <div class="date c-888 l-h-12">{{article.createTime}}</div>
      </div>
    </div>
    <!-- <div class="end fx-jc">
      <span v-if="article.top" class="tag f-12 mb-10">置顶</span>
      <div class="f-14 c-888">
        <i class="el-icon-view mr-10"> 0</i>
        <i class="el-icon-chat-dot-square"> 0</i>
      </div>
    </div> -->
  </div>
</template>

<script>
import Tag from '@/components/pubCom/Tag'
export default {
  props:['article'],
  components:{
    Tag
  }
}
</script>

<style lang="less" scoped>
.articleCard{
  padding: 1.125rem 1.5rem;
  .head{
    width: 3.75rem;
    height: 3.75rem;
    background-color: burlywood;
    margin-right: 1.25rem;
  }
  .main{
    flex: 1;
    .author{
      margin-right: 0.625rem;
    }
  }
  .end{
    flex-direction: column;
    .tag{
      width: fit-content;
      color: red;
      border: 1px solid red;
      padding: 0.0625rem 0.1875rem;
      position: relative;
      right: -2.375rem;
    }
  }
  
}
</style>