<template>
  <div class="index dis-fx">
    <header>
      <div class="out dis-fx">
        <div class="logo cur-po">
          <img src="../../assets/logo.png" alt="">
        </div>
        <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="2" :route="{name:'friends'}" >交友大厅</el-menu-item>
          <el-menu-item index="3" :route="{name:'communitylist'}">社区中心</el-menu-item>
          <el-menu-item v-for="item in topicRefine" :key="item.id" 
            :index="item.id+ 'refine'" :route=" {path: '/community/'+item.id }"> {{item.title}}</el-menu-item>
        </el-menu>
        <div class="login cur-po">
          <button v-if="isLogin" class="login-btn" @click="toLogin" >登录</button>
          <el-dropdown v-else placement="bottom">
            <span class="el-dropdown-link">
              <div class="touxiang">
                <img :src="headImg" alt="" style="width:50px;height:50px">
              </div>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="goPer">个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </header>

    <!-- 用户点击弹出的对话框 -->
    <el-dialog title="登录注册" :visible.sync="dialogTableVisible" width="30%">
      <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="用户登录" name="login">
          <el-form ref="form" :model="form" :rules="loginRules" label-width="80px">
            <el-form-item label="用户名">
              <el-input v-model="form.username" autocomplete="off" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input type="password" v-model="form.password" autocomplete="off" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">登录</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <!-- 用户注册  -->
        <el-tab-pane label="用户注册" name="register">
          <el-form ref="formSign" :model="formSign" :rules="rules" label-width="80px">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="formSign.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="formSign.password" autocomplete="off" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="password2">
              <el-input type="password" v-model="formSign.password2" placeholder="请再次输入密码"></el-input>
            </el-form-item>
            <el-form-item label="验证邮箱" prop="email">
              <div class="dis-fx">
                <el-input type="email" v-model="formSign.email" placeholder="请输入验证邮箱">
                  <template slot="append">
                    <el-button @click="sendCode">发送验证码</el-button>
                  </template>
                </el-input>
              </div>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
              <el-input v-model="formSign.code" placeholder="请输入验证码"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSign">注册</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <!-- 找回密码 -->
        <el-tab-pane label="找回密码" name="retrieve">
          <el-form ref="retrieveform" :model="retrieveform" :rules="rules2" label-width="80px">
            <el-form-item label="登录邮箱" prop="email">
              <div class="dis-fx">
                <el-input type="email" v-model="retrieveform.email" placeholder="请输入验证邮箱">
                  <template slot="append">
                    <el-button @click="sendCode2">发送验证码</el-button>
                  </template>
                </el-input>
              </div>
            </el-form-item>
            <el-form-item label="验证码" prop="code">
              <el-input v-model="retrieveform.code" placeholder="请输入验证码"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="retrieveform.password" autocomplete="off" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="password2">
              <el-input type="password" v-model="retrieveform.password2" placeholder="请再次输入密码"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="retrieve">确定</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>

