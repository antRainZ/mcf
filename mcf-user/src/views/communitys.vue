<template>
  <div>
    
    <el-row class="container" :gutter="10" style="margin-left:20%;margin-top:90px">
      <el-col :span="10">
        <el-input v-model="queryInfo.title"  placeholder="请输入要查的社区名" clearable @clear="getData">
          <el-button slot="append" icon="el-icon-search" @click="getData"></el-button>
        </el-input>
      </el-col> 
    </el-row>

    <!-- 第一行 -->
    <el-row class="container" >
      <el-col :span="4" v-for="(item, index) in tableData1" :key="item.id" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }" style="height:250px;width:300px">
          <img 
          :src="item.cover"
          style="height:150px;width:300px" 
          class="image">
          <div style="padding: 14px;">
            <span>{{item.title}}</span>
            <div class="bottom clearfix">
              <time class="time">{{ item.createTime }}</time>
              <el-button type="text" class="button" @click="toTopic(item.id)"> 查看 </el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 第二行 -->
    <el-row class="container">
      <el-col :span="4" v-for="(item, index) in tableData2" :key="item.id" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }" style="height:250px;width:300px">
          <img 
          :src="item.cover"
          style="height:150px;width:300px" 
          class="image">
          <div style="padding: 14px;">
            <span>{{item.title}}</span>
            <div class="bottom clearfix">
              <time class="time">{{ item.createTime }}</time>
              <el-button type="text" class="button"  @click="toTopic(item.id)">查看</el-button>
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

  </div>
</template>

<script>
import topicApi from '@/api/topic'
import { readBySession } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: 'communitys',
  data() {
    return {
      queryInfo: {
        title: '',
        page: 1
      },
      tableData1: [],
      tableData2: [],
      total: 0,
    };
  },
  created() {
    this.getData()
  },
  methods: {
    getData () {
      topicApi.getList(this.queryInfo).then(response=>{
        this.tableData1 = response.data.records.slice(0,3)
        this.tableData2 = response.data.records.slice(3)
        this.total = parseInt(response.data.total)
      })
    },
    handleCurrentChange (newPage) {
      this.queryInfo.page = newPage
      this.getData()
    },
    toTopic(id) {
      this.$router.push({path: `/community/${id}`}) 
    }
  },
}
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