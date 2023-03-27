<template>
  <div class="index dis-fx" style="1000px">
    <div class="main dis-fx">
      <div class="aside">
        <div class="fx-col fx-jc fx-ac mt-0925 bc-fff pb-10">
          <div class="head mt-20 mb-20"  @click="handleAvatarShow" style=" height:50px ;width:50px">
            <img :src="avatar" alt="" style=" height:50px ;width:50px" />
          </div> 
          <div class="user f-14 pb-10 f-w">
            {{nickname}}
          </div>
          <!-- <div class="intro f-12 fx-jc ">
            <span class="p-5">0 帖子</span>
            <span class="p-5">|</span>
            <span class="p-5">0 粉丝</span>
          </div> -->
        </div>
         <div class="mt-0925 bc-fff pb-10 f-14">
            <div :class="[activeIndex==1?'activeItem':'','item p-10 cur-po']"  @click="go1()">我的资料</div>
            <div :class="[activeIndex==2?'activeItem':'','item p-10 cur-po']"  @click="go2()">我的社区</div>
            <div :class="[activeIndex==3?'activeItem':'','item p-10 cur-po']"  @click="go3()">我的帖子</div>
            <div :class="[activeIndex==4?'activeItem':'','item p-10 cur-po']"  @click="go4()">我的评论</div>
            <div :class="[activeIndex==5?'activeItem':'','item p-10 cur-po']"  @click="go5()">好友列表</div>
        </div>
      </div>
      <div class="article bc-fff mt-0925 p-20" >
        <router-view></router-view>
      </div>
    </div>
    
    <el-dialog  title="修改头像" :visible.sync="avatarShow" width="30%">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:8081/app/upload/avatar"
        :show-file-list="false"
        :on-success="success"
        :before-upload="beforeAvatarUpload">
        <img v-if="avatarTmp" :src="avatarTmp" class="avatar" style="height:100px ;width:100px">
        <i v-else class="el-icon-plus avatar-uploader-icon" ></i>
      </el-upload>
      <span slot="footer" class="dialog-footer">
        <el-button @click="avatarShow = false ">取 消</el-button>
        <el-button type="primary" @click="save" > 确定 </el-button>
      </span>
    </el-dialog>
   
  </div>
</template>

<script>
import HeadText from '@/components/pubCom/headText'
import { readBySession,writeBySession } from '@/utils/storeUtil'
import { isNotBlank } from '@/utils/stringUtil'
import userApi from '@/api/user'
export default {
components:{
    HeadText
  },
  data(){
    return {
      activeIndex:1,
      avatar: '',
      nickname: 'test',
      avatarShow: false,
      avatarTmp: ''
    }
  },
  mounted () {
    this.avatar =  readBySession("avatar")
    this.nickname =  readBySession("nickname")
    this.activeIndex = readBySession('activeIndex')
    if (!isNotBlank(this.activeIndex)) {
      this.activeIndex = 1
    }
  },
  methods:{
    handleSelect(){

    },
    save() {
      userApi.updateAvatar({
        avatar: this.avatarTmp
      }).then(response=>{
        if (response.code != 200) return
        this.avatar = this.avatarTmp
      })
    },
    handleAvatarShow() {
      this.avatarTmp = this.avatar
      this.avatarShow = true
    },
    go1(){
      if (this.activeIndex==1) return
      this.activeIndex=1
      writeBySession('activeIndex',1)
      this.$router.push({path:'/perdetails'})
    },
    go2(){
      if (this.activeIndex==2) return
      this.activeIndex=2
      writeBySession('activeIndex',2)
      this.$router.push({path:'/mycommunity'})
    },
    go3(){
      if (this.activeIndex==3) return
      this.activeIndex=3
      writeBySession('activeIndex',3)
      this.$router.push({path:'/mypost'})
    },
    go4(){
      if (this.activeIndex==4) return
      this.activeIndex=4
      writeBySession('activeIndex',4)
      this.$router.push({path:'/myComment'})
    },
    go5(){
      if (this.activeIndex==5) return
      this.activeIndex=5
      writeBySession('activeIndex',5)
      this.$router.push({path:'/myfriend'})
    },
    success (res) {
      if (res.code != 200) return
      this.avatarTmp = res.data
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type == 'image/jpeg' || file.type == 'image/png'
      const isLt10M = file.size / 1024 / 1024 < 10
      if (!isJPG) {
        this.$message.error('图片只能是 JPG、PNG 格式!')
      }
      if (!isLt10M) {
        this.$message.error('图片大小不能超过 10MB!')
      }
      return isJPG && isLt10M
    }
  }
}
</script>

<style lang="less" scoped>
.index{
  background-color: #f4f5f5;
  justify-content: center;
  padding-top: 4rem;
  min-height: 94vh;
  padding-bottom: 1.25rem;
  .main{
    max-width: 960px;
    .aside{
      width: 15rem;
      flex: 1;
      .head{
        width: 3.125rem;
        height: 3.125rem;
        border-radius: 50%;
        overflow: hidden;
        background-color: yellowgreen;
      }
      .item{
        border-left: 5px solid transparent;
      }
      .item:hover{
        background-color: #409EFF;
        color: #fff;
      }
      .activeItem{
        border-left: 5px solid #409EFF;
      }
    }
    .article{
      width: 700px;
      margin-left: 1.25rem;
      font-size: 0.975rem;
    }
  }
}
.el-upload {
  border: 1px dashed #4649e9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #7aa3dd;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 150px;
  height: 150px;
  display: block;
}
</style>