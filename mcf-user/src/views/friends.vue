<template>
  <div>
    
    <el-row class="container" :gutter="10" style="margin-left:20%;margin-top:90px">
      <el-col :span="10">
        <el-input  v-model="queryInfo.name" placeholder="请输入要查的用户名" clearable @clear="getData">
          <el-button slot="append" icon="el-icon-search" @click="getData"></el-button>
        </el-input>
      </el-col> 
    </el-row>

    <el-row class="container">
      <el-col :span="4" v-for="(item, index) in tableData1" :key="item.id" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }" style="height:250px;width:300px; margin-right:10px;">
          <img 
          :src="item.avatar"
          style="height:150px;width:300px" 
          class="image">
          <div style="padding: 14px;">
            <span>{{item.nickname}} 
              <el-tooltip class="item" effect="dark" :content="item.interest" placement="top">
                <i class="el-icon-info"></i> 
              </el-tooltip>
            </span>
            <span v-if="item.score" style="margin-left:40px"> 推荐指数： {{item.score}}</span>
            <div class="bottom clearfix">
              <time class="time">{{ item.updateTime }}</time>
              <el-button type="text" class="button" @click="applyShow(item)"> 好友申请 </el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row class="container">
      <el-col :span="4" v-for="(item, index) in tableData2" :key="item.id" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }" style="height:250px;width:300px">
          <img 
          :src="item.avatar"
          style="height:150px;width:300px" 
          class="image">
          <div style="padding: 14px;">
            <span>{{item.nickname}} 
              <el-tooltip class="item" effect="dark" :content="item.interest" placement="top">
                <i class="el-icon-info"></i> 
              </el-tooltip>
            </span>
            <span v-if="item.score" style="margin-left:40px"> 推荐指数： {{item.score}}</span>
            <div class="bottom clearfix">
              <time class="time">{{ item.updateTime }}</time>
              <el-button type="text" class="button" @click="applyShow(item)"> 好友申请 </el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    
    <el-pagination style="text-align:center;"
      @current-change="handleCurrentChange"
      :current-page.sync="queryInfo.page"
      :page-size="6"
      layout="total, prev, pager, next"
      :total="total">
      </el-pagination>

    <el-dialog title="好友申请" :visible.sync="addShow">
      <el-form :model="form" label-width="80px">
        <el-form-item label="申请内容">
          <el-input type="textarea" v-model="form.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addShow = false">取 消</el-button>
        <el-button type="primary" @click="addApply()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import userApi from '@/api/user'
import applyApi from '@/api/apply'
import { readBySession } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: 'friends',
  data() {
    return {
      queryInfo: {
        name: '',
        page: 1
      },
      tableData1: [],
      tableData2: [],
      total: 0,
      addShow: false,
      form: {
        content: '',
        id: '',
        acceptName: ''
      }
    };
  },
  created() {
    this.getData()
  },
  methods: {
    getData () {
      let nickname = readBySession("nickname")
      if (!isNotBlank(nickname)) {
        this.$message({
          message: '登录后获取，获得更好的好友推荐展示',
        })
        userApi.getList(this.queryInfo).then(response=>{
          let data = response.data.records || []
          this.tableData1 = data.slice(0,3)
          if (data.length>4) {
            this.tableData2 = data.slice(3)
          } else {
            this.tableData2 = []
          }
          this.total = parseInt(response.data.total)
        })
      } else {
        userApi.getSmartList(this.queryInfo).then(response=>{
          if (response.msg && response.msg != '操作成功') {
            this.$message({
              message: response.msg,
            })
          }
          let data = response.data.records || []
          this.tableData1 = data.slice(0,3)
          if (data.length>4) {
            this.tableData2 = data.slice(3)
          } else {
            this.tableData2 = []
          }
          this.total = parseInt(response.data.total)
        })
      }
    },
    handleCurrentChange (newPage) {
      this.queryInfo.page = newPage
      this.getData()
    },
    applyShow (item) {
      let nickname = readBySession("nickname")
      if (!isNotBlank(nickname)) {
        this.$message({
          message: '请先登录',
          type: 'warning'
        })
        return
      }
      this.addShow = true
      this.form.id = item.id
      this.form.acceptName = item.username
    },
    addApply ( ){
      applyApi.applyFriend(this.form).then(response => {
        if (response.code == 200) {
          this.$message({
            message: response.msg,
            type: 'success'
          });
        }
        this.addShow = false
      })
    }
  },
}
/**
     * Java实现余弦定理计算文本相似度 https://blog.csdn.net/chengwangbaiko/article/details/78742237
     * 计算两个字符串之间的文本相似度 https://zhuanlan.zhihu.com/p/91645988
     * NLP——计算文本相似度的常用算法 https://blog.csdn.net/weixin_42572826/article/details/107107659
 */
</script>

<style>
  .container {
    margin-left: 20%;
    margin-top: 20px;
  }
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>