<script>
import userApi from '@/api/user'
import topicApi from '@/api/topic'
import { readBySession , saveUser, clearUser } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
export default {
  data(){
    var checkEmail = (rule, value, cb) => {
      // 验证手机号的正则表达式
      const reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/
      if (reg.test(value)) {
        if (!this.ok) return cb()
        userApi.checkEmail(value).then(response => {
          if (response.code != 200) {
            return cb(new Error('该邮箱已被注册'))
          }
          return cb()
        })
      } else {
        return cb(new Error('请输入合法的邮箱地址'))
      }
    }
    var checkName = (rule, value, cb) => {
      if (!this.ok) return cb()
      userApi.checkName(value).then( response => {
        if (response.code != 200) {
          return cb(new Error('该用户名已被注册'))
        }
        return cb()
      })
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.formSign.password2 !== '') {
          this.$refs.formSign.validateField('password2')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.formSign.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var validatePass3 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.retrieveform.password2 !== '') {
          this.$refs.retrieveform.validateField('password2')
        }
        callback()
      }
    }
    var validatePass4 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.retrieveform.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      activeIndex: "/",
      isLogin:true,
      headImg:'../../assets/logo.png',
      dialogTableVisible:false,
      activeName:'login',
      form: {
        username: 'antrain',
        password: '123456',
      },
      formSign:{
        username:'',
        password:'',
        password2:'',
        email:'',
        code:''
      },
      retrieveform: {
        password:'',
        password2:'',
        email:'',
        code:''
      },
      loginRules: {
        username: [
          {required: true, message: '账号不可为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不可为空', trigger: 'blur'}
        ]
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { validator: checkName, trigger: 'blur' }
        ],
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        password2: [
          { required: true, message: '请再次输入登录密码', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      },
      rules2: {
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { validator: validatePass3, trigger: 'blur' }
        ],
        password2: [
          { required: true, message: '请再次输入登录密码', trigger: 'blur' },
          { validator: validatePass4, trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { validator: checkEmail, trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      },
      topicRefine: [],
      ok: false,
    }
  },
  mounted() {
    this.activeIndex =  window.sessionStorage.getItem("activeIndex") || '/'
    let avatar =  readBySession("avatar")
    if (isNotBlank(avatar)) {
      this.headImg = avatar
      this.isLogin = false
    }
    topicApi.indexTopic().then( response =>{
      if (response.code != 200) return
      this.topicRefine = response.data
    })
  },
  methods:{
    /**
     * 登录逻辑
     */
    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
          userApi.login(this.form).then(response => {
            if (response.code != 200) return
            this.headImg = response.data.avatar
            saveUser(response.data)
            this.dialogTableVisible = false
            this.isLogin = false
            this.$message({
              message: '登录成功',
              type: 'success'
            })
          })
        }
      });
    },
    /**
     * 注册逻辑
     */
    onSign(){
      this.ok = true
      this.$refs.formSign.validate(async valid => {
        if (!valid) return
        userApi.register(this.formSign).then(response=>{
          if (response.code == 200) {
            this.$message({
              message: '注册成功',
              type: 'success'
            })
            this.activeName = "login"
          }
        })
      })
      this.ok = false
    },
    toLogin(){
      this.dialogTableVisible = true
    },
    handleSelect(key) {
      window.sessionStorage.setItem("activeIndex",key)
    },
    goPer(){
      this.$router.push({path:'/perdetails'})
      this.activeIndex = '' 
      window.sessionStorage.setItem("activeIndex",'')
    },
    logout () {
      clearUser()
      this.isLogin = true
      this.$router.push({path:'/'})
    },
    sendCode () {
      this.ok = true
      this.$refs.formSign.validateField("email", errMsg => {
        if (errMsg) return
        userApi.getCode(this.formSign.email).then( response => {
          if (response.code != 200) {
            this.$message({
              message: response.msg,
              type: 'error'
            })
            return
          }
          this.$message({
            message: '验证码已成功发送，请注意接收',
            type: 'success'
          });
        })
      })
      this.ok = false
    },
    // 找回密码 请求验证码
    sendCode2 () {
      this.$refs.retrieveform.validateField("email", errMsg => { 
        userApi.getCode(this.retrieveform.email).then( response => {
          if (response.code != 200) {
            this.$message({
              message: response.msg,
              type: 'error'
            })
            return
          }
          this.$message({
            message: '验证码已成功发送，请注意接收',
            type: 'success'
          });
        })
      })
    },
    // 找回密码
    retrieve() {
      this.$refs.retrieveform.validate(async valid => {
        if (!valid) return
        userApi.retrieve(this.retrieveform).then(response=>{
          if (response.code == 200) {
            this.$message({
              message: '修改密码成功',
              type: 'success'
            })
            this.activeName = "login"
          }
        })
      })
    }
  }
}
</script>

<style lang="less" scoped>
.index{
  background-color: #f4f5f5;
  justify-content: center;
  header{
  position: fixed;
  top: 0;
  border-bottom: solid 1px #e6e6e6;
  background-color: #fff;
  width: 100%;
  z-index: 100;
    .out{
      max-width: 960px;
      border-bottom:none;
      margin: 0 auto;
      height: 4rem;
      justify-content: center;
      align-items: center;
      .logo{
        width: 3.75rem;
        height: 3.75rem;
        margin-right: 3.125rem;
      }
      .el-menu--horizontal>.el-menu-item.is-active{
        border-bottom:none !important;
        color: #409EFF !important;
      }
      .el-menu.el-menu--horizontal {
        border-bottom: none ;
      }
      .el-menu{
        flex: 1;
        .el-menu-item{
          transition: border-color .0s;
          font-size: 1rem;
        }
      }
      .login{
        .touxiang{
          width: 50px;
          height: 50px;
          border-radius: 50%;
          overflow:hidden;
          background-color:aqua;
        }
      }
    }
  }
  .el-dialog{
    width: 50vw;
  }
  .el-menu-demo {
    height: 4rem;
  }
}
</style>

<style>
.index .el-menu>.el-menu-item {
  height: 4rem;
  line-height: 4rem;
}
</style>