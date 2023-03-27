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
            <el-pagination style="text-align:center;"
              @current-change="handleCurrentChange"
              :current-page.sync="queryInfo.page"
              :page-size="5"
              layout="total, prev, pager, next"
              :total="total">
              </el-pagination>
          </div>
      </div>
      <div class="aside">
        <div class="singIn mt-0925 bc-fff pb-10">
          <HeadText content="签到" />
          <div class="fx-jc mt-10 mb-10">
            <myButton val="每日签到" @click.native="singIn"  v-if="singInShow" />
            <myButton val="今天已完成签到" v-else />
          </div>
          <!-- <p class="ta-c l-h-12 f-12" >已连续签到<span>0</span>天</p>  -->
        </div>
        <!-- <div class="issue mt-0925 bc-fff pb-10">
          <HeadText content="发布见解" />
          <div class="fx-jc mt-10 mb-10">
            <myButton val="发帖" />
          </div>
        </div> -->
        <!-- <div class="active mt-0925 bc-fff pb-10">
          <HeadText content="活跃用户" />
        </div>
        <div class="tags mt-0925 bc-fff pb-10">
          <HeadText content="标签云" />
        </div>
        <div class="link mt-0925 bc-fff pb-10">
          <HeadText content="友情链接" />
        </div> -->
      </div>
  </div>
  </div>
</template>

<script>
import HeadText from '@/components/pubCom/headText'
import myButton from '@/components/pubCom/myButton'
import ArticleCard from './articleCard'
import articleApi from '@/api/article'
import integralApi from '@/api/integral'
import { readBySession } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: 'index',
  components:{
    HeadText,
    myButton,
    ArticleCard
  },
  data() {
    return {
      articleList:[],
      topList:[],
      singInShow: true,
      queryInfo: {
        page: 1,
        limit: 5
      },
      total: 0
    }
  },
  mounted() {
    articleApi.indexRefine().then(response=>{
      this.topList = response.data
    })
    this.getData()
  },
  methods: {
    goDetail(id) {
      this.$router.push({path: `/article/detail/${id}`})  
    },
    getData() {
      articleApi.latest(this.queryInfo).then(response=>{
        this.articleList = response.data.records 
        this.total = parseInt(response.data.total)
      }) 
    },
    handleCurrentChange (newPage) {
      this.queryInfo.page = newPage
      this.getData()
    },
    singIn() {
      let nickname = readBySession("nickname")
      if (!isNotBlank(nickname)) {
        this.$message({
          message: '请先登录',
          type: 'warning'
        })
        return
      }
      integralApi.checksignIn().then(response=>{
        if (response.code == 200) {
          integralApi.signIn().then(response=>{
            if (response.code == 200) {
              this.$message({
                message: '请先登录',
                type: 'success'
              })
            }
          })
        } else {
          this.singInShow = false
        }
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
}
.dynamic{
  max-width: 960px;
  .article{
    width: 700px;
    margin-right: 1.25rem;
    font-size: 0.975rem;
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
