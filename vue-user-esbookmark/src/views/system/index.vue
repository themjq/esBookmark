<template>
 <div>
    <el-card class="divcard">
      <div style="display: flex;justify-content: space-between;">
        <p class="info" style="float:left;position: relative;font-size: 35px;margin-top: 20px;">信息修改</p>
        <el-avatar :size="120" :src="userInfo.avatar" @error="errorHandler" style="float: right;">
          <el-avatar> {{ userInfo.username }} </el-avatar>
        </el-avatar>
      </div>
    </el-card>
    <el-card class="divcard" >
      <!--添加数据对话框表单-->
        <el-form class="updateform" ref="form" :model="userInfo" label-width="80px">
          <el-form-item label="用户名">
            <el-input v-model="userInfo.username"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="userInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="userInfo.password"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="userInfo.phone"></el-input>
          </el-form-item>
          <el-form-item label="身份证">
            <el-input v-model="userInfo.idNumber"></el-input>
          </el-form-item>
          <el-form-item label="性别" >
            <el-select v-model="userInfo.sex" placeholder="请选择" style="width:100%" >
              <el-option
                v-for="item in sexList"
                :key="item.value"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="头像">
            <el-upload
              class="avatar-uploader"
              action="/api/upload"
              :headers="token"
              name="image"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="userInfo.avatar" :src="userInfo.avatar" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>

          <el-form-item label="状态">
          <el-select v-model="userInfo.status" placeholder="请选择" value-key="userInfo.status" style="width:100%">
            <el-option
              v-for="item in statusList"
              :key="item.value"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

          <el-form-item label="创建日期">
            <el-date-picker
              v-model="userInfo.createTime"
              clearable
              type="date"
              placeholder="选择日期"
              size="small"
              style="width:100%"
            ></el-date-picker>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="updateUser">提交</el-button>

          </el-form-item>
        </el-form>
    </el-card>
 </div>
</template>

<script>
import { removeToken,getToken } from '@/utils/auth'
import { update,selectById } from '@/api/user.js'

export default {
  data(){
    return{
      userInfo:{
        
      },
      // 添加数据对话框是否展示的标记
      sexList: [{ id: 1, name: '男' }, { id: 2, name: '女' }],
      statusList: [
        {
          id: 0,
          name: '启用'
        },
        {
          id: 1,
          name: '禁用'
        }
      ],
      token: { token: getToken() }
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      removeToken()
      this.$router.push('/login')
    },
    errorHandler() {
        return true
    },
    // 文件上传相关
    handleAvatarSuccess(res, file) {
      this.userInfo.avatar = res.data
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    // 添加数据
    updateUser() {
      let operator
        // 修改
      operator = update(this.userInfo)
      operator.then((resp) => {
        if (resp.data.code === 1) {
          this.$message({ message: '恭喜你，保存成功', type: 'success' })
          this.getUser()
        } else {
          this.$message.error(resp.data.msg)
        }
      })
    },
    getUser(){
      const jwt = require('jsonwebtoken');
        const token = getToken()
        try {
          var decodedToken = jwt.decode(token);
          // console.log(decodedToken); 
          let operator= selectById(decodedToken.id)
          operator.then((resp) => {
          if (resp.data.code === 1) {
            this.userInfo=resp.data.data
            // console.log(this.userInfo)
          } else {
            this.$message.error("获取用户信息失败")
          }
        })
        } catch (err) {
          console.error('无法解码 JWT:', err);
        }
    }
  },
  mounted(){
      this.getUser()
  },
  computed:{
    sex(){
      if (!this.userInfo.sex) {
        return "暂无";
      } else if (this.userInfo.sex === 1) {
        return "男";
      } else if (this.userInfo.sex === 2) {
        return "女";
      }
    },
    status(){
      if (this.userInfo.status ==undefined) {
        return "暂无";
      } else if (this.userInfo.status === 0) {
        return "正常";
      } else if (this.userInfo.status === 1) {
        return "禁用";
      }
    }
  }
}
</script>
<style>
.divcard{
  margin-top: 12px;
  width: 90%;
  margin-left: 5%;
}
.divcard .info{
  font-size: 32px;
  font-family: "PingFang SC";
  margin: 0;
}
.text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}

  .card2 {
  background-color: rgba(243, 244, 246, 1);
  padding: 2rem;
  width:225px;
  height: 125px;
  margin: 15px;
  border-radius: 10px;
  float: left;
}
.name {
  margin-top: 0.25rem;
  font-size: 1.125rem;
  font-weight: 600;
  --tw-text-opacity: 1;
  color: rgba(55, 65, 81, 1);
}

.message {
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  color: rgba(107, 114, 128, 1);
}


.updateform{
  width: 60%;
  margin-left: 20%;
}
.updateform p{
  color: #409eff;
}
</style>
