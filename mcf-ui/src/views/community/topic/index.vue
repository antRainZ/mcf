<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入用户名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['community:topic:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['community:topic:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['community:topic:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['community:topic:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="topicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="用户名" align="center" prop="nickname" />
      <el-table-column label="封面" align="center" prop="cover" > 
        <template slot-scope="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.cover"  fit="fit"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="申请文件" align="center" prop="fileUrl" > 
        <template slot-scope="scope">
          <el-link type="primary" :href="scope.row.fileUrl">下载</el-link>
        </template>        
      </el-table-column>
      <el-table-column label="理由" align="center" prop="reason" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="加精" align="center" prop="refine" >
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
      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag>{{status[scope.row.status]}}</el-tag>
        </template> 
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <template v-if = " scope.row.status == 'A'">
            <el-button  size="mini"  type="text" icon="el-icon-edit"  @click="agree(scope.row)"
              v-hasPermi="['community:topic:edit']"
            >通过</el-button>
            <el-button  size="mini"  type="text" icon="el-icon-edit"  @click="reject(scope.row)"
              v-hasPermi="['community:topic:edit']"
            >拒绝</el-button>
          </template>
          <!-- <template v-else-if="scope.row.status == 'Y'">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="addInvitationShow(scope.row)" v-hasPermi="['community:topic:edit']"
              >添加帖子</el-button>
          </template> -->
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['community:topic:edit']"
            >修改</el-button>
          <el-button size="mini" type="text"  icon="el-icon-delete" @click="handleDelete(scope.row)"  v-hasPermi="['community:topic:remove']"
            >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination  v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"  @pagination="getList"/>

    <!-- 添加或修改主题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="封面" >
          <el-upload
            class="avatar-uploader"
            action="http://localhost/dev-api/app/upload/avatar"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="form.cover" :src="form.cover" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="申请文件" prop="fileUrl">
          <el-upload
            action="http://localhost/dev-api/app/upload/apply"
            :on-success ="success"
            :limit="1"
            name="file"
            >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="理由" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    
    <!-- 添加或修改帖子对话框 -->
    <el-dialog :title="title" :visible.sync="invitationShow" width="80%" append-to-body>
      <el-form ref="invitationform" :model="invitationform"  label-width="80px">
        <el-form-item label="标题" >
          <el-input v-model="invitationform.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="invitationform.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="invitationform.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveInvitation()">确 定</el-button>
        <el-button @click="invitationShow == false ">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTopic, getTopic, delTopic, addTopic, updateTopic, exportTopic } from "@/api/community/topic"
import { addInvitation } from "@/api/community/invitation"
import Editor from '@/components/Editor'

export default {
  name: "Topic",
  components: {
    Editor
  },
  data() {
    return {
      status: {
        Y: '有效',
        N: '无效',
        A: '申请中',
        R: '拒绝',
      },
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 主题表格数据
      topicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        nickname: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      invitationform: {
        title: undefined,
        content: undefined,
        remark: undefined,
        topicId: '',
        topicTitle: ''
      },
      invitationShow: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询主题列表 */
    getList() {
      this.loading = true;
      listTopic(this.queryParams).then(response => {
        this.topicList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        title: undefined,
        cover: undefined,
        description: undefined,
        fileUrl: undefined,
        reason: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加主题";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTopic(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改主题";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTopic(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTopic(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除主题编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTopic(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有主题数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTopic(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    success(response) {
      this.form.fileUrl = response.data
    },
    handleAvatarSuccess(res) {
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
    editRefine (row) {
      let id = row.id
      let text = '是否加精，加精后将在主页栏显示'
      let refine = 'Y'
      if (row.refine == 'Y' ) {
        text = '是否取消加精，取消后将不在主页栏显示'
        refine = 'N'
      }
      this.$confirm(text, "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return updateTopic({ id, refine})
      }).then( _ => {
        row.refine = row.refine == 'Y' ? 'N':'Y'
      })
    },
    agree (row) {
      this.$confirm('是否通过该社区申请?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return updateTopic({
          id: row.id,
          status: 'Y'
        })
      }).then(_ => {
        row.status = 'Y'
      })
    },
    reject (row) {
      this.$confirm('是否通过该社区申请?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return updateTopic({
          id: row.id,
          status: 'R'
        })
      }).then( _ => {
        row.status = 'R'
      })
    },
    addInvitationShow ( row ) {
      this.invitationShow = true
      this.invitationform.topicId = row.id
      this.invitationform.topicTitle = row.title
      this.invitationform.content = ''
      this.invitationform.title = ''
      this.invitationform.remark = ''
    },
    saveInvitation() {
      addInvitation(this.invitationform).then(_ => {
        this.msgSuccess("新增帖子成功")
        this.invitationShow = false
      });
    }
  }
};
// {"title":"校园适应","cover":"","description":"为大一新生能够快速适应大学生活设立专题","fileUrl":"","reason":"为大一新生能够快速适应大学生活设立专题"}
</script>


<style scoped>
.avatar-uploader /deep/ .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader /deep/.el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>