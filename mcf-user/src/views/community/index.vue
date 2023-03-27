<template>
<div class="index dis-fx">
  <div class="dynamic dis-fx">
    <div class="article">
        <div class="show mt-0925 bc-fff">
          <HeadText content="置顶" />
          <ArticleCard v-for="(item,index) in topList" :key="index" :article="item" @click.native="goDetail(item.id)" />
        </div>
        <div class="show mt-0925 bc-fff">
          <HeadText content="最新" />
          <ArticleCard v-for="(item,index) in articleList" :key="index" :article="item" @click.native="goDetail(item.id)" />
          <el-row  style="margin-left:30%;padding-bottom: 10px">
            <el-pagination style="text-align:center;"
              @current-change="handleCurrentChange"
              :current-page.sync="queryInfo.page"
              :page-size="5"
              layout="total, prev, pager, next"
              :total="total">
              </el-pagination>
          </el-row>
        </div>
      </div>
      <div class="aside">
        <div class="singIn mt-0925 bc-fff pb-10">
          <HeadText :content="topic.title" />
            <el-image
              style="width: 226px; height: 146px; margin:5px 7px 0;"
              v-if = "topic.cover"
              :src="topic.cover"
              fit="fit"></el-image>
        </div>
        <div class="issue mt-0925 bc-fff pb-10">
          <HeadText content="发布见解" />
          <div class="fx-jc mt-10 mb-10">
            <myButton val="我要发帖" @click.native="addPostShow()"/>
          </div>
        </div>
      </div>
  </div>
  <el-dialog title="发帖" :visible.sync="addShow">
    <el-form :model="form" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="帖子内容">
        <el-input type="textarea" v-model="form.content" 
          placeholder="请输入帖子内容" :rows="6" maxlength="1000" show-word-limit></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="addShow = false">取 消</el-button>
      <el-button type="primary" @click="addPost()">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
import HeadText from '@/components/pubCom/headText'
import myButton from '@/components/pubCom/myButton'
import ArticleCard from './articleCard'
import invitationApi from '@/api/invitation'
import topicApi from '@/api/topic'
import { readBySession } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: 'topic',
  components:{
    HeadText,
    myButton,
    ArticleCard
  },
  data(){
    return {
      articleList:[],
      topList:[],
      topicId: '',
      queryInfo: {
        title: '',
        page: 1,
        limit: 5,
        id: ''
      },
      topic: {},
      total: 0,
      addShow: false,
      form: {
        topicId: '',
        topicTitle: '',
        title: '',
        content: ''
      }
    }
  },
  mounted() {
    this.topicId = this.$route.params.id
    this.queryInfo.id = this.topicId
    topicApi.getInfo(this.topicId).then(response=>{
      this.topic = response.data
      if (!isNotBlank(this.topic.cover)) {
        // 设置图片的默认值
        this.topic.cover = ""
      }
      this.form.topicId = this.topicId
      this.form.topicTitle = this.topic.title
    })
    this.getRefineList()
    this.getLatestList()
  },
  methods: {
    getRefineList() {
      invitationApi.topicRefine({id: this.topicId}).then(response=>{
        this.topList = response.data.records
      })
    },
    getLatestList() {
      invitationApi.topicLatest(this.queryInfo).then(response=>{
        this.articleList = response.data.records,
        this.total = parseInt(response.data.total)
      })
    },
    goDetail(id) {
      this.$router.push({path: `/post/detail/${id}`})  
    },
    handleCurrentChange (newPage) {
      this.queryInfo.page = newPage
      this.getLatestList()
    },
    addPostShow (item) {
      let nickname = readBySession("nickname")
      if (!isNotBlank(nickname)) {
        this.$message({
          message: '请先登录',
          type: 'warning'
        })
        return
      }
      this.form.title = ''
      this.form.content = ''
      this.addShow = true
    },
    addPost () {
      invitationApi.add(this.form).then( response =>{
        this.form.content = ''
        this.form.title = ''
        if (response.code != 200) {
          return
        } 
        this.addShow = false
        this.$message({
          message: '请等待管理员审核通过',
          type: 'success'
        })
      })
    }
  }
}
</script>

<style lang="less" scoped>
  .index{
    background-color: #f4f5f5;
    justify-content: center;
    padding-top: 4rem;
    min-height: 94vh;
    padding-bottom: 1.25rem;
  }
  .dynamic{
    max-width: 960px;
    .article{
      width: 700px;
      margin-right: 1.25rem;
      font-size: 0.975rem;
    }
    .aside{
      width: 240px;
      flex: 1;
      .singIn{   
        .myButton{
          margin: 0 auto;
        }
      }
    }
  }
</style>