<template>
  <div class="index dis-fx">
    <div class="main dis-fx">
      <div class="article bc-fff mt-0925 p-20">
        <div class="title f-22">
          <h1>{{article.title}}</h1>
          <div class="bar l-h-12 mb-10 mt-10 f-14 c-888">
            <p> <span> {{article.createTime}} </span> {{article.nickname}}</p>
          </div>
        </div>
        <div class="content mt-40 c-333" v-html="article.content"></div>
        <div class="foot-bar f-12 c-888 pt-10">
          <span>声明：本站内网友所发表的所有内容及言论仅代表其本人，并不反映任何本站之意见及观点。</span>
        </div>
        <div class="commit mt-40 pt-10">
          <el-input type="textarea" placeholder="说点什么" v-model="form.content"></el-input>
          <div class="but mt-10 dis-fx">
            <myButton val="评论" @click.native="addComment"/>
          </div>
          <div class="commit-list mt-20">
            <div v-if="!commitList.length" class="noCommit dis-fx">
              <img src="../../assets/qiangsafa.png" alt="">
              <p>成为第一个评论的人</p>
            </div>
            <div v-else>
              <el-timeline>
                <el-timeline-item :timestamp="item.createTime" placement="top" v-for="item in commitList" :key="item.id">
                  <el-card>
                    <h4>{{item.content}}</h4>
                    <p>发布者：{{item.nickname}}</p>
                  </el-card>
                </el-timeline-item>
              </el-timeline>
              <el-row  style="margin-left:30%;padding-bottom: 10px">
                <el-pagination
                  @current-change="handleCurrentChange"
                  :current-page.sync="queryInfo.page"
                  :page-size="5"
                  layout="total, prev, pager, next"
                  :total="total">
                  </el-pagination>
              </el-row>
            </div>
          </div>
        </div>
        </div>
    </div>
  </div>
</template>

<script>
import HeadText from '@/components/pubCom/headText'
import myButton from '@/components/pubCom/myButton'
import articleApi from '@/api/article'
import { readBySession } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: "articleDetail",
  components:{
    HeadText,
    myButton
  },
  data(){
    return {
      showTextarea:false,
      commitList:[],
      queryInfo: {
        id: '',
        page: 1,
        limit: 5
      },
      form: {
        articleId:'',
        articleTitle: '',
        content: ''
      },
      addShow: '',
      article: {},
      total: 0
    }
  },
  created() {
    this.id = this.$route.params.id
    articleApi.getById(this.id).then(response=>{
      this.article = response.data
      this.queryInfo.id = response.data.id
      this.form.articleId = response.data.id
      this.form.articleTitle = response.data.title
      this.getCommentData()
    })
  },
  methods: {
    getCommentData() {
      articleApi.getCommentList(this.queryInfo).then( response=>{
        this.commitList = response.data.records || []
        this.total = parseInt(response.data.total)
      })
    },
    handleCurrentChange (newPage) {
      this.queryInfo.page = newPage
      this.getData()
    },
    addComment() {
      let nickname = readBySession("nickname")
      if (!isNotBlank(nickname)) {
        this.$message({
          message: '请先登录',
          type: 'warning'
        })
        return
      }
      if (!isNotBlank(this.form.content)) {
        this.$message({
          message: '请输入需要评论的内容',
          type: 'warning'
        })
        return
      }
      articleApi.addComment(this.form).then( response =>{
        this.form.content = ''
        if (response.code != 200) {
          return
        }
        this.getCommentList()
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
.main{
    max-width: 1200px;
    .article{
      width: 960px;
      margin-right: 1.25rem;
      font-size: 0.975rem;
      .foot-bar{
        border-top: 1px solid #eee;
      }
      .but{
        flex-direction: row-reverse;
      }
      .title {
        text-align: center;
      }
      .commit{
        border-top: 1px solid #eee;
        .noCommit{
          flex-direction: column;
          align-items: center;
          img{
            width: 300px;
          }
        }
      }
    }
    .aside{
      width: 15rem;
      flex: 1;
      .singIn{
        .myButton{
          margin: 0 auto;
        }
      }
    }
  }
</style>
