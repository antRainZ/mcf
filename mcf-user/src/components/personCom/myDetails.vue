<template>
  <div class="main">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="账号信息" name="base"> 
        <p>用户名：{{form.username}} </p>
        <p>邮箱：{{form.email}} </p>
        <p>积分：{{form.integral}}</p>
      </el-tab-pane>
      <el-tab-pane label="个人信息" name="info">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="昵称">
            <el-input v-model="form.nickname"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.telephone"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="form.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
              <el-radio label="未知"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="学校">
            <el-input v-model="form.school"></el-input>
          </el-form-item>
          <el-form-item label="院系">
            <el-input v-model="form.dept"></el-input>
          </el-form-item>
          <el-form-item label="专业">
            <el-input v-model="form.major"></el-input>
          </el-form-item>
          <el-form-item label="家庭地址">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
          <el-form-item label="现居住地">
            <el-input v-model="form.contact"></el-input>
          </el-form-item>
          <el-form-item label="个人介绍">
            <el-input type="textarea" v-model="form.introduce"></el-input>
          </el-form-item>
          <el-form-item label="兴趣爱好">
            <el-input type="textarea" v-model="form.interest"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveInfo">保存信息</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="修改密码" name="password">
        <el-form ref="pwdForm" :model="pwdForm" :rules="pwdRules" label-width="80px">
          <el-form-item label="旧密码" prop="oldPwd">
            <el-input v-model="pwdForm.oldPwd" show-password></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPwd">
            <el-input v-model="pwdForm.newPwd" show-password></el-input>
          </el-form-item>
          <el-form-item label="密码确认" prop="checkPwd">
            <el-input v-model="pwdForm.checkPwd" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updatePwd">修改密码</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="积分记录" name="integral">
        <el-table :data="tableData"  style="width: 100%" >
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column label="备注" prop="remark" ></el-table-column>
          <el-table-column label="增加值" prop="addValue" width="100"></el-table-column>
          <el-table-column label="增加前" prop="perValue" width="100"></el-table-column>
          <el-table-column label="创建时间" prop="createTime" width="200"></el-table-column>
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
    </el-tabs>

  </div>  
</template>

<script>
import userApi from '@/api/user'
import integralApi from '@/api/integral'
export default {
 data() {
  var validatePass = (rule, value, callback) => {
    if (value === '') {
      callback(new Error('请输入密码'))
    } else {
      if (this.pwdForm.checkPwd !== '') {
        this.$refs.pwdForm.validateField('checkPwd')
      }
      callback()
    }
  }
  var validatePass2 = (rule, value, callback) => {
    if (value === '') {
      callback(new Error('请再次输入密码'))
    } else if (value !== this.pwdForm.newPwd) {
      callback(new Error('两次输入密码不一致!'))
    } else {
      callback()
    }
  }
  return {
      activeName: 'base',
      form: {},
      pwdForm: {
        oldPwd: '',
        newPwd: '',
        checkPwd:''
      },
      pwdRules: {
        oldPwd: [
          { required: true, message: '请输入旧密码', trigger: 'blur' },
        ],
        newPwd: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPwd: [
          { required: true, message: '请确认新密码', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      queryInfo: {
        page: 1,
        limit: 5
      },
      tableData: [],
      total: 0,
    }
  },
  mounted () {
    userApi.getInfo().then(response=>{
      this.form = response.data
    })
    this.getData()
  },
  methods:{
    getData () {
      integralApi.getMyList(this.queryInfo).then(response=>{
        this.tableData = response.data.records
        this.total = parseInt(response.data.total)
      })
    }, 
    handleClick(tab, event) {
      console.log(tab, event);
    },
    saveInfo(){
      userApi.update(this.form).then(response=>{
        if(response.code == 200) {
          this.$message({
            showClose: true,
            message: '修改成功',
            type: 'success'
          });
        }
      })
    },
    updatePwd(){
      this.$refs.pwdForm.validate(valid => {
        if (!valid) return
        userApi.updatePwd(this.pwdForm).then( response=>{
          if (response.code == 200) {
            this.pwdForm = {}
            this.$message({
              showClose: true,
              message: '密码修改成功',
              type: 'success'
            });            
          }
        })
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
  }
}
</script>

<style lang="less" scoped>

</style>