<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入用户姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学校" prop="school">
        <el-input
          v-model="queryParams.school"
          placeholder="请输入学校"
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
          v-hasPermi="['user:user:add']"
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
          v-hasPermi="['user:user:edit']"
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
          v-hasPermi="['user:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['user:user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index"/>
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="用户昵称" align="center" prop="nickname" />
      <el-table-column label="用户姓名" align="center" prop="name" />
      <el-table-column label="头像地址" align="center" prop="avatar" >
        <template slot-scope="scope">
          <el-avatar :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="sex" />
      <el-table-column label="手机号" align="center" prop="telephone" width="150" />
      <el-table-column label="邮箱" align="center" prop="email" width="200"/>
      <el-table-column label="学校" align="center" prop="school" width="150" />
      <el-table-column label="院系" align="center" prop="dept" width="200" />
      <el-table-column label="专业" align="center" prop="major" width="150" />
      <el-table-column label="家庭地址" align="center" prop="address" width="200" />
      <el-table-column label="现居住地" align="center" prop="contact" />
      <el-table-column label="介绍" align="center" prop="introduce" />
      <el-table-column label="兴趣爱好" align="center" prop="interest" />
      <el-table-column label="用户积分" align="center" prop="integral" />
      <el-table-column label="状态" align="center" prop="status" > 
        <template slot-scope="scope">
          <el-switch
            :value=" scope.row.status == 'Y' "
            active-color="#13ce66"
            inactive-color="#ff4949"
            @change = "changeStatus(scope.row)">
          </el-switch>
        </template>        
      </el-table-column >
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="100px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['user:user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-plus-outline"
            @click="addIntegralShow(scope.row)"
          >增加积分</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['user:user:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="用户昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="用户姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入用户姓名" />
        </el-form-item>
        <el-form-item label="用户密码">
          <el-input v-model="form.password" placeholder="请输入用户密码" show-password />
        </el-form-item>
        <el-form-item label="头像地址" prop="avatar">
          <el-upload
            class="avatar-uploader"
            action="http://localhost/dev-api/app/upload/avatar"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="form.avatar" :src="form.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
            <el-radio label="未知"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="telephone">
          <el-input v-model="form.telephone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="学校" prop="school">
          <el-input v-model="form.school" placeholder="请输入学校" />
        </el-form-item>
        <el-form-item label="院系" prop="dept">
          <el-input v-model="form.dept" placeholder="请输入院系" />
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major" placeholder="请输入专业" />
        </el-form-item>
        <el-form-item label="家庭地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入家庭地址" />
        </el-form-item>
        <el-form-item label="现居住地" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入现居住地" />
        </el-form-item>
        <el-form-item label="介绍" prop="introduce">
          <el-input v-model="form.introduce" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="兴趣爱好" prop="interest">
          <el-input v-model="form.interest" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改用户积分记录对话框 -->
    <el-dialog title="添加积分" :visible.sync="integralShow" width="500px" append-to-body>
      <el-form ref="integralForm" :model="integralForm"  label-width="80px">
        <el-form-item label="增加值" >
          <el-input v-model="integralForm.addValue" placeholder="请输入增加多少积分" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="integralForm.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveIntegral">确 定</el-button>
        <el-button @click="integralShow = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser, exportUser } from "@/api/user/user";
import { addIntegral } from "@/api/user/integral";
export default {
  name: "User",
  components: {
  },
  data() {
    return {
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
      // 用户表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        school: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      integralShow: false,
      integralForm: {
        addValue: '',
        remark: '',
        userId: '',
        nickname: '',
        perValue: ''
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = response.rows;
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
        username: undefined,
        nickname: undefined,
        name: undefined,
        avatar: undefined,
        password: undefined,
        sex: "男",
        telephone: undefined,
        email: undefined,
        school: undefined,
        dept: undefined,
        major: undefined,
        address: undefined,
        contact: undefined,
        introduce: undefined,
        interest: undefined,
        integral: undefined
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
      this.title = "添加用户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUser(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
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
      this.$confirm('是否确认删除用户编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUser(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportUser(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    handleAvatarSuccess(res, file) {
      this.form.avatar = res.data
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
    changeStatus (row) {
      let status = row.status == 'Y' ? 'N':'Y'
      updateUser({
        id: row.id,
        status
      }).then( _ => {
        row.status = status
      });
    },
    addIntegralShow (row) {
      this.integralForm.userId = row.id
      this.integralForm.nickname = row.username
      this.integralForm.addValue = 1
      this.integralForm.remark = ''
      this.integralForm.perValue = row.integral
      this.integralShow = true
    },
    saveIntegral() {
      addIntegral(this.integralForm).then(response => {
        this.msgSuccess("新增成功");
        this.integralShow = false;
        this.getList()
      });
    }
  }
};
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