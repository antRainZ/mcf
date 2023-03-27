<template>
  <div class="main">
    <el-row :gutter="10">
      <el-col :span="10">
        <el-input v-model="queryInfo.title" placeholder="请输入要查的标题" clearable @clear="getData">
          <el-button slot="append" icon="el-icon-search" @click="getData"></el-button>
        </el-input>
      </el-col>
    </el-row>

    <el-table :data="tableData"  style="width: 100%" >
      <el-table-column type="index" width="50"></el-table-column>
      <el-table-column label="帖子标题" prop="invitationTitle" width="100"></el-table-column>
      <el-table-column label="评论内容" prop="content" width="100"></el-table-column>
      <el-table-column label="创建时间" prop="createTime"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleShow(scope.row.invitationId)">查看</el-button>
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
  </div>
</template>

<script>
import postCommentApi from '@/api/postComment'
export default {
  name:"myPost",
  data () {
    return {
      queryInfo: {
        title: '',
        page: 1,
        limit: 5
      },
      tableData: [],
      total: 0,
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData () {
      postCommentApi.getMyList(this.queryInfo).then(response=>{
        this.tableData = response.data.records
        this.total = parseInt(response.data.total)
      })
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
    handleDelete(id) {
      this.$confirm('此操作将删除该帖子, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        postCommentApi.del(id).then(_=>{
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
    }
  }

}
</script>