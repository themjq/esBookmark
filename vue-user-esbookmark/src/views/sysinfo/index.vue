<template>
 <div>
    <el-card class="divcard">
      <div style="display: flex;justify-content: space-between;">
        <p class="info" style="float:left;position: relative;font-size: 35px;margin-top: 20px;">用户信息</p>
        <el-avatar :size="120" :src="userInfo.avatar" @error="errorHandler" style="float: right;">
          <el-avatar> {{ userInfo.username }} </el-avatar>
        </el-avatar>
      </div>
    </el-card>
    <el-card class="divcard" style="display: flex;">
      <div class="card2">
        <div class="header" >
            <p class="name">用户ID</p>
        </div>
        <p class="message">
          {{ userInfo.id ==''?'暂无':userInfo.id}}
          
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">用户名</p>
        </div>
        <p class="message">
          {{ userInfo.username ==''?'暂无':userInfo.username }}
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">密码</p>
        </div>
        <p class="message">
          {{ userInfo.password =='' ?'暂无':userInfo.password}}
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">昵称</p>
        </div>
        <p class="message">
          {{ !userInfo.name   ?'暂无':userInfo.name}}
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">性别</p>
        </div>
        <p class="message">
          {{sex}}
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">手机号</p>
        </div>
        <p class="message">
          {{ !userInfo.phone?'暂无':userInfo.phone}}
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">状态</p>
        </div>
        <p class="message">
          {{ status }}
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">创建时间</p>
        </div>
        <p class="message">
          {{ !userInfo.createTime?'暂无':userInfo.createTime.replace('T',' ') }}
        </p>
      </div>

      <div class="card2">
        <div class="header" >
            <p class="name">最后更新时间</p>
        </div>
        <p class="message">
          {{ !userInfo.updateTime?'暂无':userInfo.updateTime.replace('T',' ')}}
        </p>
      </div>
    </el-card>
 </div>
</template>

<script>
import { removeToken,getToken } from '@/utils/auth'
import { selectById } from '@/api/user'
export default {
  data(){
    return{
      userInfo:''
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
    }
  },
  mounted(){
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
  
</style>
