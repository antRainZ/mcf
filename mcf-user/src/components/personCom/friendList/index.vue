<template>
  <div class="main">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="好友列表" name="first">
        <!-- <el-table :data="friendTableData"  style="width: 100%" >
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column label="昵称" prop="nickname" ></el-table-column>
          <el-table-column label="专业" prop="major"></el-table-column>
          <el-table-column label="学院" prop="school"></el-table-column>
          <el-table-column label="个人介绍" prop="introduce"></el-table-column>
          <el-table-column label="操作" width="200px">
            <template slot-scope="scope">
              <el-button type="primary" @click="showChat(scope.row.id)">聊天</el-button>
            </template>
          </el-table-column>
        </el-table> -->
        <myChat :friendList = "friendTableData" />
      </el-tab-pane>
      <el-tab-pane label="申请列表" name="second">
        <el-table :data="applyTableData"  style="width: 100%" >
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column label="申请者" prop="sendName" > </el-table-column>
          <el-table-column label="接受申请名" prop="acceptName" > </el-table-column>
          <el-table-column label="申请内容" prop="content"></el-table-column>
          <el-table-column label="申请时间" prop="createTime"></el-table-column>
          <el-table-column label="操作" width="200px">
            <template slot-scope="scope">
              <template v-if = "scope.row.sendName != username">
                <el-button type="success" @click="accept(scope.row.id)">同意</el-button>
                <el-button type="primary" @click="reject(scope.row.id)">拒绝</el-button>
              </template>
              <template v-else>
                <el-button type="primary" @click="deleteApply(scope.row.id)">删除</el-button>
              </template>
            </template>
          </el-table-column>
        </el-table>        
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import myChat from './myChat'
import applyApi from '@/api/apply'
import { readBySession } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: "friendList",
  data() {
      return {
        activeName: 'first',
        friendTableData: [],
        applyTableData: [],
        username: '',
      };
    },
    components: {
      myChat
    },
    mounted () {
      this.username = readBySession("username")
      this.getData()
      this.getApplyData()
    },
    methods: {
      getData () {
        applyApi.getMyFriend().then(response=>{
          this.friendTableData = response.data
        })
      },
      getApplyData() {
        applyApi.getApplyList().then(response=>{
          this.applyTableData = response.data
        })
      },
      handleClick(tab, event) {
        //console.log(tab, event);
      },
      showChat () {
        
      },
      deleteApply () {
        this.$confirm('此操作将删除该好友申请信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          applyApi.del(id).then(_=>{
            this.getApplyData()
          })
        })
      },
      accept (id) {
        applyApi.accept(id).then(_=>{
          this.getData()
          this.getApplyData()
        })
      },
      reject (id) {
        applyApi.reject(id).then(_=>{
          this.getData()
          this.getApplyData()
        })
      }
    }
}
</script>
