<template>
  <div class="main">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="社区资料" name="material">
        <p>标题：{{topic.title}}</p>
        <p>描述：{{topic.description}}</p>
        <p>申请理由：{{topic.reason}}</p>
        <p>状态：<el-tag>{{ status[topic.status]}}</el-tag> </p>
        <p v-if="topic.remark && topic.remark != ''">平台备注：{{topic.remark}}</p>
        <br/>
        <el-row :gutter="20">
          <el-col :span="4">封面图片:</el-col>
          <el-col :span="10">
            <el-image
              style="width: 100px; height: 100px"
              :src="topic.cover"
              fit="fit"></el-image>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="社区帖子" name="invitation" v-if=" topic && topic.status == 'Y'">
        <el-row :gutter="10">
          <el-col :span="10">
            <el-input v-model="queryInfo.title" placeholder="请输入要查的标题" clearable @clear="getData">
              <el-button slot="append" icon="el-icon-search" @click="getData"></el-button>
            </el-input>
          </el-col>
          <el-col :span="14"> 
            <el-button type="info" icon="el-icon-refresh" plain @click="getData">刷新</el-button>
            <el-button type="primary" icon="el-icon-circle-plus-outline" plain @click="handlerAddShow">添加</el-button>
          </el-col>
        </el-row>

        <el-table :data="tableData"  style="width: 100%" >
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column label="标题" prop="title" width="200"></el-table-column>
          <el-table-column label="是否加精" align="center" prop="refine" >
            <template slot-scope="scope">
                <el-switch
                  :value=" scope.row.refine == 'Y' "
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  :disabled = "scope.row.status == 'N'" 
                  @change = "editRefine(scope.row)">
                </el-switch>      
              </template>
            </el-table-column>
          <el-table-column label="有效" align="center" prop="status" > 
            <template slot-scope="scope">
              <el-switch
                :value=" scope.row.status == 'Y' "
                active-color="#13ce66"
                inactive-color="#ff4949"
                @change = "changeStatus(scope.row)">
              </el-switch>    
            </template>
          </el-table-column>
          <el-table-column label="发布者" prop="nickname"></el-table-column>
          <el-table-column label="创建时间" prop="createTime" width="200"></el-table-column>
          <el-table-column label="操作" width="180px" fixed="right">
            <template slot-scope="scope">
                <el-button type="primary" @click="handleShow(scope.row.id)">查看</el-button>
                <el-button type="danger"  @click="handleDelete(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页区域 -->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.page"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="queryInfo.limit"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        ></el-pagination>

      </el-tab-pane>
      <!-- <el-tab-pane label="评论管理" name="discuss">
      </el-tab-pane> -->
    </el-tabs>

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
import invitationApi from '@/api/invitation'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  name: "manage",
  props: ["topic"],
  data() {
    return {
      status: {
        Y: '有效',
        N: '无效',
        A: '申请中',
        R: '拒绝',
      },
      activeName: 'material',
      queryInfo: {
        title: '',
        page: 1,
        limit: 5,
        id: ''
      },
      tableData: [],
      total: 0,
      form: {},
      addShow: false
    };
  },
  mounted () {
    if ( this.topic.id && this.topic.id != '') {
      this.queryInfo.id = this.topic.id
      this.getData()
    }
  },
  methods: {
    getData () {
      if (!this.topic.id || this.topic.id == '') return
      invitationApi.topicList(this.queryInfo).then(response=>{
        this.tableData = response.data.records
        this.total = parseInt(response.data.total)
      })
    },
    handleClick(tab, event) {
      //console.log(tab, event);
    },
    handleAvatarSuccess(res, file) {
      this.form.cover = res.data
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    handleSizeChange (newSize) {
      this.queryInfo.limit = newSize
      this.getData()
    },
    // 监听 页码值 改变的事件
    handleCurrentChange (newPage) {
      this.queryInfo.page = newPage
      this.getData()
    },
    handlerAddShow () {
      this.form = {
        topicId: this.topic.id,
        topicTitle: this.topic.title,
        title: '',
        content: '',
        status: 'Y'
      }
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
        this.getData()
      })
    },
    handleDelete(id) {
      this.$confirm('此操作将删除该帖子, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        invitationApi.del(id).then(_=>{
          this.getData()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
      })
    },
    handleShow(id) {
      this.$router.push({path: `/post/detail/${id}`})
    },
    editRefine (row) {
      let id = row.id
      let text = '是否加精，将优先显示在前面 '
      let refine = 'Y'
      if (row.refine == 'Y' ) {
        text = '是否取消加精，取消后将不在优先显示在前面'
        refine = 'N'
      }
      this.$confirm(text, "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return invitationApi.update({ id, refine})
      }).then( _ => {
        row.refine = row.refine == 'Y' ? 'N':'Y'
      })
    },
    changeStatus (row) {
      let status = row.status == 'Y' ? 'N':'Y'
      invitationApi.update({
        id: row.id,
        status
      }).then( _ => {
        row.status = status
      });
    }
  }
}
</script>
