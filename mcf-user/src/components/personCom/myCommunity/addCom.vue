<template>
  <div>
    <div class="main fx-jc cur-po" v-if="addCardShow">
      <div class="add fx-jc fx-ac f-22 c-888"  @click="addCardShow=false"> + 创建社区</div>
    </div>
    <div v-else>
      <el-form ref="form" :model="form"  label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="封面" >
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8081/app/upload/avatar"
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
            action="http://localhost:8081/app/upload/apply"
            :on-success ="success"
            :limit="1"
            name="file" >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="理由" prop="reason">
          <el-input v-model="form.reason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">立即创建</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import topicApi from '@/api/topic'
export default {
  name: 'addCommunity',
  props: ['addShow'],
  data(){
    return {
      addCardShow:true,
      form: {
        // 
        cover:  '',
        title: 'test111',
        description: 'test111',
        reason: 'test111',
        fileUrl: ''
      }
    }
  },
  methods: {
    save() {
      topicApi.add(this.form).then(_=>{
        this.$emit('getTopic');
      })
    },
    success(response, file, fileList) {
      this.form.fileUrl = response.data
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
  }
}
</script>

<style lang="less" scoped>
.main{
  .add{
    width: 500px;
    height: 100px;
    font-style:italic;
    border: 2px dashed #ccc;
  }
}
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